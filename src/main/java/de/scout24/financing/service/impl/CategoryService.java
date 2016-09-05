package de.scout24.financing.service.impl;

import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.Category;
import de.scout24.financing.repository.CategoryRepository;
import de.scout24.financing.resource.CategoryResource;
import de.scout24.financing.resource.assembler.CategoryResourceAssembler;

/**
 * Created by tobiaswolfram on 12.10.15.
 */
@Service
public class CategoryService extends BaseService{

    public Iterable<Category> getCategories() {
        return serviceHelper.categoryRepository.findAll();
    }

    public Resource<Category> getCategory(long id) {
        return serviceHelper.categoryResourceAssembler.toResource(serviceHelper.categoryRepository.findOne(id));
    }

    public PagedResources<CategoryResource> getCategoriesPaged(Pageable p, PagedResourcesAssembler<Category> assembler) {
        Page<Category> categories = serviceHelper.categoryRepository.findAll(p);

        return assembler.toResource(categories, serviceHelper.categoryResourceAssembler);
    }

    @Secured("hasAnyAuthority('ADD_CATEGORIES')")
    public CategoryResource saveCategory(Category category) {
        category = serviceHelper.categoryRepository.save(category);
        return serviceHelper.categoryResourceAssembler.toResource(category);
    }

}
