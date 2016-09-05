package de.scout24.financing.service.impl.notifications;

import de.scout24.financing.domain.*;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.domain.enums.ObjectType;
import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.scout24.financing.exception.ExpertForumException;

/**
 * Created by ajit on 23.10.15.
 */
@Service
public class NotificationQueueService<T extends AbstractBaseDomain> extends BaseService {

    @Value("${exfo.baseUrl}")
    private String baseUrl;

    public void queueNotification(NotificationEventType notificationEventType, T triggeredOnDomainObject) throws ExpertForumException {
        NotificationQueue notificationQueue = new NotificationQueue();
        notificationQueue.setNotificationEventType(notificationEventType);
        notificationQueue.setTriggeredOnId(triggeredOnDomainObject.getId());

        queueNotification(notificationQueue, triggeredOnDomainObject);
    }

    public void queueNotification(NotificationQueue notificationQueue, T triggeredOnDomainObject) throws ExpertForumException {
        try {
            notificationQueue.setNotificationStatus(NotificationStatus.NEW);
            switch (notificationQueue.getNotificationEventType()) {
                case QUESTION_CREATED:
                case QUESTION_CLOSED:
                case QUESTION_UPDATED:
                case QUESTION_REJECTED:
                case QUESTION_SPAMMED:
                    queueQuestionNotification(notificationQueue, (Question) triggeredOnDomainObject);
                    break;
                case ANSWER_CREATED:
                case ANSWER_UPDATED:
                    queueAnswerNotification(notificationQueue, (Answer) triggeredOnDomainObject);
                    break;
                case ANSWER_COMMENTED_BY_EXPERT:
                case ANSWER_COMMENTED_BY_USER:
                    queueCommentNotification(notificationQueue, (Comment) triggeredOnDomainObject);
                    break;
                case SUPPORT_CONTACT:
                    queueUserNotification(notificationQueue, (ForumUser) triggeredOnDomainObject);
                    break;
                case EXPERT_CONTACT:
                    queueExpertNotification(notificationQueue, (ForumUser) triggeredOnDomainObject);
                    break;
                default:
                    throw new Exception("Unable to queue notification, Invalid or not implemented NotificationEventType.");
            }

            serviceHelper.notificationQueueRepository.save(notificationQueue);

        } catch (Exception ex) {
            throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.INTERNAL_ERROR, ex, notificationQueue.toString());
        }
    }

    private void queueQuestionNotification(NotificationQueue notificationQueue, Question question) {
        notificationQueue.setTriggeredOnType(ObjectType.QUESTION);
        notificationQueue.setTriggeredOnWebUrl(baseUrl + "#/questions/" + question.getId() + "/details");

        notificationQueue.setTriggeredByType(ObjectType.USER);
        notificationQueue.setTriggeredById(serviceHelper.profileService.getLoggedInUser().getId());
    }

    private void queueAnswerNotification(NotificationQueue notificationQueue, Answer answer) {
        notificationQueue.setTriggeredOnType(ObjectType.ANSWER);
        notificationQueue.setTriggeredOnWebUrl(baseUrl + "#/questions/" + answer.getQuestionId() + "/details");

        notificationQueue.setTriggeredByType(ObjectType.EXPERT_USER);
        notificationQueue.setTriggeredById(serviceHelper.profileService.getLoggedInUser().getId());
    }

    private void queueCommentNotification(NotificationQueue notificationQueue, Comment comment) {
        notificationQueue.setTriggeredOnType(ObjectType.COMMENT);
        notificationQueue.setTriggeredOnWebUrl(baseUrl + "#/questions/" + comment.getQuestionId() + "/details");

        notificationQueue.setTriggeredByType(notificationQueue.getNotificationEventType() == NotificationEventType.ANSWER_COMMENTED_BY_EXPERT
                ? ObjectType.EXPERT_USER : ObjectType.USER);
        notificationQueue.setTriggeredById(serviceHelper.profileService.getLoggedInUser().getId());
    }

    //forum is not used but for future use as the use cases are more specified and requirements become more clear.
    private void queueUserNotification(NotificationQueue notificationQueue, ForumUser forumUser) {
        notificationQueue.setTriggeredOnType(ObjectType.USER);
        notificationQueue.setTriggeredOnWebUrl(baseUrl + "#/profile/user");

        notificationQueue.setTriggeredByType(ObjectType.SUPPORT_USER);
        notificationQueue.setTriggeredById(serviceHelper.profileService.getLoggedInUser().getId());
    }

    //forum is not used but for future use as the use cases are more specified and requirements become more clear.
    private void queueExpertNotification(NotificationQueue notificationQueue, ForumUser forumUser) {
        notificationQueue.setTriggeredOnType(ObjectType.EXPERT_USER);
        notificationQueue.setTriggeredOnWebUrl(baseUrl + "#/profile/expert");

        notificationQueue.setTriggeredByType(ObjectType.USER);
        notificationQueue.setTriggeredById(serviceHelper.profileService.getLoggedInUser().getId());
    }
}
