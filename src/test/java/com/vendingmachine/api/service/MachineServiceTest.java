package com.vendingmachine.api.service;

import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.repository.MachineRepository;
import com.vendingmachine.api.service.impl.MachineServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Test
public class MachineServiceTest {
    
    @Mock
    MachineRepository machineRepository;
    
    private MachineServiceImpl sut;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        
        sut = new MachineServiceImpl(machineRepository);
    }
    
    @Test
    public void shouldAddBalance() {
        List<Machine> machine = new ArrayList<>();
        machine.add(new Machine(100));
        when(machineRepository.findAll()).thenReturn(machine);
        
        sut.addBalance(50);
        
        Assert.assertEquals(sut.getCurrentBalance(), 150);
    }
    
    @Test
    public void shouldRemoveBalance() {
        List<Machine> machine = new ArrayList<>();
        machine.add(new Machine(100));
        when(machineRepository.findAll()).thenReturn(machine);
        
        sut.removeBalance(40);
        
        Assert.assertEquals(sut.getCurrentBalance(), 60);
    }
}
