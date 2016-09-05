package de.scout24.financing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.QuestionRepository;
import de.scout24.financing.resource.QuestionResource;
import de.scout24.financing.resource.assembler.QuestionResourceAssembler;

/**
 * Created by ajit on 02.11.15.
 */
@Service
public class FavoriteService extends BaseService {

    public PagedResources<QuestionResource> getFavoriteQuestionsByForumUser(long userId, Pageable p, PagedResourcesAssembler<Question> assembler) {
        ForumUser user = serviceHelper.forumUserRepository.findOne(userId);
        Page<Question> questions = serviceHelper.questionRepository.findByUsersAsFavoriteAndPublishingStatusAndDeletedFalse(user, PublishingStatus.APPROVED, p);
        return assembler.toResource(questions, serviceHelper.questionResourceAssembler);
    }

   
}
