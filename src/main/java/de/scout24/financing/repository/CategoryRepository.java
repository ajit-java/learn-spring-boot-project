package de.scout24.financing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import de.scout24.financing.domain.Category;

//@PreAuthorize("hasRole('ROLE_USER')")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    // Page<Category> findByPlatformId(long platformId, Pageable pageable);
    Page<Category> findByCategoryNameAndDeletedFalse(String categoryName, Pageable pageable);

    Page<Category> findByParentCategoryIdAndDeletedFalse(long parentCategoryId, int categoryStatus, Pageable pageable);

    long countByDeletedFalse();
}
