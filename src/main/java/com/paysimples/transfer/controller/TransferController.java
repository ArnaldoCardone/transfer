package com.paysimples.transfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paysimples.transfer.dto.TransferDTO;
import com.paysimples.transfer.model.Transfer;
import com.paysimples.transfer.service.ExternalAuthorizationService;
import com.paysimples.transfer.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/transfers")
public class TransferController {
    
    @Autowired
    private TransferService transferService;

    @Autowired
    private ExternalAuthorizationService externalAuthorizationService;

    @PostMapping("/create")
    public ResponseEntity<Transfer> postMethodName(@RequestBody TransferDTO transferDTO) {
        
        Transfer transfer = transferService.executeTransfer(transferDTO.getPayer(), transferDTO.getPayee(), transferDTO.getValue());
        return ResponseEntity.ok(transfer);
    }
    
    @GetMapping("/test-authorization")
    public boolean testAuthorization() {
        return externalAuthorizationService.authorize();
    }
    

}
