package de.scout24.financing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;

//@PreAuthorize("hasRole('USER')")
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
    Page<Question> findByPublishingStatusAndDeletedFalse(PublishingStatus publishingStatus, Pageable pageable);

    Page<Question> findByQuestionStatusAndDeletedFalse(QuestionStatus questionStatus, Pageable pageable);

    Page<Question> findByForumUserAndDeletedFalse(ForumUser forumUser, Pageable pageable);

    long countByPublishingStatusAndDeletedFalse(PublishingStatus publishingStatus);

    long countByQuestionStatusAndDeletedFalse(QuestionStatus questionStatus);

    Page<Question> findByUsersAsFavoriteAndPublishingStatusAndDeletedFalse(ForumUser forumUser, PublishingStatus publishingStatus, Pageable pageable);
    
    long countByIdAndUsersAsFavorite(long questionId, ForumUser forumUser);
}
