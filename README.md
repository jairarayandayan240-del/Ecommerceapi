 # E-Commerce API – Secured with Spring Security

## Project Overview

This is the same product catalog API from previous labs, now secured with:
- **Session‑Based Authentication** (form login with JSESSIONID cookie)
- **Role‑Based Access Control** (USER / ADMIN)
- **Bean Validation** for robust input validation
- **CSRF Protection** enabled for write operations
- **Custom Login & Registration Pages** (Thymeleaf + static HTML)

## Security Architecture

- The application uses **Spring Security’s form login** with HTTP sessions.
- Upon successful login (POST `/login`), the server creates a session and sends a `JSESSIONID` cookie.
- The browser automatically includes this cookie on subsequent requests, authenticating the user.
- **CSRF** protection is active for all state‑changing operations; the custom login page includes the CSRF token automatically via Thymeleaf.
- Registration (`POST /api/v1/auth/register`) is CSRF‑free so that unauthenticated users can sign up.
- Passwords are hashed with **BCryptPasswordEncoder** before storage.

## Validation Rules

| Entity / DTO | Field | Constraint |
|---|---|---|
| CreateProductDto | name | `@NotBlank` |
| CreateProductDto | description | `@Size(max=500)` |
| CreateProductDto | price | `@Positive` |
| CreateProductDto | categoryName | `@NotBlank` |
| CreateProductDto | stockQuantity | `@PositiveOrZero` |
| Register request | username | checked manually (not blank, unique) |
| Register request | password | checked manually (min 6 characters) |

Validation errors are returned as a structured JSON response (400 Bad Request) with a list of field‑specific messages.

## API Endpoints (updated for Lab 9)

| Method | Endpoint | Auth Required | Role |
|--------|----------|---------------|------|
| GET | `/api/v1/products` | No | Public |
| GET | `/api/v1/products/{id}` | No | Public |
| GET | `/api/v1/products/filter` | No | Public |
| POST | `/api/v1/products` | Yes | ADMIN |
| PUT | `/api/v1/products/{id}` | Yes | ADMIN |
| PATCH | `/api/v1/products/{id}` | Yes | ADMIN |
| DELETE | `/api/v1/products/{id}` | Yes | ADMIN |
| POST | `/api/v1/auth/register` | No | Public |
| GET | `/api/v1/auth/me` | Yes | Any |
| POST | `/login` | CSRF token | Public |
| POST | `/logout` | CSRF token | Authenticated |

## Screenshots

_Add your own screenshots here. Suggested images:_

- **Database** – `users` and `products` tables with data (MySQL Workbench).
- **Registration** – successful registration message.
- **Login** – custom login page with Thymeleaf.
- **Product Catalog** – products displayed with images and logged‑in user info.
- **Error Handling** – validation error when submitting invalid data (e.g., negative price).