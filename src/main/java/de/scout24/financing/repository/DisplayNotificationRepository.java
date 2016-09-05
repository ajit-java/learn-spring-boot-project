package de.scout24.financing.repository;

import de.scout24.financing.domain.DisplayNotification;
import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.enums.DisplayNotificationStatus;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.domain.enums.ObjectType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;


/**
 * Created by ajit on 23.10.15.
 */

public interface DisplayNotificationRepository extends CrudRepository<DisplayNotification, Long> {

    //use case: to show count on bömmel icon
    int countByUserIdAndDisplayNotificationStatusAndDeletedFalse(long userId, DisplayNotificationStatus displayNotificationStatus);

    //e.g. user click on count bömmel icon
    @Modifying
    @Query("update DisplayNotification n set n.displayNotificationStatus = ?1 where n.userId = ?2 and n.displayNotificationStatus = ?3")
    int updateDisplayNotifications(DisplayNotificationStatus newDisplayNotificationStatus, long userId, DisplayNotificationStatus oldDisplayNotificationStatus );


    //e.g. user goes to detail view of a question, on which there was a banderolle was displayed
    @Modifying
    @Query("update DisplayNotification n set n.displayNotificationStatus = ?1 where n.userId = ?2 and n.displayNotificationStatus = 'NEW' and n.displayNotificationStatus = 'SEEN' " +
            "and n.triggeredOnType=?3 and n.triggeredOnId=?4")
    int updateDisplayNotifications(DisplayNotificationStatus newDisplayNotificationStatus, long userId, ObjectType triggeredOnType, long triggeredOnId);

//    NotificationStatus getNotificationStatusByTriggeredOnTypeAndDomainObjectIdAndDeletedFalse(String triggeredOnType, long domainObjectId);

}
