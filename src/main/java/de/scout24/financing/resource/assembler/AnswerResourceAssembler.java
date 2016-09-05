package de.scout24.financing.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import de.scout24.financing.controller.AnswerController;
import de.scout24.financing.controller.CommentController;
import de.scout24.financing.controller.ForumUserController;
import de.scout24.financing.controller.QuestionController;
import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Question;
import de.scout24.financing.repository.CommentRepository;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.resource.AnswerResource;

@Component
public class AnswerResourceAssembler implements ResourceAssembler<Answer, AnswerResource> {

    @Autowired
    private ForumUserRepository forumUserRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public AnswerResource toResource(Answer answer) {
        AnswerResource resource = new AnswerResource(answer);

        resource.setCommentCount(commentRepository.countByAnswerIdAndDeletedFalse(answer.getId()));

        ForumUser forumUser = forumUserRepository.findOne(answer.getForumUserId());
        resource.setForumUserName(forumUser.getFirstName() + " " + forumUser.getLastName());
        resource.setUserEmail(forumUser.getUser().getEmail());

//        getLinks(answer, resource);

        return resource;
    }

    private void getLinks(Answer answer, AnswerResource resource) {
        try {
            resource.add(linkTo(methodOn(CommentController.class).getComment(answer.getId())).withSelfRel());
            resource.add(linkTo(methodOn(ForumUserController.class).getForumUser(answer.getForumUserId()))
                    .withRel(ForumUser.class.getSimpleName()));
            resource.add(
                    linkTo(methodOn(QuestionController.class).getQuestion(answer.getQuestionId())).withRel(Question.class.getSimpleName()));
            resource.add(linkTo(methodOn(AnswerController.class).getAnswerComments(answer.getId(), new PageRequest(0, 20), null))
                    .withRel(Comment.class.getSimpleName()));
        } catch (ResourceNotFoundException ex) {
            // do nothing
        }
    }
}
