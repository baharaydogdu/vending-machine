package com.vendingmachine.api.controller;

import com.vendingmachine.api.TestUtil;
import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.service.ProductService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.when;

@Test
public class ProductControllerTest {
    
    @Mock
    private ProductService productService;
    
    private ProductController sut;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        
        sut = new ProductController(productService);
    }
    
    
    @Test
    public void shouldReturnStatusOkWhenGettingAvailableProducts() {
        List<Product> products = TestUtil.createProducts();
        when(productService.getAvailableProducts()).thenReturn(products);
        
        ResponseEntity<Object> response = sut.getProducts();
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    @Test
    public void shouldReturnStatusOkWhenBuyingProduct() throws VendingMachineException {
        when(productService.buyProductById("1")).thenReturn(0);
        ResponseEntity<Object> response = sut.buyProductById("1");
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
