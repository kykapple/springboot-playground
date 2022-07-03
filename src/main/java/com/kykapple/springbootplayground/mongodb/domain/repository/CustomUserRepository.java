package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomUserRepository {

    User findAndUpdateUserAge(String userName, int age);

    long updateAllUserAge(String userName, int age);

    User removeUser(String userId);

    List<User> findUserByRegisteredAtLeastOneHour(LocalDateTime current);

}
