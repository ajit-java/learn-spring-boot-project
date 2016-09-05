package de.scout24.financing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.domain.enums.DisplayNotificationStatus;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.domain.enums.NotificationChannel;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.domain.enums.ObjectType;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;
import de.scout24.financing.domain.enums.RecipientType;

/**
 * Created by ajit on 25.11.15.
 */
@RestController
public class EnumController {
    @RequestMapping(value = "/enums/recipientType", method = RequestMethod.GET)
    public RecipientType[] getRecipientType() {
        return RecipientType.values();
    }

    @RequestMapping(value = "/enums/exfoRole", method = RequestMethod.GET)
    public ExfoRole[] getRole() {
        return ExfoRole.values();
    }

    @RequestMapping(value = "/enums/displayNotificationStatus", method = RequestMethod.GET)
    public DisplayNotificationStatus[] getDisplayNotificationStatus() {
        return DisplayNotificationStatus.values();
    }

    @RequestMapping(value = "/enums/notificationChannel", method = RequestMethod.GET)
    public NotificationChannel[] getNotificationChannel() {
        return NotificationChannel.values();
    }

    @RequestMapping(value = "/enums/notificationEventType", method = RequestMethod.GET)
    public NotificationEventType[] getNotificationEventType() {
        return NotificationEventType.values();
    }

    @RequestMapping(value = "/enums/notificationStatus", method = RequestMethod.GET)
    public NotificationStatus[] getNotificationStatus() {
        return NotificationStatus.values();
    }

    @RequestMapping(value = "/enums/objectType", method = RequestMethod.GET)
    public ObjectType[] getObjectType() {
        return ObjectType.values();
    }

    @RequestMapping(value = "/enums/publishingStatus", method = RequestMethod.GET)
    public PublishingStatus[] getPublishingStatus() {
        return PublishingStatus.values();
    }

    @RequestMapping(value = "/enums/questionStatus", method = RequestMethod.GET)
    public QuestionStatus[] getQuestionStatus() {
        return QuestionStatus.values();
    }


}
