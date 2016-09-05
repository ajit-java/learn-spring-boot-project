package de.scout24.financing.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import de.scout24.financing.resource.CategoryResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import de.scout24.financing.controller.CategoryController;
import de.scout24.financing.domain.Category;

@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, CategoryResource> {

    @Override
    public CategoryResource toResource(Category category) {
        CategoryResource resource = new CategoryResource(category);

//        getLinks(category, resource);
        return resource;
    }

    private void getLinks(Category category, CategoryResource resource) {
        try {
            resource.add(linkTo(methodOn(CategoryController.class).getCategory(category.getId())).withSelfRel());
            resource.add(linkTo(methodOn(CategoryController.class).getCategoriesPaged(new PageRequest(0, 20), null))
                    .withRel(Category.class.getSimpleName()));
        } catch (ResourceNotFoundException ex) {
            // do nothing
        }
    }
}
