package de.scout24.financing.domain;

import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.domain.enums.NotificationChannel;
import de.scout24.financing.domain.enums.ObjectType;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ajit on 23.10.15.
 */
@Data
@Entity
public class NotificationQueue extends AbstractBaseDomain {

    /// id of the [user] the user who triggers the event.
    @Column(name = "TriggeredById")
    private long triggeredById;
    /// can be user / support user
    @Column(name = "TriggeredByType")
    @Enumerated(EnumType.STRING)
    private ObjectType triggeredByType;

    /// Web URL of the user who triggered the event.
    @Column(name = "TriggeredByWebUrl")
    private String triggeredByWebUrl;


    @Column(name = "TriggeredOnId")
    private long triggeredOnId;

    @Column(name = "TriggeredOnType")
    @Enumerated(EnumType.STRING)
    private ObjectType triggeredOnType; //QUESTION,ANSWER, COMMENT,USER,EXPERT,

    @Column(name = "TriggeredOnWebUrl")
    private String triggeredOnWebUrl;

    @Column(name = "NotificationEventType")
    @Enumerated(EnumType.STRING)
    private NotificationEventType notificationEventType;  // support_contact, q_created

    @Column(name = "NotificationText")
    @Type(type = "text")
    private String notificationText;

    @Column(name = "NotificationSubject")
    private String notificationSubject;

    @Column(name = "NotificationStatus")
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

//    @ManyToOne()
//    @MapsId("ID")
//    @JoinColumn(name = "TriggeredOnId")
//    private ForumUser triggeredOnUser;
//
//    @ManyToOne()
//    @MapsId("ID")
//    @JoinColumn(name = "TriggeredById")
//    private ForumUser triggeredByUser;
}
