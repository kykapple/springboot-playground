package com.kykapple.springbootplayground.mongodb.presentation;

import com.kykapple.springbootplayground.mongodb.presentation.dto.CreateUserRequest;
import com.kykapple.springbootplayground.mongodb.presentation.dto.UpdateUserRequest;
import com.kykapple.springbootplayground.mongodb.service.UserService;
import com.kykapple.springbootplayground.mongodb.service.dto.UpdateUserRequestDto;
import com.kykapple.springbootplayground.mongodb.service.dto.UserRequestDto;
import com.kykapple.springbootplayground.mongodb.service.dto.UserResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserResponse> saveUser(@RequestBody CreateUserRequest createUserRequest) {
        UserRequestDto userRequestDto = new UserRequestDto(createUserRequest.getName(), createUserRequest.getAge());
        UserResponse userResponse = userService.saveUser(userRequestDto);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable String userId) {
        UserResponse userResponse = userService.findUser(userId);

        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/api/users")
    public ResponseEntity<UserResponse> updateUserAge(@RequestBody UpdateUserRequest updateUserRequest) {
        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto(updateUserRequest.getName(), updateUserRequest.getAge());
        UserResponse userResponse = userService.updateUserAge(updateUserRequestDto);

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/api/users")
    public ResponseEntity<Long> updateAllUseAage(@RequestBody UpdateUserRequest updateUserRequest) {
        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto(updateUserRequest.getName(), updateUserRequest.getAge());
        long count = userService.updateAllUserAage(updateUserRequestDto);

        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> removeUser(@PathVariable String userId) {
        UserResponse userResponse = userService.removeUser(userId);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/api/users/date")
    public ResponseEntity<List<UserResponse>> findUserByRegisteredAt(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime current) {
        List<UserResponse> users = userService.findUserByRegisteredAtLeastOneHour(current);

        return ResponseEntity.ok(users);
    }


}
