package de.scout24.financing.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.enums.PublishingStatus;

//@PreAuthorize("hasRole('ROLE_USER')")
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {

    Page<Answer> findByPublishingStatusAndDeletedFalse(PublishingStatus publishingStatus, Pageable pageable);

    Page<Answer> findByForumUserAndDeletedFalse(ForumUser forumUser, Pageable pageable);

    long countByQuestionIdAndDeletedFalse(long questionId);

    List<Answer> findByQuestionIdAndDeletedFalse(long questionId);

    Page<Answer> findByQuestionIdAndDeletedFalse(long questionId, Pageable pageable);

    long countByDeletedFalse();

//    List<Answer> getByQuestionIdAndDeletedFalse(long questionId);
    List<ForumUser> getByQuestionIdAndDeletedFalse(long questionId);
}
