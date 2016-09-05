package de.scout24.financing.service.impl;

import de.scout24.financing.service.impl.notifications.NotificationQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.enums.NotificationEventType;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.CommentRepository;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.resource.CommentResource;
import de.scout24.financing.resource.assembler.CommentResourceAssembler;

/**
 * Created by tobiaswolfram on 12.10.15.
 */
@Service
public class CommentService extends BaseService {

    public CommentResource getComment(long id) {
        return serviceHelper.commentResourceAssembler.toResource(serviceHelper.commentRepository.findOne(id));
    }

    public Iterable<Comment> getComments() {
        return serviceHelper.commentRepository.findAll();
    }

    public PagedResources<CommentResource> getCommentsPaged(Pageable p, PagedResourcesAssembler<Comment> assembler) {
        Page<Comment> comments = serviceHelper.commentRepository.findAll(p);

        return assembler.toResource(comments, serviceHelper.commentResourceAssembler);
    }

    public PagedResources<CommentResource> getCommentsByStatus(PublishingStatus publishingStatus, Pageable p,
            PagedResourcesAssembler<Comment> assembler) {
        Page<Comment> comments = serviceHelper.commentRepository.findByPublishingStatusAndDeletedFalse(publishingStatus, p);

        return assembler.toResource(comments, serviceHelper.commentResourceAssembler);
    }

    public CommentResource updateCommentStatus(Comment pComment) {
        Comment comment = serviceHelper.commentRepository.findOne(pComment.getId());
        comment.setPublishingStatus(pComment.getPublishingStatus());
        serviceHelper.commentRepository.save(comment);
        return serviceHelper.commentResourceAssembler.toResource(comment);
    }

    public PagedResources<CommentResource> getCommentsByUser(long id, Pageable p, PagedResourcesAssembler<Comment> assembler) {
        Page<Comment> comments = serviceHelper.commentRepository.findByForumUserAndDeletedFalse(serviceHelper.forumUserRepository.findOne(id), p);
        return assembler.toResource(comments, serviceHelper.commentResourceAssembler);
    }

    public CommentResource saveComment(Comment comment) throws ExpertForumException {
        if (comment.getId() <= 0) { //save case
            comment.setForumUserId(serviceHelper.profileService.getLoggedInUser().getId());
            NotificationEventType notificationEventType = NotificationEventType.ANSWER_COMMENTED_BY_USER;
            if (serviceHelper.profileService.hasRole(ExfoRole.EXPERT_APPROVED)) {
                notificationEventType = NotificationEventType.ANSWER_COMMENTED_BY_EXPERT;
            }
            serviceHelper.commentRepository.save(comment);
            serviceHelper.notificationQueueService.queueNotification(notificationEventType, comment);
        } else {
            serviceHelper.commentRepository.save(comment);
        }

        return serviceHelper.commentResourceAssembler.toResource(comment);
    }
}
