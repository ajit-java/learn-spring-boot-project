package de.scout24.financing.domain;

import de.scout24.financing.domain.enums.DisplayNotificationStatus;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.domain.enums.ObjectType;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by ajit on 23.10.15.
 */
@Data
@Entity
public class DisplayNotification extends AbstractBaseDomain {

    /// id of the [user] the user who triggers the event.
    @Column(name = "TriggeredById")
    private long triggeredById;
    /// can be user / support user
    @Column(name = "TriggeredByType")
    @Enumerated(EnumType.STRING)
    private ObjectType triggeredByType;

    @Column(name = "TriggeredOnId")
    private long triggeredOnId;//always user id

    @Column(name = "TriggeredOnType")
    @Enumerated(EnumType.STRING)
    private ObjectType triggeredOnType; ////always user

    @Column(name = "NotificationEventType")
    @Enumerated(EnumType.STRING)
    private NotificationEventType notificationEventType;  // support_contact, q_created

    @Column(name = "DisplayNotificationStatus")
    @Enumerated(EnumType.STRING)
    private DisplayNotificationStatus displayNotificationStatus;

    @ManyToOne()
    @MapsId("ID")
    @JoinColumn(name = "TriggeredOnId")
    private ForumUser triggeredOnUser;

    @ManyToOne()
    @MapsId("ID")
    @JoinColumn(name = "TriggeredById")
    private ForumUser triggeredByUser;


    //user for whome this display notification is, or in whose profile this will be displayed.
    @Column(name = "ID_User")
    private long userId;

    @ManyToOne()
    @MapsId("ID")
    @JoinColumn(name = "ID_User")
    private ForumUser User;
}
