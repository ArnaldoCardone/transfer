package com.paysimples.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class ExternalAuthorizationService {
    
    private static String AUTHORIZATION_URL = "https://util.devi.tools/api/v2/authorize";

    @Autowired
    private RestTemplate restTemplate;

    public boolean authorize(){
        
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(AUTHORIZATION_URL, String.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                //TO-DO - Mudar para MAP o tratamento do retorno {"status":"success","data":{"authorization":true}}
                if (responseEntity.getBody() != null){
                return  responseEntity.getBody().contains("authorization");
                }
            }
            return false;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao chamar serviço de autorização externo");
        }
        
        
    }
}