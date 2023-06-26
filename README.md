# Vending Machine

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-program">About The Program</a>
      <ul>
		<li><a href="#purpose">Purpose</a></li>
		<li><a href="#constraints">Contraints</a></li>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#program-modules">Program Modules</a></li>
      </ul>
    </li> 
    <li><a href="#usage">Prepare Environment</a></li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>

## About The Program

### Purpose

This program represents a vending machine which tracks coins within the machine, solds products to users and refund coins if any after purchase.

### Constraints

1. Vending Machine Initializer initialize machine with below default repositories.
   
* 10 quantitites for all product types are inserted.
* 100 quantities for all coin types are inserted.


### Built With

* This program is written in Java 11.
* Spring Boot Framework is used for building REST API.
* Maven is used as build tool.

### Program Modules

The program consists of 2 modules.

1) Vending Machine Api
   * API module includes REST API codes.
   * Allow usage to users via browser.

2) Vending Machine Runner
   * Runner module includes command-line program codes.
   * Allow interactive test-harness via command-line.

## Prepare Environment

Clone the repository

```
git@github.com:baharaydogdu/vending-machine.git
```

Navigate to vending-machine project folder

```
cd vending-machine/
```

Build project

```
mvn clean install
```

Run project
```
mvn spring-boot:run
```

## Usage
### Command-Line Program

Option menu:

![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/a7e7ad45-3197-4705-88ef-8c0ced06ba79)

1. List Available Products
   Lists all products including their price and quantity within the machine.
   
   ![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/da19ca30-796c-4c76-ae91-935c93ccc12a)

2. List Machine Coins
   Lists all coins including their quantities within the machine.
   
   ![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/e4eef60c-f71a-481e-bf10-7ff6d2f8a604)

3. Check Current Balance
   Returns current balance of the user.
   
   ![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/0eab4942-3c42-4d3a-a818-267850b465a6)

4. Insert Coin
   Inserts given coin into the machine and save it for the current balance of user.

   ![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/dc7abe64-4ddf-40fc-b19e-1ad192873e02)

5. Buy Product
   Buys given product if user has enough credit and machine has that product. After purchase, the current balance of user and product quantity within the machine are updated.

   ![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/5af70ca6-9b24-441d-b552-0f14cb3168b4)

6. Refund
   Refunds as coins which are available within the machine.

   ![image](https://github.com/baharaydogdu/vending-machine/assets/47500612/3b7ff31e-e73d-4e46-a735-969611799d25)

7. Exit
   Shutdowns the machine.

### REST API

1) To run program by using browser, navigate to below urls

```
localhost:8080/products  
```
```
localhost:8080/product/buy/{id}
````
```
localhost:8080/coins/insert/{value}
````
```
localhost:8080/coins/refund
````
```
localhost:8080/machine/currentBalance
````


