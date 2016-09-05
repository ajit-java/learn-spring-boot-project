package de.scout24.financing.service.impl.notifications.processing;

import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.service.impl.BaseService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by ajit on 13.11.15.
 */
@Service
public class NotificationProcessingService extends BaseService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(NotificationProcessingService.class);

    @Scheduled(initialDelay=50000, fixedDelay = 5000)//TODO @VALUE
    // @Profile("notificationService") //enable profiles in case a standalone instance of notification processor is needed.
    public void processNotification() throws ExpertForumException {
        BaseNotificationService notificationHandler = null;
//        LOGGER.debug("Entering notification scheduler: " + DateTime.now());
//        System.out.println("Entering notification scheduler.. : " + DateTime.now());
        NotificationQueue notificationQueue = getNextNotification();
        if (notificationQueue != null) {
            try {
                switch (notificationQueue.getTriggeredOnType()) {
                    case QUESTION:
                        notificationHandler = serviceHelper.questionNotificationService;
                        break;
                    case ANSWER:
                        notificationHandler = serviceHelper.answerNotificationService;
                        break;
                    case COMMENT:
                        notificationHandler = serviceHelper.commentNotificationService;
                        break;
                    case USER:
                        notificationHandler = serviceHelper.userNotificationService;
                        break;
                    case EXPERT_USER:
                        notificationHandler = serviceHelper.expertNotificationService;
                        break;
                    default:
                    case SUPPORT_USER:
                    case NOTICE:
                        throw new Exception("Invalid or not implemented TriggeredOnType.");
                }
            } catch (Exception ex) {
                throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.INTERNAL_ERROR, ex, notificationQueue.toString());
            }
            notificationHandler.handleNotifications(notificationQueue);
            notificationQueue.setNotificationStatus(NotificationStatus.PROCESSED);
            serviceHelper.notificationQueueRepository.save(notificationQueue);
        }
    }

    @Transactional
    private NotificationQueue getNextNotification()
    {//read one row and change the status to processing
        NotificationQueue notificationQueue = serviceHelper.notificationQueueRepository.getTop1ByNotificationStatusAndDeletedFalseOrderByCreatedAtDesc(NotificationStatus.NEW);
        if(notificationQueue != null) {
            notificationQueue.setNotificationStatus(NotificationStatus.PROCESSING);
            return serviceHelper.notificationQueueRepository.save(notificationQueue);
        }

        return null;
    }
}
