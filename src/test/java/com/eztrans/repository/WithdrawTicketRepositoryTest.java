package com.eztrans.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.eztrans.enums.TicketStatus;
import com.eztrans.models.WithdrawTicket;

@DataJpaTest
public class WithdrawTicketRepositoryTest {

    @Autowired
    private WithdrawTicketRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByToken() {
        WithdrawTicket ticket = new WithdrawTicket("1234567890", "0987654321", "John Doe", "Jane Doe", 1000L, "token123");
        entityManager.persist(ticket);
        entityManager.flush();

        Optional<WithdrawTicket> found = repository.findByToken("token123");

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getPayerMobile()).isEqualTo("1234567890");
        assertThat(found.get().getPayeeMobile()).isEqualTo("0987654321");
        assertThat(found.get().getPayerName()).isEqualTo("John Doe");
        assertThat(found.get().getPayeeName()).isEqualTo("Jane Doe");
        assertThat(found.get().getStatus()).isEqualTo(TicketStatus.ACTIVE);
        assertThat(found.get().getAmount()).isEqualTo(1000L);
        assertThat(found.get().getToken()).isEqualTo("token123");
    }

    @Test
    public void testFindAllByPayerMobile() {
        WithdrawTicket ticket1 = new WithdrawTicket("1234567890", "0987654321", "John Doe", "Jane Doe", 1000L, "token123");
        WithdrawTicket ticket2 = new WithdrawTicket("1234567890", "0987654321", "John Smith", "Jane Smith", 2000L, "token456");
        entityManager.persist(ticket1);
        entityManager.persist(ticket2);
        entityManager.flush();

        List<WithdrawTicket> found = repository.findAllByPayerMobile("1234567890");

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0).getPayerName()).isEqualTo("John Doe");
        assertThat(found.get(1).getPayerName()).isEqualTo("John Smith");
    }

    @Test
    public void testFindAllByPayeeMobile() {
        WithdrawTicket ticket1 = new WithdrawTicket("1234567890", "0987654321", "John Doe", "Jane Doe", 1000L, "token123");
        WithdrawTicket ticket2 = new WithdrawTicket("0987654321", "1234567890", "Jane Smith", "John Smith", 2000L, "token456");
        entityManager.persist(ticket1);
        entityManager.persist(ticket2);
        entityManager.flush();

        List<WithdrawTicket> found = repository.findAllByPayeeMobile("1234567890");

        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getPayeeName()).isEqualTo("John Smith");
    }
}
