package com.eztrans.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.eztrans.models.Token;

@DataJpaTest
@ActiveProfiles("test")
public class TokenRepositoryTest {

    @MockBean
    private TokenRepository tokenRepository;

    @Test
    public void testFindByTokenString() {
        String tokenString = "token123";
        Token token = new Token(tokenString, "atmQrDataString123");
        when(tokenRepository.findByTokenString(anyString())).thenReturn(Optional.of(token));

        Optional<Token> result = tokenRepository.findByTokenString(tokenString);
        assertTrue(result.isPresent());
        assertEquals(tokenString, result.get().getTokenString());
    }

    @Test
    public void testFindByTokenStringNotFound() {
        String tokenString = "token123";
        when(tokenRepository.findByTokenString(anyString())).thenReturn(Optional.empty());

        Optional<Token> result = tokenRepository.findByTokenString(tokenString);
        assertFalse(result.isPresent());
    }

}
