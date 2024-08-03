# Catalog Management Spring Boot Application

## Overview
This project is a simple Catalog Management System built using Spring Boot. It provides RESTful APIs to manage products including creation, retrieval, updating, and deletion.

## Prerequisites
- Java 11 or higher
- Maven 3.6.3 or higher
- MySQL 8.0 or higher

## Setup Instructions

1. **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd catalog-management
    ```

2. **Set up the MySQL Database:**
    - Create a new database named `catalog_db`:
      ```sql
      CREATE DATABASE catalog_db;
      ```

3. **Configure database connection:**
    - Update the `src/main/resources/application.properties` file with your MySQL credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/catalog_db
      spring.datasource.username=<your_mysql_username>
      spring.datasource.password=<your_mysql_password>
      spring.jpa.hibernate.ddl-auto=update
      ```

4. **Build the project:**
    ```bash
    mvn clean install
    ```

5. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Create a Product
- **URL:** `/api/products`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "name": "Product Name",
      "brand": "Brand Name",
      "description": "Product Description",
      "price": 100.0,
      "quantity": 10,
      "category": "Category"
  }
