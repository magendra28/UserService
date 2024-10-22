package com.productservice.userservice.repository;

import com.productservice.userservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Override
     Token save(Token token);


    Optional<Token> findByTokenValAndDeleted(String token,boolean deleted);

    Optional<Token> findByTokenValAndDeletedAndExpiryAtGreaterThan(String token, boolean deleted, Date currentDate);
}
