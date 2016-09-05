package de.scout24.financing.service.impl;

import javax.transaction.Transactional;

import de.scout24.financing.domain.*;
import de.scout24.financing.service.impl.notifications.NotificationQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.AnswerRepository;
import de.scout24.financing.repository.CommentRepository;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.QuestionRepository;
import de.scout24.financing.resource.AnswerResource;
import de.scout24.financing.resource.CommentResource;
import de.scout24.financing.resource.QuestionResource;
import de.scout24.financing.resource.assembler.AnswerResourceAssembler;
import de.scout24.financing.resource.assembler.CommentResourceAssembler;
import de.scout24.financing.resource.assembler.QuestionResourceAssembler;
import de.scout24.financing.solr.repository.QuestionSolrRepository;

/**
 * Initially created by Ajit on 08.10.15.
 */
@Service
@Transactional
public class QuestionService extends BaseService {

    public QuestionResource getQuestion(long id) {
        return serviceHelper.questionResourceAssembler.toResource(serviceHelper.questionRepository.findOne(id));
    }

    public Iterable<Question> getQuestions() {
        return serviceHelper.questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        return serviceHelper.questionRepository.save(question);
    }

    public PagedResources<QuestionResource> getQuestionsPaged(Pageable p, PagedResourcesAssembler<Question> assembler) {
        Page<Question> questions = serviceHelper.questionRepository.findAll(p);
        return assembler.toResource(questions, serviceHelper.questionResourceAssembler);
    }

    public PagedResources<QuestionResource> getQuestionsByStatus(QuestionStatus questionStatus, Pageable p,
            PagedResourcesAssembler<Question> assembler) {
        Page<Question> questions = serviceHelper.questionRepository.findByQuestionStatusAndDeletedFalse(questionStatus, p);
        return assembler.toResource(questions, serviceHelper.questionResourceAssembler);
    }

    public PagedResources<QuestionResource> getQuestionsByPublishingStatus(PublishingStatus publishingStatus, Pageable p,
            PagedResourcesAssembler<Question> assembler) {
        Page<Question> questions = serviceHelper.questionRepository.findByPublishingStatusAndDeletedFalse(publishingStatus, p);
        return assembler.toResource(questions, serviceHelper.questionResourceAssembler);
    }

    public PagedResources<AnswerResource> getQuestionAnswers(long questionId, Pageable p, PagedResourcesAssembler<Answer> assembler) {
        Page<Answer> answers = serviceHelper.answerRepository.findByQuestionIdAndDeletedFalse(questionId, p);
        return assembler.toResource(answers, serviceHelper.answerResourceAssembler);
    }

    public PagedResources<CommentResource> getQuestionComments(long questionId, Pageable p, PagedResourcesAssembler<Comment> assembler) {
        Page<Comment> comments = serviceHelper.commentRepository.findByQuestionIdAndDeletedFalse(questionId, p);
        return assembler.toResource(comments, serviceHelper.commentResourceAssembler);
    }

    @PreAuthorize("@exfoUserDetailsService.canAccessUser(#id)")
    public PagedResources<QuestionResource> getQuestionsByForumUser(long id, Pageable p, PagedResourcesAssembler<Question> assembler) {
        Page<Question> questions = serviceHelper.questionRepository.findByForumUserAndDeletedFalse(serviceHelper.forumUserRepository.findOne(id), p);
        return assembler.toResource(questions, serviceHelper.questionResourceAssembler);
    }

    public PagedResources<QuestionResource> getFavoriteQuestionsByForumUser(long forumUserId, Pageable p, PagedResourcesAssembler<Question> assembler) {
        Page<Question> questions = serviceHelper.forumUserRepository.getFavoriteQuestionsById(forumUserId, p);
        return assembler.toResource(questions, serviceHelper.questionResourceAssembler);
    }

    //    NEW,    APPROVED,    SPAM,    REJECTED;
    public QuestionResource updateQuestionPublishingStatus(Question question) throws ExpertForumException {
        Question dbQuestion = serviceHelper.questionRepository.findOne(question.getId());
        dbQuestion.setPublishingStatus(question.getPublishingStatus());
        dbQuestion = serviceHelper.questionRepository.save(dbQuestion);

        switch (question.getPublishingStatus()) {
//            case APPROVED: question is visible immediately - @PM specification
//                notificationQueueService.queueNotification(NotificationEventType.QUESTION_CREATED, question);
//                break;
            case SPAM:
                serviceHelper.notificationQueueService.queueNotification(NotificationEventType.QUESTION_SPAMMED, question);
                break;
            case REJECTED:
                serviceHelper.notificationQueueService.queueNotification(NotificationEventType.QUESTION_REJECTED, question);
                break;
            default:
                break;
        }

        return serviceHelper.questionResourceAssembler.toResource(dbQuestion);
    }

    //    OPEN,    ANSWERED,    ANSWERED_HELPFULLY,    CLOSED;
    public QuestionResource updateQuestionStatus(Question question)  throws ExpertForumException {
        Question dbQuestion = serviceHelper.questionRepository.findOne(question.getId());
        dbQuestion.setQuestionStatus(question.getQuestionStatus());
        dbQuestion = serviceHelper.questionRepository.save(dbQuestion);

        switch (question.getQuestionStatus()) {
            case CLOSED:
                serviceHelper.notificationQueueService.queueNotification(NotificationEventType.QUESTION_CLOSED, question);
                break;
            default:
                break;
        }

        return serviceHelper.questionResourceAssembler.toResource(dbQuestion);
    }

    public void removeQuestionById(long id) {
        serviceHelper.questionSolrRepository.delete(id);
        serviceHelper.questionRepository.delete(id);
    }

    public QuestionResource markQuestionAsFavorite(long questionId) {
        Question question = serviceHelper.questionRepository.findOne(questionId);
        ForumUser forumUser = serviceHelper.forumUserRepository.findOne(serviceHelper.profileService.getLoggedInUser().getId());
        question.getUsersAsFavorite().add(forumUser);
        return serviceHelper.questionResourceAssembler.toResource(serviceHelper.questionRepository.save(question));
    }

    public QuestionResource unmarkQuestionAsFavorite(long questionId) {
        Question question = serviceHelper.questionRepository.findOne(questionId);
        ForumUser forumUser = serviceHelper.forumUserRepository.findOne(serviceHelper.profileService.getLoggedInUser().getId());
        question.getUsersAsFavorite().remove(forumUser);
        return serviceHelper.questionResourceAssembler.toResource(serviceHelper.questionRepository.save(question));
    }

    public boolean isMarkedAsFavorite(long questionId) {
        ForumUser forumUser = serviceHelper.forumUserRepository.findOne(serviceHelper.profileService.getLoggedInUser().getId());
        return (serviceHelper.questionRepository.countByIdAndUsersAsFavorite(questionId, forumUser) > 0);
    }

    @Secured("hasAnyAuthority('CREATE_QUESTIONS', 'EDIT_QUESTIONS')")
    public QuestionResource saveQuestion(Question question) throws ExpertForumException {
        if (question.getId() > 0) {
            NotificationEventType notificationEventType = NotificationEventType.QUESTION_UPDATED;
            question = serviceHelper.questionRepository.save(question);
            serviceHelper.notificationQueueService.queueNotification(notificationEventType, question);
        } else { //Save case
            question.setForumUserId(serviceHelper.profileService.getLoggedInUser().getId()); //id of user who is asking question
            question.setPublishingStatus(PublishingStatus.APPROVED);//as per @PM specs
            question = serviceHelper.questionRepository.save(question);
            serviceHelper.notificationQueueService.queueNotification(NotificationEventType.QUESTION_CREATED, question);
        }

        return serviceHelper.questionResourceAssembler.toResource(question);
    }
}
