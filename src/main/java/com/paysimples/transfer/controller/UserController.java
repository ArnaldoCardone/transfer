package com.paysimples.transfer.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paysimples.transfer.dto.MerchantDTO;
import com.paysimples.transfer.model.Merchant;
import com.paysimples.transfer.model.User;
import com.paysimples.transfer.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated= userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }
    
    @PostMapping("/create-merchant")
    public ResponseEntity<Merchant> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        Merchant merchantCreated = userService.createMerchant(merchantDTO);
        
        return ResponseEntity.ok(merchantCreated);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
}
