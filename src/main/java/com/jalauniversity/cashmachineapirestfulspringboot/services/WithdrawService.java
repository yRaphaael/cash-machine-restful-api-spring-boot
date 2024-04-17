package com.jalauniversity.cashmachineapirestfulspringboot.services;

import com.jalauniversity.cashmachineapirestfulspringboot.exceptions.MoneyServiceException;
import com.jalauniversity.cashmachineapirestfulspringboot.models.MoneyModel;
import com.jalauniversity.cashmachineapirestfulspringboot.repositories.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WithdrawService {

    @Autowired
    MoneyRepository moneyRepository;

    public List<MoneyModel> withdrawMoney(double amount) throws MoneyServiceException {
        if (amount <= 0) {
            throw new MoneyServiceException("O valor do saque está inválido");
        }

        List<MoneyModel> availableMoney = moneyRepository.findAll();
        availableMoney.sort(Comparator.comparingDouble(MoneyModel::getMoneyValue).reversed());

        double remainingAmount = amount;
        List<MoneyModel> withdrawnMoney = new ArrayList<>();

        for (MoneyModel money : availableMoney) {
            int quantityToWithdraw = (int) (remainingAmount / money.getMoneyValue());
            if (quantityToWithdraw > 0 && quantityToWithdraw <= money.getMoneyQuantity()) {
                withdrawnMoney.add(money);
                remainingAmount -= quantityToWithdraw * money.getMoneyValue();
                money.setMoneyQuantity(money.getMoneyQuantity() - quantityToWithdraw);
                moneyRepository.save(money);
            }
        }

        if (remainingAmount > 0) {
            throw new MoneyServiceException("Não é possível sacar o valor solicitado com as notas disponíveis");
        }

        return withdrawnMoney;
    }
}
