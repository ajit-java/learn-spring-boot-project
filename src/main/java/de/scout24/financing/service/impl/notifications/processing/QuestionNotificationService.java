package de.scout24.financing.service.impl.notifications.processing;

import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.domain.enums.RecipientType;
import de.scout24.financing.exception.ExpertForumException;

/**
 * Created by ajit on 06.11.15.
 */

@Service
public class QuestionNotificationService extends BaseNotificationService {

    @Override
    public void handleNotifications(NotificationQueue notificationQueue) throws ExpertForumException {
        super.notificationQueue = notificationQueue;
        switch (notificationQueue.getNotificationEventType()) {
            case QUESTION_CREATED:
                notifyInterestedExperts();
                notifyInitiator();
                notifySupportUser();
                break;
            case QUESTION_UPDATED:
                throw new NotYetImplementedException();
            case QUESTION_CLOSED:
                notifyInitiator();
                notifyAllExpertsWhoAnsweredTheQuestion();
                break;
            case QUESTION_REJECTED:
                notifyInitiator();
                break;
            case QUESTION_SPAMMED:
                notifyInitiator();
                break;
            default:
                throw new NotYetImplementedException();
        }
    }

    //interested experts: in whose filter criteria this question falls, filter: category,PLZ+distance+who in general has enabled notifications,
    private void notifyInterestedExperts() throws ExpertForumException {
        List<ForumUser> expertsToNotify = serviceHelper.forumUserService.getAllExperts();//todo get experts from solr base on question category, zipcode and role=expert
        NotificationRule notificationRule = getNotificationRule(ExfoRole.EXPERT_APPROVED, RecipientType.EXPERT_INTERESTED);
        super.saveDisplayNotifications(notificationRule, notificationQueue, expertsToNotify);
        super.sendEmailNotifications(notificationRule, expertsToNotify);
    }

    //notifying user who has asked question that his question now live.
    private void notifyInitiator() throws ExpertForumException {
        ForumUser notificationReceiver = serviceHelper.forumUserRepository.findOne(notificationQueue.getTriggeredById());
        NotificationRule notificationRule = getNotificationRule(ExfoRole.USER, RecipientType.THREAD_INITIATOR);
        super.saveDisplayNotification(notificationRule, notificationReceiver);
        super.sendEmailNotification(notificationRule, notificationReceiver);
    }

    //notify all support users that there is a new question
    private void notifySupportUser() throws ExpertForumException {
        List<ForumUser> notificationReceivers = serviceHelper.forumUserRepository.getByRoleAndDeletedFalse(serviceHelper.roleRepository.getByName(ExfoRole.SUPPORT));
        NotificationRule notificationRule = getNotificationRule(ExfoRole.USER, RecipientType.SUPPORT_USER);
        super.saveDisplayNotifications(notificationRule, notificationQueue, notificationReceivers);
        super.sendEmailNotifications(notificationRule, notificationReceivers);
    }

    private void notifyAllExpertsWhoAnsweredTheQuestion()  throws ExpertForumException {
        List<ForumUser> expertsToNotify = serviceHelper.answerRepository.getByQuestionIdAndDeletedFalse(notificationQueue.getTriggeredOnId());
        NotificationRule notificationRule = getNotificationRule(ExfoRole.EXPERT_APPROVED, RecipientType.EXPERT_OTHERS);
        super.saveDisplayNotifications(notificationRule, notificationQueue, expertsToNotify);
        super.sendEmailNotifications(notificationRule, expertsToNotify);
    }
}

