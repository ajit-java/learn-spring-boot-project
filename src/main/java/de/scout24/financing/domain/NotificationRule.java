package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.RecipientType;
import lombok.Data;

/**
 * Created by ajit on 23.10.15.
 */
@Data
@Entity
public class NotificationRule extends AbstractBaseDomain {
    @Column(name = "RecipientType")
    @Enumerated(EnumType.STRING)
    private RecipientType recipientType;

    @Column(name = "ExfoRole")
    @Enumerated(EnumType.STRING)
    private ExfoRole exfoRole;

    @Column(name = "Mail")
    private boolean mail;

    @Column(name = "Push")
    private boolean push;

    @Column(name = "Display")
    private boolean display;

    @Column(name = "MailSubject")
    private String mailSubject;

    @Column(name = "MailText")
    @Type(type = "text")
    private String mailText;

    // null for now as there is no display notification text but just the count e,g. {{user}} commented on Question Title
    @Column(name = "DisplayNotificationText")
    private String displayNotificationText;

    @Column(name = "NotificationEventType")
    @Enumerated(EnumType.STRING)
    private NotificationEventType notificationEventType;  // support_contact, q_created
}
