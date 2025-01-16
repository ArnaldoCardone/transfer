package com.paysimples.transfer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paysimples.transfer.model.Transfer;
import com.paysimples.transfer.model.User;
import com.paysimples.transfer.repository.MerchantRepository;
import com.paysimples.transfer.repository.TransferRepository;
import com.paysimples.transfer.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ExternalAuthorizationService externalAuthorizationService;

    @Transactional
    public Transfer executeTransfer(long payerId, long payeeId, BigDecimal valueTransfer){

        //Verifica se o pagador é uma loja
        if(merchantRepository.existsById(payerId)){
            throw new RuntimeException("Loja não pode realizar transferência");
        }
        //Valida o usuário pagador da transação
        User uPayer = userRepository.findById(payerId).orElseThrow(() -> new RuntimeException("Usuário pagador não encontrado"));
        
        //Valida o usuário recebedor da transação
        User uPayee = userRepository.findById(payeeId).orElseThrow(() -> new RuntimeException("Usuário beneficiário não encontrado"));

        //Valida se o usuário pagador tem saldo suficiente para realizar a transferência
        if(uPayer.getBalance().compareTo(valueTransfer) < 0){
            throw new RuntimeException("Saldo insuficiente para realizar a transferência");
        }
        //Valida se o usuário pagador e beneficiário são a mesma pessoa
        if(uPayer.getId() == uPayee.getId()){
            throw new RuntimeException("Usuário pagador e beneficiário são a mesma pessoa");
        }
        //Valida se o token de autorização é válido
        if(!externalAuthorizationService.authorize()){
            throw new RuntimeException("Autorização falhou. Transferência não autorizada");
        }

        //Atualiza o saldo dos usuários
        uPayer.setBalance(uPayer.getBalance().subtract(valueTransfer)); 
        userRepository.save(uPayer);
        uPayee.setBalance(uPayee.getBalance().add(valueTransfer));
        userRepository.save(uPayee);

        //Realiza a transferência
        Transfer transfer = new Transfer(); 
        transfer.setPayer(uPayer);
        transfer.setPayee(uPayee);
        transfer.setValueTransfer(valueTransfer);
        transfer.setTransferedAt(LocalDateTime.now());
        transferRepository.save(transfer);
        
        //Notifica o usuário pagador
        notificationService.notify(uPayer, "Transferência realizada com sucesso");
        notificationService.notify(uPayee, "Transferência recebida com sucesso");

        return transfer;
    }
}
