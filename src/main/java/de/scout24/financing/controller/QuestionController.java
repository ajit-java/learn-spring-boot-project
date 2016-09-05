package de.scout24.financing.controller;

import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.exception.MissingFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;
import de.scout24.financing.resource.AnswerResource;
import de.scout24.financing.resource.CommentResource;
import de.scout24.financing.resource.QuestionResource;
import de.scout24.financing.service.impl.QuestionService;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public Resource<Question> saveUpdate(@RequestBody Question question) throws ExpertForumException {
        return questionService.saveQuestion(question);
    }

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    public Resource<Question> getQuestion(@PathVariable long id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(value = "/questions/all", method = RequestMethod.GET)
    public Iterable<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @RequestMapping(value = "/questions/all/paged", method = RequestMethod.GET)
    public PagedResources<QuestionResource> getQuestionsPaged(@PageableDefault Pageable p, PagedResourcesAssembler<Question> assembler) {
        return questionService.getQuestionsPaged(p, assembler);
    }

    @RequestMapping(value = "/questions/QuestionStatus/{questionStatus}", method = RequestMethod.GET)
    public PagedResources<QuestionResource> getQuestionsByStatus(@PathVariable QuestionStatus questionStatus, @PageableDefault Pageable p,
            PagedResourcesAssembler<Question> assembler) {
        return questionService.getQuestionsByStatus(questionStatus, p, assembler);
    }

    @RequestMapping(value = "/questions/PublishingStatus/{publishingStatus}", method = RequestMethod.GET)
    public PagedResources<QuestionResource> getQuestionsByPublishingStatus(@PathVariable PublishingStatus publishingStatus,
            @PageableDefault Pageable p, PagedResourcesAssembler<Question> assembler) {
        return questionService.getQuestionsByPublishingStatus(publishingStatus, p, assembler);
    }

    @RequestMapping(value = "/questions/{questionId}/answers", method = RequestMethod.GET)
    public PagedResources<AnswerResource> getQuestionAnswers(@PathVariable long questionId, @PageableDefault Pageable p,
            PagedResourcesAssembler<Answer> assembler) {
        return questionService.getQuestionAnswers(questionId, p, assembler);
    }

    @RequestMapping(value = "/questions/{questionId}/comments", method = RequestMethod.GET)
    public PagedResources<CommentResource> getQuestionComments(@PathVariable long questionId, @PageableDefault Pageable p,
            PagedResourcesAssembler<Comment> assembler) {
        return questionService.getQuestionComments(questionId, p, assembler);
    }

    @RequestMapping(value = "/forumUsers/{id}/questions", method = RequestMethod.GET)
    public PagedResources<QuestionResource> getQuestionsByForumUser(@PathVariable long id, @PageableDefault Pageable p,
            PagedResourcesAssembler<Question> assembler) {
        return questionService.getQuestionsByForumUser(id, p, assembler);
    }

    @RequestMapping(value = "/questions/updatePublishingStatus", method = RequestMethod.POST)
    public Resource<Question> updateQuestionPublishingStatus(@RequestBody Question question) throws ExpertForumException {

        return questionService.updateQuestionPublishingStatus(question);
    }
    @RequestMapping(value = "/questions/updateQuestionStatus", method = RequestMethod.POST)
    public Resource<Question> updateQuestionStatus(@RequestBody Question question) throws ExpertForumException {

        return questionService.updateQuestionStatus(question);
    }

}
