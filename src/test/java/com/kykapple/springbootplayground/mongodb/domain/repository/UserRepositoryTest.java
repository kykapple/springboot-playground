package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;
import com.kykapple.springbootplayground.mongodb.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void 유저를_생성한다() {
        // given
        User testUser = new User("testUser", 25);

        // when
        User savedUser = userRepository.save(testUser);

        // then
        assertThat(savedUser.get_id()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo(testUser.getName());
        assertThat(savedUser.getAge()).isEqualTo(testUser.getAge());
    }

    @Test
    void 유저_정보를_수정한다() {
        // given
        User savedUser = userRepository.save(new User("testUser", 25));

        // when
        User modifiedUser = userRepository.findAndUpdateUserAge(savedUser.getName(), 35);

        // then
        assertThat(savedUser.getAge()).isNotEqualTo(modifiedUser.getAge());
    }

    @Test
    void 유저를_삭제한다() {
        // given
        User savedUser = userRepository.save(new User("testUser", 25));

        // when
        userRepository.delete(savedUser);

        // then
        assertThatThrownBy(() ->
                userRepository.findById(savedUser.get_id().toString())
                        .orElseThrow(() -> new UserNotFoundException("해당하는 유저가 존재하지 않습니다."))
        ).isInstanceOf(UserNotFoundException.class);
    }

}