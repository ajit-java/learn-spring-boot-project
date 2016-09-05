package de.scout24.financing.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.domain.enums.NotificationStatus;
import de.scout24.financing.domain.enums.ObjectType;
import de.scout24.financing.exception.ExceptionHandler;
import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.NotificationQueueRepository;
import de.scout24.financing.service.impl.ServiceHelper;
import de.scout24.financing.service.impl.notifications.processing.AnswerNotificationService;
import de.scout24.financing.service.impl.notifications.processing.BaseNotificationService;
import de.scout24.financing.service.impl.notifications.processing.CommentNotificationService;
import de.scout24.financing.service.impl.notifications.processing.ExpertNotificationService;
import de.scout24.financing.service.impl.notifications.processing.NotificationProcessingService;
import de.scout24.financing.service.impl.notifications.processing.QuestionNotificationService;
import de.scout24.financing.service.impl.notifications.processing.UserNotificationService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NotificationProcessingService.class)
public class NotificationProcessingServiceTest {

    @Mock
    private BaseNotificationService notificationService;
    
    @Mock
    private ServiceHelper serviceHelper;
    
    @Spy
    private NotificationQueue notificationQueue = new NotificationQueue();
    
    @InjectMocks
    private NotificationProcessingService notificationProcessingService;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        serviceHelper.notificationQueueRepository = Mockito.mock(NotificationQueueRepository.class);
        Mockito.when(serviceHelper.notificationQueueRepository.save(Mockito.any(NotificationQueue.class))).thenReturn(notificationQueue);
        Mockito.when(serviceHelper.notificationQueueRepository.getTop1ByNotificationStatusAndDeletedFalseOrderByCreatedAtDesc(
                Mockito.any(NotificationStatus.class))).thenReturn(notificationQueue);
        // TODO stub notificationHandler.handleNotifications(notificationQueue)
    }
    
    @Test
    public void testProcessNotificationOnQuestion() throws Exception {
        serviceHelper.questionNotificationService = Mockito.mock(QuestionNotificationService.class);
        notificationQueue.setTriggeredOnType(ObjectType.QUESTION);
        notificationProcessingService.processNotification();
        Assert.assertEquals(NotificationStatus.PROCESSED, notificationQueue.getNotificationStatus());
        Mockito.verify(serviceHelper.questionNotificationService);
    }

    @Test
    public void testProcessNotificationOnAnswer() throws Exception {
        serviceHelper.answerNotificationService = Mockito.mock(AnswerNotificationService.class);
        notificationQueue.setTriggeredOnType(ObjectType.ANSWER);
        notificationProcessingService.processNotification();
        Assert.assertEquals(NotificationStatus.PROCESSED, notificationQueue.getNotificationStatus());
        Mockito.verify(serviceHelper.answerNotificationService);
    }

    @Test
    public void testProcessNotificationOnComment() throws Exception {
        serviceHelper.commentNotificationService = Mockito.mock(CommentNotificationService.class);
        notificationQueue.setTriggeredOnType(ObjectType.COMMENT);
        notificationProcessingService.processNotification();
        Assert.assertEquals(NotificationStatus.PROCESSED, notificationQueue.getNotificationStatus());
        Mockito.verify(serviceHelper.commentNotificationService);
    }

    @Test
    public void testProcessNotificationOnUser() throws Exception {
        serviceHelper.userNotificationService = Mockito.mock(UserNotificationService.class);
        notificationQueue.setTriggeredOnType(ObjectType.USER);
        notificationProcessingService.processNotification();
        Assert.assertEquals(NotificationStatus.PROCESSED, notificationQueue.getNotificationStatus());
        Mockito.verify(serviceHelper.userNotificationService);
    }

    @Test
    public void testProcessNotificationOnExpert() throws Exception {
        serviceHelper.expertNotificationService = Mockito.mock(ExpertNotificationService.class);
        notificationQueue.setTriggeredOnType(ObjectType.EXPERT_USER);
        notificationProcessingService.processNotification();
        Assert.assertEquals(NotificationStatus.PROCESSED, notificationQueue.getNotificationStatus());
        Mockito.verify(serviceHelper.expertNotificationService);
    }

    @Test(expected = ExpertForumException.class)
    public void testProcessNotificationOnInvalidType() throws Exception {
        serviceHelper.exceptionHandler = Mockito.mock(ExceptionHandler.class);
        Mockito.when(serviceHelper.exceptionHandler.handle(Mockito.any(NotificationProcessingService.class), Mockito.any(ExceptionKey.class), 
                Mockito.any(Exception.class), Mockito.anyString())).thenReturn(new ExpertForumException());
        notificationQueue.setTriggeredOnType(ObjectType.SUPPORT_USER);
        notificationProcessingService.processNotification();
    }

}
