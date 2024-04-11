package com.jalauniversity.cashmachineapirestfulspringboot.services;

import com.jalauniversity.cashmachineapirestfulspringboot.dtos.MoneyRecordDto;
import com.jalauniversity.cashmachineapirestfulspringboot.exceptions.MoneyServiceException;
import com.jalauniversity.cashmachineapirestfulspringboot.models.MoneyModel;
import com.jalauniversity.cashmachineapirestfulspringboot.repositories.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
public class MoneyService {

    @Autowired
    MoneyRepository moneyRepository;

    public MoneyModel saveMoney(MoneyRecordDto moneyRecordDto, LocalDateTime creadtedAt) throws MoneyServiceException {
        validateMoneyRecordDto(moneyRecordDto);

        if (moneyRepository.existsByMoneyValue(moneyRecordDto.moneyValue())) {
            throw new MoneyServiceException("This ballot informed has already been created");
        }

        MoneyModel moneyModel = new MoneyModel();
        moneyModel.setMoneyQuantity(moneyRecordDto.moneyQuantity());
        moneyModel.setMoneyValue(moneyRecordDto.moneyValue());
        moneyModel.setMoneyCreatedAt(creadtedAt);
        moneyModel.setMoneyUpdatedAt(LocalDateTime.now());

        return moneyRepository.save(moneyModel);
    }

    public MoneyModel rechargeMoney(UUID id, int quantity) throws MoneyServiceException {
        if (quantity <= 0) {
            throw new MoneyServiceException("The recharge amount must be positive");
        }

        Optional<MoneyModel> moneyModelOptional = moneyRepository.findById(id);

        if (!moneyModelOptional.isPresent()) {
            throw new MoneyServiceException("Ballot not found.");
        }

        MoneyModel moneyModel = moneyModelOptional.get();
        moneyModel.setMoneyQuantity(moneyModel.getMoneyQuantity() + quantity);
        moneyModel.setMoneyUpdatedAt(LocalDateTime.now());

        return moneyRepository.save(moneyModel);
    }

    private void validateMoneyRecordDto(MoneyRecordDto moneyRecordDto) throws MoneyServiceException {
        if (moneyRecordDto.moneyQuantity() <= 0) {
            throw new MoneyServiceException("Ballot Quantity invalid");
        }
        if (moneyRecordDto.moneyValue() <= 0) {
            throw new MoneyServiceException("Ballot Value invalid");
        }
    }

    public Optional<MoneyModel> getMoneyByID(UUID id) {
        return moneyRepository.findById(id);
    }

    public List<MoneyModel> getAllMoney(){
        return moneyRepository.findAll();
    }

    public boolean deleteMoney(UUID id){
        Optional<MoneyModel> moneyOP = moneyRepository.findById(id);
        if(moneyOP.isPresent()){
            moneyRepository.delete(moneyOP.get());
            return true;
        } else {
            return false;
        }
    }

}
