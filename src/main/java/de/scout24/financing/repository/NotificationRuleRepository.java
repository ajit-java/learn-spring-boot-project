package de.scout24.financing.repository;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.RecipientType;
import de.scout24.financing.domain.enums.ExfoRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasRole('ROLE_USER')")
public interface NotificationRuleRepository extends PagingAndSortingRepository<NotificationRule, Long> {
    NotificationRule getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(NotificationEventType notificationEventType, ExfoRole exfoRole, RecipientType recipientType);
}
