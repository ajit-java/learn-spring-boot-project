package de.scout24.financing.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import de.scout24.financing.controller.AnswerController;
import de.scout24.financing.controller.ForumUserController;
import de.scout24.financing.controller.QuestionController;
import de.scout24.financing.controller.UserController;
import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.User;
import de.scout24.financing.resource.ForumUserResource;

@Component
public class ForumUserResourceAssembler implements ResourceAssembler<ForumUser, ForumUserResource> {

    @Override
    public ForumUserResource toResource(ForumUser forumUser) {
        ForumUserResource resource = new ForumUserResource(forumUser);

//        getLinks(forumUser, resource);
        return resource;
    }

    private void getLinks(ForumUser forumUser, ForumUserResource resource) {
        try {
            resource.add(linkTo(methodOn(ForumUserController.class).getForumUser(forumUser.getId())).withSelfRel());
            resource.add(linkTo(methodOn(UserController.class).getUser(forumUser.getUser().getId())).withRel(User.class.getSimpleName()));
            resource.add(
                    linkTo(methodOn(QuestionController.class).getQuestionsByForumUser(forumUser.getUser().getId(), new PageRequest(0, 20), null))
                            .withRel(Question.class.getSimpleName()));
            resource.add(
                    linkTo(methodOn(AnswerController.class).getAnswersByForumUser(forumUser.getUser().getId(), new PageRequest(0, 20), null))
                            .withRel(Answer.class.getSimpleName()));
        } catch (ResourceNotFoundException ex) {
            // do nothing
        }
    }

}
