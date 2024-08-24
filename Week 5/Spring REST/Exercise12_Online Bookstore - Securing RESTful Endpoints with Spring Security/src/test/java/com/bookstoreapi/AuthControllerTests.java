package com.example.bookstore;

import com.example.bookstore.controller.AuthController;
import com.example.bookstore.dto.AuthRequest;
import com.example.bookstore.dto.AuthResponse;
import com.example.bookstore.service.JwtUserDetailsService;
import com.example.bookstore.config.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService userDetailsService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthenticationToken() throws Exception {
        AuthRequest authRequest = new AuthRequest("user1", "password");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(jwtTokenUtil.generateToken(any())).thenReturn("fake-jwt-token");

        ResponseEntity<?> response = authController.createAuthenticationToken(authRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("fake-jwt-token", ((AuthResponse) response.getBody()).getJwt());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testSaveUser() throws Exception {
        AuthRequest authRequest = new AuthRequest("user1", "password");
        when(userDetailsService.save(any())).thenReturn(any());

        ResponseEntity<?> response = authController.saveUser(authRequest);

        assertEquals(200, response.getStatusCodeValue());
        verify(userDetailsService, times(1)).save(any());
    }
}
