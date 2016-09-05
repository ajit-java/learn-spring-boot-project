package de.scout24.financing.resource;

import lombok.Data;
import org.springframework.hateoas.Resource;

import de.scout24.financing.domain.Category;
@Data
public class CategoryResource extends Resource<Category> {
    public CategoryResource(Category content) {
        super(content);
    }
}
