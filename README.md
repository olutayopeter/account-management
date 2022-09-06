# Account Management using Java8, Spring Boot and H2 DB

RESTful API to simulate account management operations. 

## Requirements

* Creation of a new account:Account owner name, phone. This operation should return a unique ten-digit account number.
* Deposit: Account number and the amount to deposit. This operation should return the total balance in the account and the amount deposited.
* Withdrawal: Account number and the amount to withdraw. This operation should return the balance amount withdrawn and the account balance.
* Transactions: Account number (optional), if given account number, return all transactions related to the account. If there is no account number, return all the transactions in the system. Sort the transactions by the most recent. Write the sorting algorithm to sort the transactions by createdAt (unix timestamp)




## Getting Started

1. Checkout the project from GitHub

```
git clone https://github.com/olutayopeter/account-management

```
2. Enable Lombok support on your IDE

Refer to the following link for instructions:

```
https://projectlombok.org/setup/eclipse

```
3. Open IDE of your choice and Import as existing maven project in your workspace

```
- Import existing maven project
- Run mvn clean install
- If using STS, Run As Spring Boot App

```
4. Default port for the api is 4000


### Prerequisites

* Java 8
* Intellij,Spring Tool Suite 4 or similar IDE
* [Maven](https://maven.apache.org/) - Dependency Management

### Maven Dependencies

```
spring-boot-starter-actuator
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-web
spring-boot-devtools
h2 - Inmemory database
lombok - to reduce boilerplate code
springfox-swagger2
springfox-swagger-ui
spring-boot-starter-test
spring-security-test

```

## Swagger

Please find the Rest API documentation in the below url

```
http://localhost:4000/swagger-ui/

```

## H2 In-Memory Database

Make sure to use jdbc:h2:mem:testdb as your jdbc url. If you intend to you use custom database name, please
define datasource properties in application.properties

```
http://localhost:4000/h2-console/

```

## Testing the Account Management APP Rest Api

1. Please use the Swagger url to perform CRUD operations. 

2. Browse to <project-root>/src/test/resources to find sample requests to add customer and accounts.


## Authors

* **Olutayo Adelodun**

## Postman collection
I have included postman collection of the test conducted
link: https://www.getpostman.com/collections/de4b5b7c829d1163a605

