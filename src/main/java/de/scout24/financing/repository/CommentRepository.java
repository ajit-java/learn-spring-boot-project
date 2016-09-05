package de.scout24.financing.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.enums.PublishingStatus;

//@PreAuthorize("hasRole('ROLE_USER')")
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    Page<Comment> findByPublishingStatusAndDeletedFalse(PublishingStatus publishingStatusId, Pageable pageable);

    Page<Comment> findByForumUserAndDeletedFalse(ForumUser forumUser, Pageable pageable);

    long countByQuestionIdAndDeletedFalse(long questionId);

    List<Comment> findByQuestionIdAndDeletedFalse(long questionId);

    Page<Comment> findByQuestionIdAndDeletedFalse(long answerId, Pageable pageable);

    long countByAnswerIdAndDeletedFalse(long answerId);

    List<Comment> findByAnswerIdAndDeletedFalse(long answerId);

    Page<Comment> findByAnswerIdAndDeletedFalse(long answerId, Pageable pageable);

    long countByDeletedFalse();
}
