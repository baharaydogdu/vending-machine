package com.vendingmachine.runner;

import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import com.vendingmachine.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class VendingMachineRunner implements ApplicationRunner {
    
    private ProductService productService;
    private CoinService coinService;
    private MachineService machineService;
    
    @Autowired
    public VendingMachineRunner(ProductService productService, CoinService coinService, MachineService machineService) {
        this.productService = productService;
        this.coinService = coinService;
        this.machineService = machineService;
    }
    
    public void run(ApplicationArguments args) {
    
        Scanner enter = new Scanner(System.in);
        System.out.println("---------- VENDING MACHINE STARTED----------");
    
        optionMenu(enter);
    }
    
    private void optionMenu(Scanner enter) {
        printOptionMenu();
        String choice = enter.next();
        
        switch(choice) {
        case "1":
            System.out.println(productService.getAvailableProducts());
            optionMenu(enter);
        case "2":
            System.out.println(coinService.getMachineCoins());
            optionMenu(enter);
        case "3":
            System.out.println("Current balance: " + machineService.getCurrentBalance());
            optionMenu(enter);
        case "4":
            System.out.println(" Please type coin amount as penny:");
            String coin = enter.next();
            insertCoin(coin);
            optionMenu(enter);
        case "5":
            System.out.println(productService.getAvailableProducts());
            System.out.println(" Please type the id of product you would like to buy:");
            String productId = enter.next();
            buyProductById(productId);
            optionMenu(enter);
        case "6":
            System.out.println("Below coins are refunded:");
            System.out.println(coinService.refundCoins());
            optionMenu(enter);
        case "7":
            System.out.println("CHEERS..");
            System.exit(0);
        }
    }
    
    private void buyProductById(String productId) {
        String message;
        try {
            productService.buyProductById(productId);
            message = String.format("Product is bought successfully. Current balance: <%s>", machineService.getCurrentBalance());
        } catch (VendingMachineException e) {
            message = String.format("Product could not be bought. Error message: <%s>", e.getMessage());
        }
        System.out.println(message);
    }
    
    private void insertCoin(String coin){
        String message;
        try {
            coinService.insertCoin(coin);
            message = String.format("Coin is inserted. Current balance: <%s>", machineService.getCurrentBalance());
        } catch (VendingMachineException e) {
            message = String.format("Coin could not be inserted. Error message: <%s>", e.getMessage());
        }
        System.out.println(message);
    }
    
    private void printOptionMenu() {
        System.out.println("\nPlease select an option: ");
        System.out.println(" 1. List Available Products");
        System.out.println(" 2. List Machine Coins");
        System.out.println(" 3. Check Current Balance");
        System.out.println(" 4. Insert Coin");
        System.out.println(" 5. Buy Product");
        System.out.println(" 6. Refund");
        System.out.println(" 7. Exit");
    }
    
}

