package de.scout24.financing.controller;

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

import de.scout24.financing.domain.Question;
import de.scout24.financing.resource.QuestionResource;
import de.scout24.financing.service.impl.FavoriteService;
import de.scout24.financing.service.impl.QuestionService;

/**
 * Created by ajit on 02.11.15.
 */
@RestController
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/forumUsers/{id}/favoriteQuestions", method = RequestMethod.GET)
    public PagedResources<QuestionResource> getQuestionsByForumUser(@PathVariable long id, @PageableDefault Pageable p,
            PagedResourcesAssembler<Question> assembler) {
        return favoriteService.getFavoriteQuestionsByForumUser(id, p, assembler);
    }

    @RequestMapping(value = "/questions/{id}/toggleFavorite", method = RequestMethod.GET)
    public QuestionResource toggleFavorite(@PathVariable("id") long questionId) {
        if(questionService.isMarkedAsFavorite(questionId))
            return questionService.unmarkQuestionAsFavorite(questionId);
        else
            return questionService.markQuestionAsFavorite(questionId);
    }

}
