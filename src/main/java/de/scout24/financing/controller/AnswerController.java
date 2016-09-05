package de.scout24.financing.controller;

import de.scout24.financing.domain.Question;
import de.scout24.financing.exception.ExpertForumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.resource.AnswerResource;
import de.scout24.financing.resource.CommentResource;
import de.scout24.financing.service.impl.AnswerService;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    public Resource<Answer> saveUpdate(@RequestBody Answer answer) throws ExpertForumException {
        return answerService.saveAnswer(answer);
    }

    @RequestMapping(value = "/answers/{id}", method = RequestMethod.GET)
    public Resource<Answer> getAnswer(@PathVariable long id) {
        return answerService.getAnswer(id);
    }

    @RequestMapping(value = "/answers/all", method = RequestMethod.GET)
    public Iterable<Answer> getAnswers() {
        return answerService.getAnswers();
    }

    @RequestMapping(value = "/answers/all/paged", method = RequestMethod.GET)
    public PagedResources<AnswerResource> getAnswersPaged(@PageableDefault Pageable pageable, PagedResourcesAssembler<Answer> assembler) {
        return answerService.getAnswersPaged(pageable, assembler);
    }

    @RequestMapping(value = "/answers/byPublishingStatus", method = RequestMethod.GET)
    public PagedResources<AnswerResource> getAnswersByStatus(PublishingStatus publishingStatus, @PageableDefault Pageable pageable,
            PagedResourcesAssembler<Answer> assembler) {
        return answerService.getAnswersByStatus(publishingStatus, pageable, assembler);
    }

    @RequestMapping(value = "/answers/{answerId}/comments", method = RequestMethod.GET)
    public PagedResources<CommentResource> getAnswerComments(@PathVariable long answerId, @PageableDefault Pageable pageable,
            PagedResourcesAssembler<Comment> assembler) {
        return answerService.getAnswerComments(answerId, pageable, assembler);
    }

    //get answers of user
    @RequestMapping(value = "/forumUsers/{id}/answers", method = RequestMethod.GET)
    public PagedResources<AnswerResource> getAnswersByForumUser(@PathVariable long id, @PageableDefault Pageable p,
            PagedResourcesAssembler<Answer> assembler) {
        return answerService.getAnswersByForumUser(id, p, assembler);
    }

}
