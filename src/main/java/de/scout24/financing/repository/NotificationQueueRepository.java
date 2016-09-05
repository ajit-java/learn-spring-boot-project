package de.scout24.financing.repository;

import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.enums.NotificationStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by ajit on 23.10.15.
 */
public interface NotificationQueueRepository extends CrudRepository<NotificationQueue, Long> {
    //display
//    int countByTriggeredOnIdAndNotificationStatusAndNotificationEventTypeAndDeletedFalse(long senderUserId,
//                                        NotificationStatus notificationStatus, NotificationChannel notificationChannel);
//
//    @Modifying
//    @Query("update Notification n set n.notificationStatus = ?1 where n.triggeredOnId = ?2 and n.notificationStatus = ?3")
//    int updateDisplayNotifications(NotificationStatus newNotificationStatus, long triggeredOnId, NotificationStatus oldNotificationStatus );
//
//    @Modifying
//    @Query("update Notification n set n.notificationStatus = ?1 where n.triggeredOnId = ?2 and n.notificationStatus = 'NEW' and n.notificationStatus = 'SEEN' " +
//            "and n.triggeredOnType=?3 and n.domainObjectId=?4")
//    int updateDisplayNotifications(NotificationStatus newNotificationStatus, long triggeredOnId, String dmainObjectType, long domainObjectId);
//
//
//    NotificationStatus getNotificationStatusByTriggeredOnTypeAndDomainObjectIdAndDeletedFalse(String triggeredOnType, long domainObjectId);

//    @Query("select n* from NotificationQueue n where n.NotificationStatus=?1 order by n.createdAt")
    NotificationQueue getTop1ByNotificationStatusAndDeletedFalseOrderByCreatedAtDesc(NotificationStatus notificationStatus);
}
