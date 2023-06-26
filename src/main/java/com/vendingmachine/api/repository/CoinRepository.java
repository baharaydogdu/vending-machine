package com.vendingmachine.api.repository;

import com.vendingmachine.api.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
    
    Coin findByName(String value);
}
