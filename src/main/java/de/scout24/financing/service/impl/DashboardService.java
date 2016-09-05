package de.scout24.financing.service.impl;

import de.scout24.financing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import de.scout24.financing.domain.enums.QuestionStatus;
import de.scout24.financing.domain.enums.ExfoRole;
import org.springframework.stereotype.Service;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.resource.DashboardResource;
import de.scout24.financing.resource.QuestionDashboardResource;

/**
 * Initially created by Tino on 05.08.15.
 */
@Service
public class DashboardService extends BaseService{

    public DashboardResource getAdminDashboard() {

        DashboardResource resource = new DashboardResource();
        resource.setAnswersCount(serviceHelper.answerRepository.countByDeletedFalse());
        resource.setCommentsCount(serviceHelper.commentRepository.countByDeletedFalse());

        resource.setUserCount(serviceHelper.forumUserRepository.countByRoleAndDeletedFalse(serviceHelper.roleRepository.getByName(ExfoRole.USER)));
        resource.setNewExpertsCount(serviceHelper.forumUserRepository.countByRoleAndDeletedFalse(serviceHelper.roleRepository.getByName(ExfoRole.EXPERT_REQUESTED)));
        resource.setExpertsCount(serviceHelper.forumUserRepository.countByRoleAndDeletedFalse(serviceHelper.roleRepository.getByName(ExfoRole.EXPERT_APPROVED)));

        resource.setApprovedQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.APPROVED));
        resource.setNewQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.NEW));
        resource.setClosedQuestionCount(serviceHelper.questionRepository.countByQuestionStatusAndDeletedFalse(QuestionStatus.CLOSED));
        resource.setSpamQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.SPAM));

        resource.setCategoryCount(serviceHelper.categoryRepository.countByDeletedFalse());

        return resource;

    }

    public QuestionDashboardResource getQuestionDashboard() {

        QuestionDashboardResource resource = new QuestionDashboardResource();
        resource.setNewQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.NEW));
        resource.setApprovedQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.APPROVED));
        resource.setSpamQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.SPAM));
        resource.setRejectedQuestionCount(serviceHelper.questionRepository.countByPublishingStatusAndDeletedFalse(PublishingStatus.REJECTED));

        return resource;
    }
}

