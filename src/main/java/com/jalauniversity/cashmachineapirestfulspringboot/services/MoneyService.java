package com.jalauniversity.cashmachineapirestfulspringboot.services;

import com.jalauniversity.cashmachineapirestfulspringboot.repositories.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {

    @Autowired
    MoneyRepository moneyRepository;


}
