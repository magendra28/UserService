package com.productservice.userservice.repository;

import com.productservice.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    User save(User user);

    Optional<User> findByEmail(String email);
}
