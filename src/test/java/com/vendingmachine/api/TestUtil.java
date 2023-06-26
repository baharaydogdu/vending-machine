package com.vendingmachine.api;

import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    
    
    public static List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "test1", 10, 5 ));
        products.add(new Product(2L, "test2", 15, 5));
        return products;
    }
    
    public static Product createProductWithZeroQuantity() {
        return new Product(Long.valueOf("1"), "test1", 10, 0 );
    }
    
    public static List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin("100", 5));
        coins.add(new Coin("5", 5));
        return coins;
    }
}
