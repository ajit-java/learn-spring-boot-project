package de.scout24.financing.service.impl.notifications.processing;

import de.scout24.financing.domain.NotificationQueue;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class AnswerNotificationService extends BaseNotificationService {
    
    @Override
    public void handleNotifications(NotificationQueue notificationQueue) {
        super.notificationQueue = notificationQueue;
//        Question question = QuestionService
        switch (notificationQueue.getNotificationEventType()) {
            case ANSWER_COMMENTED_BY_USER:
                break;
        }
    }
}
