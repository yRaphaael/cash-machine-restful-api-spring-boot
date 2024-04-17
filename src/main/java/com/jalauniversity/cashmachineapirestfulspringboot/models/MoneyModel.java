package com.jalauniversity.cashmachineapirestfulspringboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_MONEY")
public class MoneyModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MONEY")
    private UUID idMoney;

    @Column(name = "MONEY_VALUE")
    private double moneyValue;

    @Column(name = "MONEY_QUANTITY")
    private int moneyQuantity;

    @Column(name = "MONEY_CREATED_AT")
    private LocalDateTime moneyCreatedAt;

    @Column(name = "MONEY_UPDATED_AT")
    private LocalDateTime moneyUpdatedAt;


    public MoneyModel() {

    }

    public UUID getIdMoney() {
        return idMoney;
    }

    public void setIdMoney(UUID idMoney) {
        this.idMoney = idMoney;
    }

    public double getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(double moneyValue) {
        this.moneyValue = moneyValue;
    }

    public int getMoneyQuantity() {
        return moneyQuantity;
    }

    public void setMoneyQuantity(int moneyQuantity) {
        this.moneyQuantity = moneyQuantity;
    }

    public LocalDateTime getMoneyCreatedAt() {
        return moneyCreatedAt;
    }

    public void setMoneyCreatedAt(LocalDateTime moneyCreatedAt) {
        this.moneyCreatedAt = moneyCreatedAt;
    }

    public LocalDateTime getMoneyUpdatedAt() {
        return moneyUpdatedAt;
    }

    public void setMoneyUpdatedAt(LocalDateTime moneyUpdatedAt) {
        this.moneyUpdatedAt = moneyUpdatedAt;
    }
}
