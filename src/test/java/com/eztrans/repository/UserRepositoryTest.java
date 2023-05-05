package com.eztrans.repository;

import com.eztrans.dto.response.UserInfoResponse;
import com.eztrans.models.User;
import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void findByMobile() {
        User user=new User("shubham3","soni","shubham1@example.com","7870577777","7482");
        userRepository.save(user);

        Optional<User> byMobile = userRepository.findByMobile(user.getMobile());
        assertThat(byMobile.get().getMobile()).isEqualTo(user.getMobile());
    }

    @Test
    void existsByMobile() {
        User user=new User("shubham","soni","shubham123@example.com","7870577070","7482");
        userRepository.save(user);

        Boolean Actual =userRepository.existsByMobile(user.getMobile());
        assertThat(Actual).isTrue();
    }

    @Test
    void existsByEmail() {
        User user=new User("shubham1","soni","shubham1234@example.com","9348993307","7482");
        userRepository.save(user);

        Boolean Actual =userRepository.existsByEmail(user.getEmail() );
        assertThat(Actual).isTrue();
    }

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }
}