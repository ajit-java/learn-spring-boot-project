package de.scout24.financing.service.impl.notifications;

import de.scout24.financing.domain.DisplayNotification;
import de.scout24.financing.domain.enums.DisplayNotificationStatus;
import de.scout24.financing.repository.DisplayNotificationRepository;
import de.scout24.financing.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ajit on 20.11.15.
 */
@Service
public class DisplayNotificationService extends BaseService {

//     USe case: click on bubble count on nav Flow: NEW -> UNSEEN -> SEEN
    public int markDisplayNotificationsAsUnSeen(long userId) {
        return serviceHelper.displayNotificationRepository.updateDisplayNotifications(DisplayNotificationStatus.UNSEEN, userId, DisplayNotificationStatus.NEW);
    }

    // use case: question details view Flow: NEW -> UNSEEN -> SEEN
    public int markDisplayNotificationsAsSeen(DisplayNotification displayNotification) {
        return serviceHelper.displayNotificationRepository.updateDisplayNotifications(DisplayNotificationStatus.SEEN, displayNotification.getUserId(),
                displayNotification.getTriggeredOnType(),displayNotification.getTriggeredOnId());
    }

    public int getUserNotificationsCount(long userId)
    {
        return serviceHelper.displayNotificationRepository.countByUserIdAndDisplayNotificationStatusAndDeletedFalse(userId, DisplayNotificationStatus.NEW);
    }

    public DisplayNotification save(DisplayNotification displayNotification) {
        return serviceHelper.displayNotificationRepository.save(displayNotification);
    }

}
