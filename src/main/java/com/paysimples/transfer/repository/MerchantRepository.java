package com.paysimples.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paysimples.transfer.model.Merchant;
import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    
    Optional<Merchant> findById(long id);
    Optional<Merchant> findByCnpj(String cnpj);
    
}
