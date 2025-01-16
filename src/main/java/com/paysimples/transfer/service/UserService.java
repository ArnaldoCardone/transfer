package com.paysimples.transfer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paysimples.transfer.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.paysimples.transfer.dto.MerchantDTO;
import com.paysimples.transfer.model.Merchant;
import com.paysimples.transfer.model.User;
import com.paysimples.transfer.repository.MerchantRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Transactional
    public User createUser(User user){
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    public Merchant createMerchant(MerchantDTO merchantDTO){
        User user = new User();
        user.setFullName(merchantDTO.getFullName());
        user.setCpf(merchantDTO.getCpf());
        user.setEmail(merchantDTO.getEmail());
        user.setPassword(merchantDTO.getPassword());
        user.setBalance(BigDecimal.ZERO);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        Merchant merchant = new Merchant();
        merchant.setCnpj(merchantDTO.getCnpj());
        merchant.setMerchant(user);
        return merchantRepository.save(merchant);
    }
}
