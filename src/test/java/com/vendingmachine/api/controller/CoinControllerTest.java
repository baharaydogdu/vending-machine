package com.vendingmachine.api.controller;

import com.vendingmachine.api.service.CoinService;
import com.vendingmachine.api.service.MachineService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

@Test
public class CoinControllerTest {
    
    @Mock
    private CoinService coinService;
    @Mock
    private MachineService machineService;
    
    private CoinController sut;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        
        sut = new CoinController(coinService, machineService);
    }
    
    
    @Test
    public void shouldReturnStatusOkWhenGettingCurrentBalance() {
        when(machineService.getCurrentBalance()).thenReturn(100);
        
        ResponseEntity<Object> response = sut.insertCoin("100");
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
}
