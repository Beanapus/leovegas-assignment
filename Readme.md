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


### Notes
* The JSON file containing the accounts can be found at: src/main/resources/accounts.json
* **Please do not** manually change values stored in the accounts.json file. The JSON file is acting as a database, and values should only be updated through API calls/requests
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