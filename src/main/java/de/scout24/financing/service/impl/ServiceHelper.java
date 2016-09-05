package de.scout24.financing.service.impl;

import de.scout24.financing.domain.DisplayNotification;
import de.scout24.financing.exception.ExceptionHandler;
import de.scout24.financing.repository.*;
import de.scout24.financing.resource.assembler.*;
import de.scout24.financing.service.impl.auth.ExfoUserDetailsService;
import de.scout24.financing.service.impl.notifications.DisplayNotificationService;
import de.scout24.financing.service.impl.notifications.NotificationQueueService;
import de.scout24.financing.service.impl.notifications.processing.*;
import de.scout24.financing.solr.repository.QuestionSolrRepository;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by ajit on 20.11.15.
 */
@Service
public class ServiceHelper {
    @Autowired
    public EmailService emailService;
    @Autowired
    public ForumUserService forumUserService;

    @Autowired
    public FavoriteService favoriteService;
    @Autowired
    public UserNotificationService userNotificationService;
    @Autowired
    public DisplayNotificationService displayNotificationService;
    @Autowired
    public ProfileService profileService;
    @Autowired
    public QuestionNotificationService questionNotificationService;
    @Autowired
    public NotificationQueueService notificationQueueService;
    @Autowired
    public AnswerNotificationService answerNotificationService;
    @Autowired
    public CommentNotificationService commentNotificationService;
    @Autowired
    public ExpertNotificationService expertNotificationService;
    @Autowired
    public RestService restService;
    @Autowired
    public ExfoUserDetailsService userDetailsService;

    @Autowired
    public DisplayNotificationRepository displayNotificationRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public QuestionRepository questionRepository;
    @Autowired
    public AnswerRepository answerRepository;
    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public ForumUserRepository forumUserRepository;
    @Autowired
    public NotificationQueueRepository notificationQueueRepository;
    @Autowired
    public QuestionSolrRepository questionSolrRepository;
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public NotificationRuleRepository notificationRuleRepository;
    @Autowired
    public RoleRepository roleRepository;


    @Autowired
    public ExceptionHandler exceptionHandler;

    @Autowired
    public PasswordEncoder encoder;
    
    @Autowired
    public MessageSource messageSource;

    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    public Configuration freemarkerConfiguration;

    @Autowired
    CategoryResourceAssembler categoryResourceAssembler;
    @Autowired
    public ForumUserResourceAssembler forumUserResourceAssembler;
    @Autowired
    public QuestionResourceAssembler questionResourceAssembler;
    @Autowired
    public AnswerResourceAssembler answerResourceAssembler;
    @Autowired
    public CommentResourceAssembler commentResourceAssembler;
    
    @Autowired
    public UserResourceAssembler userResourceAssembler;
}
