package com.vendingmachine.api.controller;

import com.vendingmachine.api.entity.Machine;
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
public class MachineControllerTest {
    
    @Mock
    private MachineService machineService;
    
    private MachineController sut;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        
        sut = new MachineController(machineService);
    }
    
    
    @Test
    public void shouldReturnStatusOkWhenGettingCurrentBalance() {
        when(machineService.getCurrentBalance()).thenReturn(0);
        
        ResponseEntity<Machine> response = sut.getCurrentBalance();
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
}
