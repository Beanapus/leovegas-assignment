# LeoVegas Coding Challenge Assignment

### Pre-requisites

* Java 17
* Maven

---

### Compile / Build

mvn clean install

---

### Run

mvn spring-boot:run

---

### Features

* Create account
* Add credit to account
* Debit account
* View Account details and Balance
* View all Accounts and Balances
* Transaction history for all Accounts, including unsuccessful transactions
* Unit tests to cover core features

#### Constraints

* All transaction, both credit and debit, must have unique id's. If not, an exception is thrown
* A debit transaction will be deemed unsuccessful if the debited amount is greater that the current balance

### Notes

* The JSON file containing the accounts can be found at: src/main/resources/accounts.json
* **Please do not** manually change values stored in the accounts.json file. The JSON file is acting as a database, and
  values should only be updated through API calls/requests
* The application will run on port 8080

### API Reference

---

#### Debit Account

* **URI**: localhost:8080/api/accounts/debit
* **Request Mapping**: POST
* **Request Body**: {JSON} :
    * **id**: {int},
    * **accountName**: {String},
    * **date**: {LocalDateTime} e.g : 2022-11-01T12:30:00,
    * **type**: {DEBIT},
    * **amount**: {int}

---

#### Credit Account

* **URI** : localhost:8080/api/accounts/credit
* **Request Mapping**: POST
* **Request Body** {JSON}:
    * **id**: {int},
    * **accountName**: {String},
    * **date**: {LocalDateTime} e.g : 2022-11-01T12:30:00,
    * **type**: {CREDIT},
    * **amount**: {int}

---

#### Get Account

* **URI** : localhost:8080/api/accounts/get
* **Request Mapping**: GET
* **Request Parameters**:
    * **accountName**: {String}

---

#### Get All Accounts

* **URI** : localhost:8080/api/accounts/getAll
* **Request Mapping**: GET

---

### Author: William McLaughlin