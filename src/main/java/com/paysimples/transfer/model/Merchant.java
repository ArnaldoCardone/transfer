package com.paysimples.transfer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Marcações Spring Boot
@Entity
@Table(name = "tb_merchants")
@Inheritance(strategy = InheritanceType.JOINED)

// Marcações lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User merchant;


}
