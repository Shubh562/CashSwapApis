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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eztrans.dto.request.SendMoneyRequest;
import com.eztrans.models.User;
import com.eztrans.models.WithdrawTicket;
import com.eztrans.repository.TokenRepository;
import com.eztrans.repository.UserRepository;
import com.eztrans.repository.WithdrawTicketRepository;
import com.eztrans.utils.TokenGenerator;


    @RunWith(MockitoJUnitRunner.class)
    public class TransactionServiceImplTest {

        @Mock
        private WithdrawTicketRepository withdrawTicketRepository;

        @Mock
        private UserRepository userRepository;

        @Mock
        private TokenRepository tokenRepository;

        @Mock
        private TokenGenerator tokenGenerator;

        @InjectMocks
        private TransactionServiceImpl transactionService;

        @Before
        public void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testSendMoneyWithValidData() {
            SendMoneyRequest request = new SendMoneyRequest("1234567890","0987654321",100);

            User payer = new User();
            payer.setFirstName("Payer");
            payer.setLastName("1");
            payer.setEmail("Payer@example.com");
            payer.setMobile("1234567890");
            payer.setPin("1234");

            User payee = new User();
            payee.setFirstName("Payee");
            payee.setLastName("1");
            payee.setEmail("Payee@example.com");
            payee.setMobile("0987654321");
            payee.setPin("1234");

            when(userRepository.findByMobile("1234567890")).thenReturn(Optional.of(payer));
            when(userRepository.findByMobile("0987654321")).thenReturn(Optional.of(payee));
            when(tokenGenerator.generateNumericToken(10)).thenReturn("1234567890");
            when(tokenGenerator.generateToken()).thenReturn("abcdefg");

            ResponseEntity<?> response = transactionService.sendMoney(request);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(WithdrawTicket.class, response.getBody().getClass());
        }

        @Test
        public void testSendMoneyWithInvalidPayerMobile() {
            SendMoneyRequest request = new SendMoneyRequest("1234567890","0987654321",100);
            User payee = new User();
            payee.setFirstName("Payee");
            payee.setLastName("1");
            payee.setEmail("Payee@example.com");
            payee.setMobile("0987654321");
            payee.setPin("1234");

            when(userRepository.findByMobile("1234567890")).thenReturn(Optional.empty());
            when(userRepository.findByMobile("0987654321")).thenReturn(Optional.of(payee));

            ResponseEntity<?> response = transactionService.sendMoney(request);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("Payer does not exist", response.getBody());
        }
}