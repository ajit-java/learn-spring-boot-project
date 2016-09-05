package de.scout24.financing.controller;

import de.scout24.financing.exception.ExpertForumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.resource.CommentResource;
import de.scout24.financing.service.impl.CommentService;
import de.scout24.financing.exception.MissingFieldException;

/**
 * Created by ajitchahal on 10/09/15.
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public Resource<Comment> saveUpdate(@RequestBody Comment comment) throws ExpertForumException {
        return commentService.saveComment(comment);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public Resource<Comment> getComment(@PathVariable long id) {
        return commentService.getComment(id);
    }

    @RequestMapping(value = "/comments/all", method = RequestMethod.GET)
    public Iterable<Comment> getComments() {
        return commentService.getComments();
    }

    @RequestMapping(value = "/comments/all/paged", method = RequestMethod.GET)
    public PagedResources<CommentResource> getCommentsPaged(@PageableDefault Pageable p, PagedResourcesAssembler<Comment> assembler) {
        PagedResources<CommentResource> comments = commentService.getCommentsPaged(p, assembler);

        return comments;
    }

    @RequestMapping(value = "/comments/byPublishingStatus", method = RequestMethod.GET)
    public PagedResources<CommentResource> getCommentsByStatus(PublishingStatus publishingStatus, @PageableDefault Pageable p, PagedResourcesAssembler<Comment> assembler) {
        return commentService.getCommentsByStatus(publishingStatus, p, assembler);
    }

    @RequestMapping(value = "/comments/updatePublishingStatus", method = RequestMethod.POST)
    public Resource<Comment> updateCommentsStatus(@RequestBody Comment comment) throws MissingFieldException {

        return commentService.updateCommentStatus(comment);

    }

    @RequestMapping(value = "/forumUsers/{id}/comments", method = RequestMethod.GET)
    public PagedResources<CommentResource> getCommentsByUser(@PathVariable long id, @PageableDefault Pageable p, PagedResourcesAssembler<Comment> assembler) {
        return commentService.getCommentsByUser(id, p, assembler);
    }

    // @RequestMapping(value="/comments/byForumUser",method = RequestMethod.GET)
    // public PagedResources<Resource<Comment>> getCommentsByUser(long forumUserId, @PageableDefault Pageable p, PagedResourcesAssembler
    // pagedAssembler){
    // Page<Comment> comments = commentRepository.findByForumUser(forumUserRepository.findOne(forumUserId), p);
    //
    // return pagedAssembler.toResource(comments, commentResourceAssembler);
    // }
}
