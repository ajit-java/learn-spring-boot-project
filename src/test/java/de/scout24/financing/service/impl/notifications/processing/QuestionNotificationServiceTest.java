package de.scout24.financing.service.impl.notifications.processing;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.scout24.financing.domain.DisplayNotification;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.RecipientType;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.AnswerRepository;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.NotificationRuleRepository;
import de.scout24.financing.repository.RoleRepository;
import de.scout24.financing.service.impl.ForumUserService;
import de.scout24.financing.service.impl.ProfileService;
import de.scout24.financing.service.impl.ServiceHelper;
import de.scout24.financing.service.impl.notifications.DisplayNotificationService;

@RunWith(MockitoJUnitRunner.class)
public class QuestionNotificationServiceTest {

    @Mock
    private BaseNotificationService baseNotificationService;
    
    @Mock
    private ServiceHelper serviceHelper;
    
    @InjectMocks
    private QuestionNotificationService questionNotificationService;
    
    @Before
    public void setup() throws ExpertForumException {
        serviceHelper.profileService = Mockito.mock(ProfileService.class);
        serviceHelper.notificationRuleRepository = Mockito.mock(NotificationRuleRepository.class);
        serviceHelper.forumUserRepository = Mockito.mock(ForumUserRepository.class);
        serviceHelper.roleRepository = Mockito.mock(RoleRepository.class);
        serviceHelper.displayNotificationService = Mockito.mock(DisplayNotificationService.class);
        serviceHelper.answerRepository = Mockito.mock(AnswerRepository.class);
        serviceHelper.forumUserService = Mockito.mock(ForumUserService.class);
        Mockito.doNothing().when(baseNotificationService)
                .sendEmailNotifications(Mockito.any(NotificationRule.class), Mockito.anyListOf(ForumUser.class));
        List<ForumUser> users = new ArrayList<>();
        Mockito.when(serviceHelper.forumUserService.getAllExperts()).thenReturn(users);
        NotificationRule notificationRule = new NotificationRule();
        Mockito.when(serviceHelper.notificationRuleRepository.getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(
                Mockito.any(NotificationEventType.class), Mockito.any(ExfoRole.class), Mockito.any(RecipientType.class))).thenReturn(notificationRule);
        DisplayNotification displayNotification = new DisplayNotification();
        Mockito.when(serviceHelper.displayNotificationService.save(Mockito.any(DisplayNotification.class))).thenReturn(displayNotification);
        Mockito.when(serviceHelper.answerRepository.getByQuestionIdAndDeletedFalse(Mockito.anyLong())).thenReturn(users);
    }
    
    @Test
    public void testHandleNotificationOnQuestionCreated() throws ExpertForumException {
        NotificationQueue notificationQueue = new NotificationQueue();
        notificationQueue.setNotificationEventType(NotificationEventType.QUESTION_CREATED);
        questionNotificationService.handleNotifications(notificationQueue);
        Mockito.verify(serviceHelper.notificationRuleRepository).getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(
                NotificationEventType.QUESTION_CREATED, ExfoRole.EXPERT_APPROVED, RecipientType.EXPERT_INTERESTED);
    }

    @Test
    public void testHandleNotificationOnQuestionClosed() throws ExpertForumException {
        NotificationQueue notificationQueue = new NotificationQueue();
        notificationQueue.setNotificationEventType(NotificationEventType.QUESTION_CLOSED);
        questionNotificationService.handleNotifications(notificationQueue);
        Mockito.verify(serviceHelper.notificationRuleRepository).getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(
                NotificationEventType.QUESTION_CLOSED, ExfoRole.USER, RecipientType.THREAD_INITIATOR);
    }

    @Test
    public void testHandleNotificationOnQuestionRejected() throws ExpertForumException {
        NotificationQueue notificationQueue = new NotificationQueue();
        notificationQueue.setNotificationEventType(NotificationEventType.QUESTION_REJECTED);
        questionNotificationService.handleNotifications(notificationQueue);
        Mockito.verify(serviceHelper.notificationRuleRepository).getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(
                NotificationEventType.QUESTION_REJECTED, ExfoRole.USER, RecipientType.THREAD_INITIATOR);
    }

    @Test
    public void testHandleNotificationOnQuestionSpammed() throws ExpertForumException {
        NotificationQueue notificationQueue = new NotificationQueue();
        notificationQueue.setNotificationEventType(NotificationEventType.QUESTION_SPAMMED);
        questionNotificationService.handleNotifications(notificationQueue);
        Mockito.verify(serviceHelper.notificationRuleRepository).getByNotificationEventTypeAndExfoRoleAndRecipientTypeAndDeletedFalse(
                NotificationEventType.QUESTION_SPAMMED, ExfoRole.USER, RecipientType.THREAD_INITIATOR);
    }

    @Test(expected = NotYetImplementedException.class)
    public void testHandleNotificationOnQuestionUpdated() throws ExpertForumException {
        NotificationQueue notificationQueue = new NotificationQueue();
        notificationQueue.setNotificationEventType(NotificationEventType.QUESTION_UPDATED);
        questionNotificationService.handleNotifications(notificationQueue);
    }
}
