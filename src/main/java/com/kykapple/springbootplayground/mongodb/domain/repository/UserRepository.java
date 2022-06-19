package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
}
