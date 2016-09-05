package de.scout24.financing.repository;

import java.util.List;

import de.scout24.financing.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.User;
import de.scout24.financing.domain.enums.ExfoRole;

public interface ForumUserRepository extends PagingAndSortingRepository<ForumUser, Long> {
    Page<ForumUser> findByUserAndDeletedFalse(User user, Pageable pageable);

    ForumUser findByUserIdAndDeletedFalse(long userId);

    long countByDeletedFalse();

    long countByRoleAndDeletedFalse(Role role);

    ForumUser findByEmail(String email);
    
    Page<Question> getFavoriteQuestionsById(long id, Pageable pageable);

    List<ForumUser> getByRoleAndDeletedFalse(Role role);
}
