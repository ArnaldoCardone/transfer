package com.paysimples.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paysimples.transfer.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    

    
}
