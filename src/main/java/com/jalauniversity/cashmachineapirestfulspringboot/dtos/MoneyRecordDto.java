package com.jalauniversity.cashmachineapirestfulspringboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record MoneyRecordDto(@NotNull double moneyValue, @NotNull int moneyQuantity, @NotBlank LocalDateTime moneyCreatedAt, @NotBlank LocalDateTime moneyUpdatedAt) {

}
