# Spring Boot Quiz Application

This is a simple **Quiz Application** built with Spring Boot. The project demonstrates a clean architecture with layered components, making it an ideal starting point for beginners or those looking to implement a similar application. 

## Features
- Create, read, update, and delete (CRUD) operations for quiz entities.
- Organized structure with Controller, Service, Repository, and Model layers.
- Custom exception handling for better error reporting.
- Modular design for scalability and maintainability.

## Project Structure
- `configuration`: Contains configuration files.
- `controller`: Handles incoming HTTP requests and directs them to appropriate services.
- `exception`: Includes custom exception classes for error handling.
- `model`: Defines the domain models used in the application.
- `repository`: Interfaces for database interactions (using Spring Data JPA).
- `service`: Contains business logic for the application.

## Technologies Used
- **Java 21**
- **Spring Boot 3.2.1**
- **Spring Data JPA**
- **PostgreSQL** (runtime database)
- **Maven** for dependency management
- **Lombok** to reduce boilerplate code
- **Spring DevTools** for live reload during development
- **Spring Boot Starter Test** for testing
