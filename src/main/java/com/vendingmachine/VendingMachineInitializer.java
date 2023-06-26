package com.vendingmachine;

import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.entity.model.CoinType;
import com.vendingmachine.api.entity.model.ProductType;
import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import com.vendingmachine.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VendingMachineInitializer implements ApplicationRunner {
    
    private ProductService productService;
    private CoinService coinService;
    private MachineService machineService;
    private final int INITIAL_PRODUCT_QUANTITY = 10;
    private final int INITIAL_COIN_QUANTITY = 100;
    private final int INITIAL_BALANCE = 0;
    
    @Autowired
    public VendingMachineInitializer(ProductService productService, CoinService coinService, MachineService machineService) {
        this.productService = productService;
        this.coinService = coinService;
        this.machineService = machineService;
    }
    
    public void run(ApplicationArguments args) {
        addInitialProducts();
        addInitialCoins();
        setInitialBalance();
    }
    
    private void addInitialProducts() {
        List<Product> initialProducts = new ArrayList<>();
        Arrays.stream(ProductType.values()).forEach(productType -> {
            initialProducts.add(new Product(productType.getName(), productType.getPrice(), INITIAL_PRODUCT_QUANTITY));
        });
        productService.addProducts(initialProducts);
    }
    
    private void addInitialCoins() {
        List<Coin> initialCoins = new ArrayList<>();
        Arrays.stream(CoinType.values()).forEach(coinType -> {
            initialCoins.add(new Coin(coinType.getName(), INITIAL_COIN_QUANTITY));
        });
        coinService.addCoins(initialCoins);
    }
    
    private void setInitialBalance() {
        machineService.addBalance(INITIAL_BALANCE);
    }
}

