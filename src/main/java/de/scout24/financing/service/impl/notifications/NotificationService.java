//package de.scout24.financing.service.impl;
//
//import de.scout24.financing.domain.DisplayNotification;
//import de.scout24.financing.domain.ForumUser;
//import de.scout24.financing.domain.enums.DisplayNotificationStatus;
//import de.scout24.financing.domain.enums.ObjectType;
//import de.scout24.financing.repository.DisplayNotificationRepository;
//import de.scout24.financing.repository.ForumUserRepository;
//import de.scout24.financing.service.impl.notifications.processing.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.stereotype.Service;
//
///**
// * Created by ajit on 23.10.15.
// */
//@Service
//@Configurable
//public class NotificationService {
//    @Autowired
//    private DisplayNotificationRepository displayNotificationRepository;
//
//    @Autowired
//    private ForumUserRepository forumUserRepository;
//
//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private ProfileService profileService;
//
//    @Autowired
//    private FavoriteService favoriteService;
//
//    // USe case: click on bubble count on nav Flow: NEW -> UNSEEN -> SEEN
//    public int markDisplayNotificationsAsUnSeen(long receiverUserId) {
//        return displayNotificationRepository.updateDisplayNotifications(DisplayNotificationStatus.UNSEEN, receiverUserId, DisplayNotificationStatus.NEW);
//    }
//
//    // use case: question details view Flow: NEW -> UNSEEN -> SEEN
//    public int markDisplayNotificationsAsSeen(long receiverUserId, long domainObjectId, ObjectType domainObjectType) {
//        return displayNotificationRepository.updateDisplayNotifications(DisplayNotificationStatus.SEEN, receiverUserId, domainObjectType, domainObjectId);
//    }
//
//    private void processDisplayNotification(DisplayNotification displayNotification) {
//        //1. llo though users
//        displayNotification.setDisplayNotificationStatus(DisplayNotificationStatus.NEW);
//        displayNotificationRepository.save(displayNotification);
//    }
//    /*-------------------- Display notifications end --------------------*/
//    /*-------------------- Common Start--------------------*/
//    private ForumUser setupNotificationProperties(DisplayNotification displayNotification) {
//        ForumUser notificationReceiver = forumUserRepository.findOne(displayNotification.getTriggeredOnId());
//        displayNotification.setDisplayNotificationStatus(DisplayNotificationStatus.NEW);
//
//        return notificationReceiver;
//    }
//    /*-------------------- Common end --------------------*/
//}
