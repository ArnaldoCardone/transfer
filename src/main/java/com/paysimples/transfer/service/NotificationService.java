package com.paysimples.transfer.service;

import org.springframework.stereotype.Service;

import com.paysimples.transfer.model.User;

@Service
public class NotificationService {
    
    public boolean notify(User user, String message){
        System.out.println("Notification: " + message);
        return true;
    }
}
