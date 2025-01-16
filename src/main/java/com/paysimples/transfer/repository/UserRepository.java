package com.paysimples.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paysimples.transfer.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findById(long id);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    
    
}
