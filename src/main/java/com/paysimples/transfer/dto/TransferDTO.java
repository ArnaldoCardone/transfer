package com.paysimples.transfer.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class TransferDTO {
    private BigDecimal value;
    private long payer;
    private long payee;

}
