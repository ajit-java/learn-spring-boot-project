package de.scout24.financing.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.scout24.financing.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmailAndPasswordAndDeletedFalse(String loginId, String password);

    User findByEmailAndDeletedFalse(String email);
    User findBySsoIdAndDeletedFalse(String ssoId);
}
