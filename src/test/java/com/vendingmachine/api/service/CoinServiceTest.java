package com.vendingmachine.api.service;

import com.vendingmachine.api.TestUtil;
import com.vendingmachine.api.entity.Coin;
import com.vendingmachine.api.exception.CoinNotValidException;
import com.vendingmachine.api.exception.VendingMachineException;
import com.vendingmachine.api.repository.CoinRepository;
import com.vendingmachine.api.service.impl.CoinServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.when;

@Test
public class CoinServiceTest {
    
    @Mock
    CoinRepository coinRepository;
    @Mock
    MachineService machineService;
    
    private CoinServiceImpl sut;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        
        sut = new CoinServiceImpl(coinRepository, machineService);
    }
    
    @Test
    public void shouldInsertCoin() throws VendingMachineException {
        List<Coin> coins = TestUtil.createCoins();
        when(coinRepository.findByName("5")).thenReturn(coins.get(1));
        
        sut.insertCoin("5");
        
        Assert.assertEquals(coins.get(1).getQuantity(), 6);
    }
    
    @Test(expectedExceptions = CoinNotValidException.class)
    public void shouldNotInsertCoin() throws VendingMachineException {
        List<Coin> coins = TestUtil.createCoins();
        when(coinRepository.findByName("5")).thenReturn(coins.get(1));
    
        sut.insertCoin("6");
    
        Assert.assertEquals(coins.get(1).getQuantity(), 6);
    }
    
    @Test
    public void shouldRemoveCoin() {
        List<Coin> coins = TestUtil.createCoins();
        when(coinRepository.findByName("5")).thenReturn(coins.get(1));
    
        sut.removeCoin("5");
    
        Assert.assertEquals(coins.get(1).getQuantity(), 4);
    }
}
