# E-Commerce API — Product Catalog Service

A simple Spring Boot REST API that manages an in-memory product catalog. It provides CRUD operations, filtering, and basic validation.

## Features

- CRUD endpoints for products
- In-memory storage (ArrayList) with counter-based IDs
- Filtering by category, price, and name
- Input validation and global error handling

## Tech

- Java 17+ (project compiles with newer JVMs)
- Spring Boot
- Lombok
- Gradle

## Quickstart

1. Clone the repo:

```bash
git clone https://github.com/jairarayandayan240-del/Ecommerceapi.git
cd Ecommerceapi
```

2. Build:

```bash
./gradlew.bat build
```

3. Run:

```bash
./gradlew.bat bootRun
```

4. Open API (default):

- All products: http://localhost:8080/api/v1/products
- Example: open http://localhost:8080/ (redirects to products list)

## API Endpoints

- GET /api/v1/products — list all products
- GET /api/v1/products/{id} — get product by id
- GET /api/v1/products/filter?filterType=category&filterValue=Electronics — filter products
- POST /api/v1/products — create product (JSON body)
- PUT /api/v1/products/{id} — replace product
- PATCH /api/v1/products/{id} — partial update
- DELETE /api/v1/products/{id} — delete product

## Notes

- This project uses in-memory storage; data resets on restart.
- If you want `main` as default branch on GitHub, rename locally and push.

If you want, I can add example curl requests or a Postman collection.
# E-Commerce API - Product Catalog Service

## Project Overview

A RESTful API backend for an e-commerce product catalog built with Spring Boot. This API provides complete CRUD (Create, Read, Update, Delete) operations for managing products with in-memory data storage. The application demonstrates understanding of HTTP methods, status codes, headers, and REST principles.

### Features

- RESTful API endpoints following HTTP standards
- Complete CRUD operations for product management
- In-memory data storage with `ArrayList<Product>`
- Counter-based ID generation strategy
- Filtering capabilities (by category, price, name)
- Comprehensive input validation
- Global exception handling with consistent error responses
- Proper HTTP status codes and response headers
- Lombok annotations to reduce boilerplate code

### Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 25+ | Programming language |
| Spring Boot | 3.4.5 | Application framework |
| Spring Web | - | REST API endpoints |
| Lombok | - | Reduce boilerplate code |
| Gradle/Maven | - | Build automation |

### Project Structure


## Setup Instructions

### Prerequisites

- Java 25 or higher installed
- Git installed
- IDE (IntelliJ IDEA, VS Code, or Eclipse)
- Internet connection for dependency download

### Installation Steps

1. **Clone the repository**
```bash
git clone <your-repository-url>
cd ecommerce-api

