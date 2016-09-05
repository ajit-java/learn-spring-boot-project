package de.scout24.financing.service.impl;

import javax.transaction.Transactional;

import de.scout24.financing.service.impl.notifications.NotificationQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.Question;
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
import de.scout24.financing.resource.assembler.AnswerResourceAssembler;
import de.scout24.financing.resource.assembler.CommentResourceAssembler;

/**
 * Initially created by Ajit on 08.10.15.
 */
@Service
@Transactional
public class AnswerService extends BaseService {

    public Resource<Answer> getAnswer(long id) {
        return serviceHelper.answerResourceAssembler.toResource(serviceHelper.answerRepository.findOne(id));
    }

    public Iterable<Answer> getAnswers() {
        return serviceHelper.answerRepository.findAll();
    }

    public PagedResources<AnswerResource> getAnswersPaged(Pageable p, PagedResourcesAssembler<Answer> assembler) {
        Page<Answer> answers = serviceHelper.answerRepository.findAll(p);
        return assembler.toResource(answers, serviceHelper.answerResourceAssembler);
    }

    public PagedResources<AnswerResource> getAnswersByStatus(PublishingStatus publishingStatus, Pageable p, PagedResourcesAssembler<Answer> assembler) {
        Page<Answer> answers = serviceHelper.answerRepository.findByPublishingStatusAndDeletedFalse(publishingStatus, p);

        return assembler.toResource(answers, serviceHelper.answerResourceAssembler);
    }

    public PagedResources<CommentResource> getAnswerComments(long answerId, Pageable p, PagedResourcesAssembler<Comment> assembler) {
        Page<Comment> comments = serviceHelper.commentRepository.findByAnswerIdAndDeletedFalse(answerId, p);

        return assembler.toResource(comments, serviceHelper.commentResourceAssembler);
    }

    public PagedResources<AnswerResource> getAnswersByForumUser(long id,Pageable p, PagedResourcesAssembler<Answer> assembler) {
        Page<Answer> answers = serviceHelper.answerRepository.findByForumUserAndDeletedFalse(serviceHelper.forumUserRepository.findOne(id), p);

        return assembler.toResource(answers, serviceHelper.answerResourceAssembler);
    }

    public AnswerResource saveAnswer(Answer answer) throws ExpertForumException {
        NotificationEventType notificationEventType = NotificationEventType.ANSWER_CREATED;
        if (answer.getId() > 0) {
            notificationEventType = NotificationEventType.ANSWER_UPDATED;
        } else { //Save case
            answer.setForumUserId(serviceHelper.profileService.getLoggedInUser().getId());
            Question question =  serviceHelper.questionRepository.findOne(answer.getQuestionId());
            question.setQuestionStatus(QuestionStatus.ANSWERED);
            serviceHelper.questionRepository.save(question);
        }
        serviceHelper.answerRepository.save(answer);

        serviceHelper.notificationQueueService.queueNotification(notificationEventType, answer);

        return serviceHelper.answerResourceAssembler.toResource(answer);
    }
}
