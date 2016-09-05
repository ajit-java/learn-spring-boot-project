package de.scout24.financing.service.impl.notifications.processing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import de.scout24.financing.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.NotificationQueue;
import de.scout24.financing.exception.ExceptionHandler;
import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.exception.ExpertForumException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class EmailService extends BaseService {

    @Value("${exfo.mail.from}")
    private String notificationEmailFrom;

    @Value("${exfo.mail.subjectprefix}")
    private String subjectPrefix;

    public void sendContactEmail(NotificationQueue notificationQueue) throws ExpertForumException {
        String ftlTemplate = "emails/mailTemplateHtml.ftl";
        Map<String, String> emailModel = getMailModel(serviceHelper.forumUserService.findOne(notificationQueue.getTriggeredOnId()));
        emailModel.put("emailText", notificationQueue.getNotificationText());

        getTemplateTextAndSendEmail(serviceHelper.forumUserService.findOne(notificationQueue.getTriggeredOnId()), ftlTemplate, emailModel, notificationQueue.getNotificationSubject());
    }

    public void notifyUser(ForumUser notificationReceiver, String emailSubject, String emailText, String triggeredOnWebUrl)  throws ExpertForumException {
        String ftlTemplate = "emails/mailTemplateHtml.ftl";
        Map<String, String> emailModel = getMailModel(notificationReceiver);
        emailModel.put("emailText", emailText);
        emailModel.put("linkText", triggeredOnWebUrl);
        getTemplateTextAndSendEmail(notificationReceiver, ftlTemplate, emailModel, emailSubject);
    }

//    public void sendQuestionThreadUpdateEmailNotification(NotificationQueue notificationQueue, ForumUser notificationReceiver) throws ExpertForumException {
//        String ftlTemplate = "emails/questionThreadUpdated.ftl";
//        Map<String, String> emailModel = new HashMap<String, String>();
//        emailModel.put("userName", notificationReceiver.getLastName());
//        emailModel.put("emailText", notificationQueue.getNotificationText());
//
//        getTemplateTextAndSendEmail(notificationQueue, ftlTemplate, emailModel);
//    }

    private Map<String, String> getMailModel(ForumUser notificationReceiver) {
        Map<String, String> emailModel = new HashMap<String, String>();

        String gender = notificationReceiver.getGender();
        String lastName = notificationReceiver.getLastName();

        if(gender!=null && gender !="" && lastName != null && lastName != "" ){
            if(gender.equals("f")) {
                emailModel.put("salutation", "Sehr geehrte Frau " + lastName);
            }
            else{
                emailModel.put("salutation", "Sehr geehrter Herr " + lastName);
            }
        }
        else{
            emailModel.put("salutation", "Guten Tag");
        }

        return emailModel;
    }

    private void getTemplateTextAndSendEmail(ForumUser notificationReceiver, String ftlTemplate, Map<String, String> emailModel, String emailSubject) throws ExpertForumException {
        String emailText;
        try {
            emailText = FreeMarkerTemplateUtils.processTemplateIntoString(serviceHelper.freemarkerConfiguration.getTemplate(ftlTemplate, "UTF-8"), emailModel);
            sendHtmlMail(emailText, notificationReceiver.getEmail(), emailSubject);
        } catch (IOException | TemplateException e) {
            throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.INTERNAL_ERROR, e);
        } catch (MessagingException me) {
            throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.MAIL_NOT_SENT, me);
        }
    }

    private void sendHtmlMail(String emailText, String emailTo, String emailSubject) throws MessagingException {
        MimeMessage message = serviceHelper.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setFrom(notificationEmailFrom);
        mimeMessageHelper.setTo(emailTo);
        mimeMessageHelper.setSubject(subjectPrefix + emailSubject);
        mimeMessageHelper.setText(emailText, true);
        serviceHelper.javaMailSender.send(message);
    }
}
