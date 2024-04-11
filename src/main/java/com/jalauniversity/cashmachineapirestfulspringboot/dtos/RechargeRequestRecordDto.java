package com.jalauniversity.cashmachineapirestfulspringboot.dtos;

import jakarta.validation.constraints.NotNull;

public record RechargeRequestRecordDto(@NotNull int moneyQuantity) {

    public RechargeRequestRecordDto(int moneyQuantity) {
        this.moneyQuantity = moneyQuantity;
    }

    public int getMoneyQuantity() {
        return moneyQuantity;
    }
}

