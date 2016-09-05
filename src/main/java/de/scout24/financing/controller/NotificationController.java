package de.scout24.financing.controller;

import de.scout24.financing.domain.DisplayNotification;
import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.enums.DisplayNotificationStatus;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.service.impl.ProfileService;
import de.scout24.financing.service.impl.notifications.DisplayNotificationService;
import de.scout24.financing.service.impl.notifications.NotificationQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ajit on 23.10.15.
 */
@RestController
public class NotificationController {
    @Autowired
    private NotificationQueueService notificationQueueService;
    @Autowired
    private DisplayNotificationService displayNotificationService;
    @Autowired
    protected ProfileService profileService;

    @Autowired
    protected ForumUserRepository forumUserRepository;

    //a support user is trying to send an email to a user for what ever reason.
    @RequestMapping(value = "/notifications/supportContact", method = RequestMethod.POST)
    public ResponseEntity<?> queueSupportContactNotification(@RequestBody NotificationQueue notificationQueue) throws ExpertForumException {
        notificationQueue.setNotificationEventType(NotificationEventType.SUPPORT_CONTACT);
        notificationQueueService.queueNotification(notificationQueue, forumUserRepository.findOne(notificationQueue.getTriggeredOnId()));// id of user

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    //a user is trying to contact an expert, send all contact data of user to expert
    @RequestMapping(value = "/notifications/expertContact", method = RequestMethod.POST)
    public ResponseEntity<?> queueExpertContactNotification(@RequestBody NotificationQueue notificationQueue) throws ExpertForumException {
        notificationQueue.setNotificationEventType(NotificationEventType.EXPERT_CONTACT);
        notificationQueueService.queueNotification(notificationQueue, forumUserRepository.findOne(notificationQueue.getTriggeredOnId()));//id of expert

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/forumUsers/displayNotificationsCount", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNotificationsCount() {
        return new ResponseEntity<>(displayNotificationService.getUserNotificationsCount(profileService.getLoggedInUser().getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/forumUsers/displayNotificationsAsUnSeen", method = RequestMethod.POST)
    public ResponseEntity<?> markDisplayNotificationsAsUnSeen() {
        return new ResponseEntity<>(displayNotificationService.markDisplayNotificationsAsUnSeen(profileService.getLoggedInUser().getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/notifications/{id}/displayNotificationSeen", method = RequestMethod.POST)
    public ResponseEntity<?> markDisplayNotificationsAsSeen(@RequestBody DisplayNotification displayNotification) {
        return new ResponseEntity<>(displayNotificationService.markDisplayNotificationsAsSeen(displayNotification), HttpStatus.OK);
    }



}
