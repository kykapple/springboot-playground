package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;

public interface CustomUserRepository {

    User findAndUpdateUserAge(String userName, int age);

    long updateAllUserAge(String userName, int age);

    User removeUser(String userId);

}
