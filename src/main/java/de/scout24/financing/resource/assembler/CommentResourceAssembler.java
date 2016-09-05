package de.scout24.financing.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
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
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.resource.CommentResource;

@Component
public class CommentResourceAssembler implements ResourceAssembler<Comment, CommentResource> {

    @Autowired
    private ForumUserRepository forumUserRepository;

    @Override
    public CommentResource toResource(Comment comment) {
        CommentResource resource = new CommentResource(comment);

        ForumUser user = forumUserRepository.findOne(comment.getForumUserId());
        resource.setForumUserName(user.getFirstName() + " " + user.getLastName());
        resource.setUserEmail(user.getUser().getEmail());
        resource.setExpertUserComment(user.getRole().getName() == ExfoRole.EXPERT_APPROVED);

//        getLinks(comment, resource);

        return resource;
    }

    private void getLinks(Comment comment, CommentResource resource) {
        try {
            resource.add(linkTo(methodOn(CommentController.class).getComment(comment.getId())).withSelfRel());
            resource.add(linkTo(methodOn(ForumUserController.class).getForumUser(comment.getForumUserId()))
                    .withRel(ForumUser.class.getSimpleName()));
            resource.add(linkTo(methodOn(AnswerController.class).getAnswer(comment.getAnswerId())).withRel(Answer.class.getSimpleName()));
            resource.add(linkTo(methodOn(QuestionController.class).getQuestion(comment.getQuestionId()))
                    .withRel(Question.class.getSimpleName()));
        } catch (ResourceNotFoundException ex) {
            // do nothing
        }
    }
}
