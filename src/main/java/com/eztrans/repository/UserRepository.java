package com.eztrans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eztrans.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByMobile(String mobile);

  Boolean existsByMobile(String mobile);

  Boolean existsByEmail(String email);
}
