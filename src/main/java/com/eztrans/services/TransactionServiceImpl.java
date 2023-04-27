package com.eztrans.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eztrans.dto.request.SendMoneyRequest;
import com.eztrans.dto.request.WithdrawRequest;
import com.eztrans.models.Token;
import com.eztrans.models.User;
import com.eztrans.models.WithdrawTicket;
import com.eztrans.repository.TokenRepository;
import com.eztrans.repository.UserRepository;
import com.eztrans.repository.WithdrawTicketRepository;
import com.eztrans.utils.TokenGenerator;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	WithdrawTicketRepository withdrawTicketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	TokenGenerator tokenGenerator;

	@Override
	public ResponseEntity<?> sendMoney(SendMoneyRequest sendMoneyRequest) {
		// Amount check max 2000
		if (sendMoneyRequest.getAmount() > 2000) return ResponseEntity.badRequest().body("Amount cannot exceed 2000");
		
		Token token = new Token(tokenGenerator.generateNumericToken(10), tokenGenerator.generateToken());
		Optional<User> payer = userRepository.findByMobile(sendMoneyRequest.getPayerMobile());
		Optional<User> payee = userRepository.findByMobile(sendMoneyRequest.getPayeeMobile());
		if (payer.isPresent() && payee.isPresent()) {
			WithdrawTicket wTicket = new WithdrawTicket(payer.get().getMobile(), payee.get().getMobile(),
					payer.get().getName(), payee.get().getName(), sendMoneyRequest.getAmount(), token.getTokenString());
			tokenRepository.save(token);
			withdrawTicketRepository.save(wTicket);
			return ResponseEntity.ok(wTicket);
		}
		String msg;
		if (!payer.isPresent()) msg = "Payer does not exist";
		else msg = "Payee does not exist";
		return ResponseEntity.badRequest().body(msg);
	}

	@Override
	public String getQRCodeData(String tokenString) {
		Optional<Token> tokenObj = tokenRepository.findByTokenString(tokenString);
		if (tokenObj.isPresent()) {
			return tokenObj.get().getAtmQrDataString();
		}
		return ("Invalid_Token");
	}

	@Override
	public boolean verifyAtmQREncodedToken(String tokenString, String atmQrDataString) {
		Optional<Token> tokenObj = tokenRepository.findByTokenString(tokenString);
		if (tokenObj.isPresent()) {
			String atmStringFromTokenString = tokenObj.get().getAtmQrDataString();
			boolean validationSuccess = atmQrDataString.compareTo(atmStringFromTokenString) == 0;
			return validationSuccess;
		}
		return false;
	}

	@Override
	public ResponseEntity<?> withdrawList(WithdrawRequest withdrawRequest) {
		String mobileNumber = withdrawRequest.getPayeeMobile();
		List<WithdrawTicket> wTickets = new ArrayList<>();
		if (!(mobileNumber == null || mobileNumber.length() == 0)) {
			wTickets.addAll(maskMobileNo(withdrawTicketRepository.findAllByPayeeMobile(mobileNumber)));

		} else {
			mobileNumber = withdrawRequest.getPayerMobile();
			if (!(mobileNumber == null || mobileNumber.length() == 0)) {
				wTickets.addAll(maskMobileNo(withdrawTicketRepository.findAllByPayerMobile(mobileNumber)));
			}
		}
		return ResponseEntity.ok(wTickets);
	}

	@Override
	public ResponseEntity<?> withdrawDetails(WithdrawRequest withdrawRequest) {
		Long ticketId = withdrawRequest.getTicketId();
		if (!(ticketId == null)) {
			return ResponseEntity.ok(maskTicket(withdrawTicketRepository.findById(ticketId).get()));
		}
		return ResponseEntity.badRequest().body("Invalid Ticket");
	}
	
	private List<WithdrawTicket> maskMobileNo(List<WithdrawTicket> results) {
		return results.stream().map(obj -> {
			return maskTicket(obj);
		}).collect(Collectors.toCollection(ArrayList::new));
	}
	

	private WithdrawTicket maskTicket(WithdrawTicket ticket) {
		ticket.setPayeeMobile("XXXXXX" + ticket.getPayeeMobile().substring(6));
		ticket.setPayerMobile("XXXXXX" + ticket.getPayerMobile().substring(6));
		return ticket;
	}
}
