package com.eztrans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eztrans.models.WithdrawTicket;

@Repository
public interface WithdrawTicketRepository extends JpaRepository<WithdrawTicket, Long> {
	Optional<WithdrawTicket> findByToken(String payerMobile);

	List<WithdrawTicket> findAllByPayerMobile(String payerMobile);

	List<WithdrawTicket> findAllByPayeeMobile(String payeeMobile);
}
