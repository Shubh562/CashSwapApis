package com.eztrans.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.eztrans.dto.request.LoginRequest;
import com.eztrans.dto.request.SignupRequest;
import com.eztrans.dto.response.MessageResponse;
import com.eztrans.dto.response.UserInfoResponse;
import com.eztrans.models.User;
import com.eztrans.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticateUser() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setMobile("1234567890");
        loginRequest.setPin("1234");

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setMobile("1234567890");
        user.setPin("1234");

        when(userRepository.findByMobile(loginRequest.getMobile())).thenReturn(Optional.of(user));

        ResponseEntity<?> responseEntity = userService.authenticateUser(loginRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());

        UserInfoResponse userInfoResponse = (UserInfoResponse) responseEntity.getBody();
        assertEquals(Optional.of(user.getId()), Optional.ofNullable(userInfoResponse.getId()));
        assertEquals(user.getEmail(), userInfoResponse.getEmail());
        assertEquals(user.getMobile(), userInfoResponse.getMobile());
    }

    @Test
    public void testCreateUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstName("John");
        signupRequest.setLastName("Doe");
        signupRequest.setMobile("1234567890");
        signupRequest.setEmail("johndoe@example.com");
        signupRequest.setPin("1234");



        when(userRepository.existsByMobile(signupRequest.getMobile())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);

        ResponseEntity<?> responseEntity = userService.createUser(signupRequest);

        assertEquals(200, responseEntity.getStatusCodeValue());

        MessageResponse messageResponse = (MessageResponse) responseEntity.getBody();
        assertEquals("User registered successfully!", messageResponse.getMessage());
    }
}
