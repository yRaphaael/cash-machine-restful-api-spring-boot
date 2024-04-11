package com.jalauniversity.cashmachineapirestfulspringboot.controllers;


import com.jalauniversity.cashmachineapirestfulspringboot.dtos.MoneyRecordDto;
import com.jalauniversity.cashmachineapirestfulspringboot.dtos.RechargeRequestRecordDto;
import com.jalauniversity.cashmachineapirestfulspringboot.exceptions.MoneyServiceException;
import com.jalauniversity.cashmachineapirestfulspringboot.models.MoneyModel;
import com.jalauniversity.cashmachineapirestfulspringboot.services.MoneyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @PostMapping
    public MoneyModel saveMoney(@RequestBody MoneyRecordDto moneyRecordDto) throws MoneyServiceException {
        LocalDateTime createdAt = LocalDateTime.now();
        return moneyService.saveMoney(moneyRecordDto, createdAt);
    }

    @PostMapping("/recharge/{id}")
    public MoneyModel rechargeMoney(@PathVariable UUID id, @RequestBody RechargeRequestRecordDto requestDto) throws MoneyServiceException {
        return moneyService.rechargeMoney(id, requestDto.getMoneyQuantity());
    }



    @GetMapping("/{id}")
    public Optional<MoneyModel> getMoneyById(@PathVariable UUID id) throws MoneyServiceException {
        return moneyService.getMoneyByID(id);
    }

    @GetMapping
    public List<MoneyModel> getAllMoney() throws MoneyServiceException {
        return moneyService.getAllMoney();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoney(@PathVariable UUID id) throws MoneyServiceException {
        if (moneyService.deleteMoney(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new MoneyServiceException("Ballot not found.");
        }
    }
}

