package de.scout24.financing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.domain.Question;
import de.scout24.financing.resource.QuestionResource;
import de.scout24.financing.resource.assembler.QuestionResourceAssembler;
import de.scout24.financing.service.impl.SearchService;

@RestController
public class SearchController {
    
    @Autowired
    private SearchService searchService;
    
    @Autowired
    private QuestionResourceAssembler questionResourceAssembler;

    @RequestMapping(value = "search/{term}", method = RequestMethod.GET)
    public PagedResources<QuestionResource> search(@PathVariable String term, @PageableDefault Pageable pageable, 
            PagedResourcesAssembler<Question> assembler) {
        Page<Question> questions = searchService.search(term, pageable);
        return assembler.toResource(questions, questionResourceAssembler);
    }
}
