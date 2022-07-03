package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    List<User> findUsersByRegisteredAtBetween(LocalDateTime from, LocalDateTime to);

}
