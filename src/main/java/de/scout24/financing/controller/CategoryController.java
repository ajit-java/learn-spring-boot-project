package de.scout24.financing.controller;

import de.scout24.financing.domain.Question;
import de.scout24.financing.exception.ExpertForumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import de.scout24.financing.domain.Category;
import de.scout24.financing.resource.CategoryResource;
import de.scout24.financing.service.impl.CategoryService;

/**
 * Created by ajitchahal on 10/09/15.
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories/all", method = RequestMethod.GET)
    public Iterable<Category> getCategories() {
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public Resource<Category> getCategory(@PathVariable long id) {
        return categoryService.getCategory(id);
    }

    @RequestMapping(value = "/categories/all/paged", method = RequestMethod.GET)
    public PagedResources<CategoryResource> getCategoriesPaged(@PageableDefault Pageable p, PagedResourcesAssembler<Category> assembler) {
        PagedResources<CategoryResource> categories = categoryService.getCategoriesPaged(p, assembler);

        return categories;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public CategoryResource saveUpdate(@RequestBody Category category) throws ExpertForumException {
        return categoryService.saveCategory(category);
    }
}
