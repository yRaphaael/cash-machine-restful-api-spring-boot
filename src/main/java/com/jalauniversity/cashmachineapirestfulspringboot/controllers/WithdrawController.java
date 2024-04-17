package com.jalauniversity.cashmachineapirestfulspringboot.controllers;

import com.jalauniversity.cashmachineapirestfulspringboot.dtos.WithdrawRequestRecordDto;
import com.jalauniversity.cashmachineapirestfulspringboot.exceptions.MoneyServiceException;
import com.jalauniversity.cashmachineapirestfulspringboot.models.MoneyModel;
import com.jalauniversity.cashmachineapirestfulspringboot.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @PostMapping
    public ResponseEntity<?> withdrawMoney(@RequestBody WithdrawRequestRecordDto requestDto) {
        try {
            List<MoneyModel> withdrawnMoney = withdrawService.withdrawMoney(requestDto.amount());
            return ResponseEntity.ok(withdrawnMoney);
        } catch (MoneyServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

