package de.scout24.financing.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import de.scout24.financing.controller.ForumUserController;
import de.scout24.financing.controller.QuestionController;
import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;
import de.scout24.financing.repository.AnswerRepository;
import de.scout24.financing.repository.CommentRepository;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.resource.QuestionResource;
import de.scout24.financing.service.impl.ProfileService;
import de.scout24.financing.service.impl.QuestionService;
import de.scout24.financing.utility.DateTimeHelper;

/**
 * Created by ajitchahal on 08/09/15.
 */
@Component
public class QuestionResourceAssembler implements ResourceAssembler<Question, QuestionResource> {
    @Autowired
    private ForumUserRepository forumUserRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    private NotificationRepository notificationRepository;
//
    @Autowired
    private ProfileService profileService;

    @Autowired
    private QuestionService questionService;

    @Override
    public QuestionResource toResource(Question question) {
        QuestionResource resource = new QuestionResource(question);

        resource.setAnswerCount(answerRepository.countByQuestionIdAndDeletedFalse(question.getId()));
        resource.setCommentCount(commentRepository.countByQuestionIdAndDeletedFalse(question.getId()));
        ForumUser forumUserOfQuestion = forumUserRepository.findOne(question.getForumUserId());
        if (forumUserOfQuestion != null && forumUserOfQuestion.getFirstName() != null && forumUserOfQuestion.getLastName() != null) {
            resource.setForumUserName(forumUserOfQuestion.getFirstName() + " " + forumUserOfQuestion.getLastName());
            resource.setUserEmail(forumUserOfQuestion.getUser().getEmail());
        }
        resource.setLastActivity(DateTimeHelper.getUtcDatetimeAsDate());// TODO @ hary: get it from db
        resource.setIsfavorite(questionService.isMarkedAsFavorite(question.getId()));

//        notificationRepository.getNotificationStatusByDomainObjectTypeAndDomainObjectIdAndDeletedFalse()
//        resource.setNotificationStatus(  );
//        getLinks(question, resource);

        return resource;
    }

    private void getLinks(Question question, QuestionResource resource) {
        try {
            resource.add(linkTo(methodOn(QuestionController.class).getQuestion(question.getId())).withSelfRel());
            resource.add(linkTo(methodOn(ForumUserController.class).getForumUser(question.getForumUserId()))
                    .withRel(ForumUser.class.getSimpleName()));
            resource.add(linkTo(methodOn(QuestionController.class).getQuestionAnswers(question.getId(), new PageRequest(0, 20), null))
                    .withRel(Answer.class.getSimpleName()));
            resource.add(linkTo(methodOn(QuestionController.class).getQuestionComments(question.getId(), new PageRequest(0, 20), null))
                    .withRel(Comment.class.getSimpleName()));
            resource.add(linkTo(methodOn(QuestionController.class).getQuestionsByPublishingStatus(question.getPublishingStatus(),
                    new PageRequest(0, 20), null)).withRel(PublishingStatus.class.getSimpleName()));
            resource.add(linkTo(
                    methodOn(QuestionController.class).getQuestionsByStatus(question.getQuestionStatus(), new PageRequest(0, 20), null))
                            .withRel(QuestionStatus.class.getSimpleName()));
        } catch (ResourceNotFoundException ex) {
            // do nothing
        }
    }

    // public List<QuestionResource> toResource(List<Question> questions) {
    // List<QuestionResource> questionResources = new
    // ArrayList<QuestionResource>();
    // for (Iterator<Question> i = questions.iterator(); i.hasNext(); ) {
    // QuestionResource resource = toResource(i.next());
    // questionResources.add(resource);
    // }
    //
    // return questionResources;
    // }
}
