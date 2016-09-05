package de.scout24.financing.service.impl.notifications.processing;

import de.scout24.financing.domain.DisplayNotification;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.enums.DisplayNotificationStatus;
import de.scout24.financing.domain.enums.RecipientType;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.NotificationQueueRepository;
import de.scout24.financing.service.impl.FavoriteService;

import java.util.List;

public abstract class BaseNotificationService extends BaseService {
    protected NotificationQueue notificationQueue;

    public abstract void handleNotifications(NotificationQueue notificationQueue) throws ExpertForumException;

    protected void saveDisplayNotifications(NotificationRule notificationRule, NotificationQueue notificationQueue, List<ForumUser> notificationReceivers) {
        if (notificationRule != null && notificationRule.isDisplay()) {
            for (ForumUser notificationReceiver : notificationReceivers) {
                saveDisplayNotification(notificationRule, notificationReceiver);
            }
        }
    }

    protected void saveDisplayNotification(NotificationRule notificationRule, ForumUser notificationReceiver) {
        if (notificationRule != null && notificationRule.isDisplay()) {
            DisplayNotification displayNotification = new DisplayNotification() ;
            displayNotification.setTriggeredById(notificationQueue.getTriggeredById());
            displayNotification.setTriggeredByType(notificationQueue.getTriggeredByType());
            displayNotification.setNotificationEventType(notificationQueue.getNotificationEventType());
            displayNotification.setTriggeredOnType(notificationQueue.getTriggeredOnType());
            displayNotification.setTriggeredOnId(notificationQueue.getTriggeredOnId());
            displayNotification.setDisplayNotificationStatus(DisplayNotificationStatus.NEW);
            displayNotification.setUserId(notificationReceiver.getId());//user for whome this display notification is, or in whose profile this will be displayed.

            serviceHelper.displayNotificationService.save(displayNotification);
        }
    }

    protected void sendEmailNotifications(NotificationRule notificationRule, List<ForumUser> notificationReceivers) throws ExpertForumException {
        if (notificationRule != null && notificationRule.isMail()) {
            for (ForumUser notificationReceiver : notificationReceivers) {
                sendEmailNotification(notificationRule, notificationReceiver);
            }
        }
    }

    protected void sendEmailNotification(NotificationRule notificationRule, ForumUser notificationReceiver) throws ExpertForumException {
        if (notificationRule != null && notificationRule.isMail()) {
            serviceHelper.emailService.notifyUser(notificationReceiver, notificationRule.getMailSubject(), notificationRule.getMailText(), notificationQueue.getTriggeredOnWebUrl());
        }
    }

    protected NotificationRule getNotificationRule(ExfoRole role, RecipientType recipientType) {
        return serviceHelper.notificationRuleRepository.getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(
                notificationQueue.getNotificationEventType(), role, recipientType);
    }
}
