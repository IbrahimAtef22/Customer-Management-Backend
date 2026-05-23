# Customer-Management-Backend

## Project Overview

This project consists of **two separate applications** that work together:

1. **Backend** – Spring Boot REST API with full CRUD operations and a SQL database.
2. **Desktop Client** – Java Swing application that consumes the REST API.  
   **No direct database connection** – all data goes through the API.

This separation follows a real‑world clean architecture: backend handles data and business logic, desktop client focuses purely on the user interface.

---

## Project Structure

### Backend (Spring Boot) – main packages
- `controller` – REST endpoints (`/api/customers`)
- `service` – business logic
- `repository` – database access (Spring Data JPA)
- `model` – Customer model
- `exception` - handle exceptions
- `mapper` - map entity to dto and vise versa

## Technologies Used

| Component               | Backend                     | Desktop Client                |
|-------------------------|-----------------------------|-------------------------------|
| Language                | Java 17                     | Java 17                       |
| Framework / Libraries   | Spring Boot, Spring Data JPA| Swing, OkHttp, Jackson        |
| Build tool              | Maven                       | Maven                         |
| Database                | MySQL                       | None (uses REST API)          |
| HTTP client             | Spring Web (server side)    | OkHttp                        |
| JSON binding            | Jackson (auto)              | Jackson (with JavaTimeModule) |

---

## 1.2 Run the Backend
Open a terminal in the Customer-Management-Backend/ folder:

run this command in bash:

- mvn clean spring-boot:run

Or run the main method in CustomerMangBackendApplication.java from your IDE.

## The API will be available at:
- GET    http://localhost:8080/customers          Retrieve all customers
- GET    http://localhost:8080/customers/{id}     Retrieve a single customer by ID
- POST   http://localhost:8080/customers          Create a new customer
- PUT    http://localhost:8080/customers/{id}     Update an existing customer
- DELETE http://localhost:8080/customers/{id}     Delete a customer