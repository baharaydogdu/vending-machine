package com.vendingmachine.api.service.impl;

import com.vendingmachine.api.entity.Machine;
import com.vendingmachine.api.repository.MachineRepository;
import com.vendingmachine.api.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineServiceImpl implements MachineService {
    
    private MachineRepository machineRepository;
    
    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }
    
    @Override
    public int getCurrentBalance() {
        return machineRepository.findAll().get(0).getCurrentBalance();
    }
    
    @Override
    public void addBalance(int balance) {
        if(machineRepository.findAll().isEmpty()) {
            machineRepository.save(new Machine(balance));
        } else {
            Machine machine = machineRepository.findAll().get(0);
            machine.setCurrentBalance(machine.getCurrentBalance() + balance);
            machineRepository.saveAndFlush(machine);
        }
    }
    
    @Override
    public void removeBalance(int balance) {
        Machine machine = machineRepository.findAll().get(0);
        machine.setCurrentBalance(machine.getCurrentBalance() - balance);
        machineRepository.saveAndFlush(machine);
    }
}
