package com.vendingmachine.api.entity.model;

public enum CoinType {
    
    ONE_PENNY("1"),
    TWO_PENCE("2"),
    FIVE_PENCE("5"),
    TEN_PENCE("10"),
    TWENTY_PENCE("20"),
    FIFTY_PENCE("50"),
    ONE_POUND("100"),
    TWO_POUND("200");
    
    
    private String name;
    
    CoinType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
