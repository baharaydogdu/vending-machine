package com.vendingmachine.api.service;

import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.exception.VendingMachineException;

import java.util.List;

public interface ProductService {
    
    List<Product> getAvailableProducts();
    
    void addProducts(List<Product> products);
    
    void removeProduct(String name) throws VendingMachineException;
    
    int buyProductById(String id) throws VendingMachineException;
}
