package com.paysimples.transfer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Marcações Spring Boot
@Entity
@Table(name = "tb_transfers")
//@Inheritance(strategy = InheritanceType.JOINED)

//Marcações lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private User payee;

    @Column(name = "value_transfer", nullable = false)
    private BigDecimal valueTransfer;

    @Column(name = "transfered_at", nullable = false)
    private LocalDateTime transferedAt;
    
}
