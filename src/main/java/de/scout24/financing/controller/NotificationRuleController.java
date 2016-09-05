package de.scout24.financing.controller;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.resource.AnswerResource;
import de.scout24.financing.resource.CommentResource;
import de.scout24.financing.service.impl.NotificationRuleService;
import de.scout24.financing.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationRuleController {

    @Autowired
    private NotificationRuleService notificationRuleService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/notificationRules/all", method = RequestMethod.GET)
    public Iterable<NotificationRule> getNotificationsRules() {
        Iterable<NotificationRule> rules = notificationRuleService.getNotificationsRules();

        return rules;
    }
}
