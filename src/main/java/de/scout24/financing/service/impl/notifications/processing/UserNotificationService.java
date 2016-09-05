package de.scout24.financing.service.impl.notifications.processing;

import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.exception.ExpertForumException;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService  extends BaseNotificationService {

    @Override
    public void handleNotifications(NotificationQueue notificationQueue) throws ExpertForumException {
        super.notificationQueue = notificationQueue;
        switch (notificationQueue.getNotificationEventType()) {
            case SUPPORT_CONTACT:
                serviceHelper.emailService.sendContactEmail(notificationQueue);
                break;
            default:
                break;
        }
    }
}
