package com.kykapple.springbootplayground.mongodb.service;

import com.kykapple.springbootplayground.mongodb.domain.User;
import com.kykapple.springbootplayground.mongodb.domain.repository.UserRepository;
import com.kykapple.springbootplayground.mongodb.service.dto.UpdateUserRequestDto;
import com.kykapple.springbootplayground.mongodb.service.dto.UserRequestDto;
import com.kykapple.springbootplayground.mongodb.service.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse saveUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getName(), userRequestDto.getAge());
        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser);
    }

    public UserResponse findUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return new UserResponse(user);
    }

    public UserResponse updateUserAge(UpdateUserRequestDto updateUserRequestDto) {
        User modifiedUser = userRepository.findAndUpdateUserAge(updateUserRequestDto.getName(), updateUserRequestDto.getAge());

        return new UserResponse(modifiedUser);
    }

    public long updateAllUserAage(UpdateUserRequestDto updateUserRequestDto) {
        return userRepository.updateAllUserAge(updateUserRequestDto.getName(), updateUserRequestDto.getAge());
    }

    public UserResponse removeUser(String userId) {
        User removedUser = userRepository.removeUser(userId);

        return new UserResponse(removedUser);
    }

}
