package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Category extends AbstractBaseDomain {

    @Column(name = "CategoryName")
    private String categoryName;
    @Column(name = "ID_Category_parent")
    private Long parentCategoryId;

}
