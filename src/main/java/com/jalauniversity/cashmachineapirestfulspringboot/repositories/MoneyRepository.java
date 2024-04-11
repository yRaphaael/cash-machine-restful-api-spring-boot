package com.jalauniversity.cashmachineapirestfulspringboot.repositories;

import com.jalauniversity.cashmachineapirestfulspringboot.models.MoneyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyModel, UUID> {

    boolean existsByMoneyValue(double moneyValue);

}
