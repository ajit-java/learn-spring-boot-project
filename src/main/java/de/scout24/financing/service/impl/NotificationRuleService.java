package de.scout24.financing.service.impl;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.resource.AnswerResource;
import de.scout24.financing.resource.CommentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Initially created by Ajit on 08.10.15.
 */
@Service
@Transactional
public class NotificationRuleService extends BaseService {
    public Iterable<NotificationRule> getNotificationsRules() {
        return serviceHelper.notificationRuleRepository.findAll();
    }
}
