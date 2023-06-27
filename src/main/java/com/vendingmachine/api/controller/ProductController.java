package com.vendingmachine.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.response.ResponseHandler;
import com.vendingmachine.api.response.ResponseMessage;
import com.vendingmachine.api.service.ProductService;
import com.vendingmachine.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping (path="/products", produces = "application/json")
    public ResponseEntity<Object> getProducts() {
        List<Product> products = productService.getAvailableProducts();
        return ResponseHandler.generateSuccessResponse(ResponseMessage.PRODUCT_LIST_RETURNED_SUCCESSFULLY.getMessage(), HttpStatus.OK, products);
    
    }
    
    
    @GetMapping (path="/products/buy/{id}", produces = "application/json")
    public ResponseEntity<Object> buyProductById(@PathVariable ("id") @Validated final String id) {
        try {
            int currentBalance = productService.buyProductById(id);
            JsonNode responseData = JsonUtil.convertObjectWithoutField(new Machine(currentBalance), "id");
            return ResponseHandler.generateSuccessResponse(ResponseMessage.PRODUCT_BOUGHT_SUCCESSFULLY.getMessage(), HttpStatus.OK, responseData);
        }
        catch (VendingMachineException e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

}
