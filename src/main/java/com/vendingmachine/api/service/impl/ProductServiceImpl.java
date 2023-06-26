package com.vendingmachine.api.service.impl;

import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.exception.NotEnoughCreditException;
import com.vendingmachine.api.exception.ProductNotAvailableException;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.repository.ProductRepository;
import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import com.vendingmachine.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    private final ProductRepository productRepository;
    private final CoinService coinService;
    private final MachineService machineService;
    
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CoinService coinService, MachineService machineService) {
        this.productRepository = productRepository;
        this.coinService = coinService;
        this.machineService = machineService;
    }
    
    @Override
    public List<Product> getAvailableProducts() {
        logger.info("Get available products request is received.");
        return productRepository.findAll();
    }
    
    @Override
    public void addProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
    
    @Override
    public void removeProduct(String name) throws VendingMachineException {
        Product product= productRepository.findByName(name);
        if(product.getQuantity() == 0) {
            throw new ProductNotAvailableException("Product not available.");
        } else {
            int currentQuantity = product.getQuantity();
            product.setQuantity(currentQuantity-1);
            productRepository.saveAndFlush(product);
        }
    }
    
    @Override
    public int buyProductById(String id) throws VendingMachineException {
        Optional<Product> product = productRepository.findById(Long.valueOf(id));
        if(! product.isPresent() || product.get().getQuantity() == 0) {
            throw new ProductNotAvailableException("Product not available.");
        }
        if(machineService.getCurrentBalance() >= product.get().getPrice()) {
            removeProduct(product.get().getName());
            machineService.removeBalance(product.get().getPrice());
            logger.info("Product is bought successfully. Remaining balance: ");
            return machineService.getCurrentBalance();
        } else {
            throw new NotEnoughCreditException("Not enough credit.");
        }
    }
}
