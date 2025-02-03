package com.zzuag.user_service.persistence;

import com.zzuag.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserCertification_Email(String email);
    Optional<User> findByUserIdAndIsDeletedIsFalse(Long id);
    Optional<User> findByUserCertification_EmailAndIsDeletedIsFalse(String email);
}
