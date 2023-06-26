package com.vendingmachine.api.service;

import com.vendingmachine.api.TestUtil;
import com.vendingmachine.api.entity.Product;
import com.vendingmachine.api.exception.NotEnoughCreditException;
import com.vendingmachine.api.exception.ProductNotAvailableException;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.repository.ProductRepository;
import com.vendingmachine.api.service.impl.ProductServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.when;

@Test
public class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CoinService coinService;
    @Mock
    private MachineService machineService;
    
    private ProductService sut;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        
        sut = new ProductServiceImpl(productRepository, coinService, machineService);
    }
    
    @Test
    public void shouldReturnAvailableProducts() {
        List<Product> products = TestUtil.createProducts();
        when(productRepository.findAll()).thenReturn(products);
        
        List<Product> availableProducts = sut.getAvailableProducts();
    
        Assert.assertEquals(products, availableProducts);
    }
    
    @Test(expectedExceptions = ProductNotAvailableException.class)
    public void shouldNotRemoveProductWhenProductDoesNotExist() throws VendingMachineException {
        Product product = TestUtil.createProductWithZeroQuantity();
        when(productRepository.findByName(product.getName())).thenReturn(product);
        
        sut.removeProduct(product.getName());
    }
    
    public void shouldBuyGivenProductById() throws VendingMachineException {
        List<Product> products = TestUtil.createProducts();
        when(productRepository.findAll()).thenReturn(products);
        when(machineService.getCurrentBalance()).thenReturn(500);
        when(productRepository.findById(Long.valueOf("1"))).thenReturn(java.util.Optional.of(products.get(0)));
        when(productRepository.findByName(products.get(0).getName())).thenReturn(products.get(0));
        
        sut.buyProductById("1");
        Mockito.verify(machineService).removeBalance(products.get(0).getPrice());
    }
    
    @Test(expectedExceptions = ProductNotAvailableException.class)
    public void shouldNotBuyWhenGivenProductDoesNotExist() throws VendingMachineException {
        Product product = TestUtil.createProductWithZeroQuantity();
        when(productRepository.findById(Long.valueOf("1"))).thenReturn(java.util.Optional.of(product));
        
        sut.buyProductById("1");
    }
    
    @Test(expectedExceptions = NotEnoughCreditException.class)
    public void shouldNotBuyWhenCurrentBalanceIsNotEnough() throws VendingMachineException {
        List<Product> products = TestUtil.createProducts();
        when(productRepository.findAll()).thenReturn(products);
        when(productRepository.findById(Long.valueOf("1"))).thenReturn(java.util.Optional.of(products.get(0)));
        when(machineService.getCurrentBalance()).thenReturn(0);
        
        sut.buyProductById("1");
    }
}
