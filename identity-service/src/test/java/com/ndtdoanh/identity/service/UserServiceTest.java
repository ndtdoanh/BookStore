package com.ndtdoanh.identity.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ndtdoanh.identity.dto.request.UserRequest;
import com.ndtdoanh.identity.dto.response.UserResponse;
import com.ndtdoanh.identity.entity.User;
import com.ndtdoanh.identity.exception.AppException;
import com.ndtdoanh.identity.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2002, 2, 20);

        request = UserRequest.builder()
                .username("trongdoanh3")
                .firstName("Trong")
                .lastName("Doanh3")
                .password("ntd12345")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("99ed79c005f5")
                .username("trongdoanh3")
                .firstName("Trong")
                .lastName("Doanh3")
                .dob(dob)
                .build();

        user = User.builder()
                .id("99ed79c005f5")
                .username("trongdoanh3")
                .firstName("Trong")
                .lastName("Doanh3")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        userService.createUser(request);
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("99ed79c005f5");
        Assertions.assertThat(response.getUsername()).isEqualTo("trongdoanh3");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }
}
