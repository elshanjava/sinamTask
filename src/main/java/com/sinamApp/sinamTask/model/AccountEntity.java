package com.sinamApp.sinamTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity userId;
    private Currencies currency;
    private Long accountNumber;
    private Double balance;
    private AccountStatuses status;


}
