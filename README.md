# Experiment 6 — JWT Authentication Backend

**Student Name:** Vaidehi Sharma  
**Course:** FullStack Development 2026  

---

## Project Overview

This project implements **JWT (JSON Web Token) Authentication** using **Spring Boot** and **Spring Security**.  
It demonstrates secure login, protected route access, and token invalidation (logout).

## JWT Authentication Flow

1. User sends `POST /login` with username and password
2. Server validates credentials and returns a signed JWT token
3. Client sends the token in `Authorization: Bearer <token>` header
4. Spring Security filter validates the token on every protected request
5. On logout, token is added to an in-memory blacklist

## Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.3 |
| Spring Security | 6.2.2 |
| JWT (jjwt) | 0.11.5 |
| H2 Database | Runtime |
| Maven | 3.9.x |

## Project Structure

```
src/main/java/com/example/jwt_demo/
├── controllers/
│   └── AuthController.java       ← Login, Protected, Logout endpoints
├── security/
│   ├── JwtUtil.java              ← Token generation & validation
│   ├── JwtFilter.java            ← Intercepts & validates requests
│   ├── SecurityConfig.java       ← Spring Security configuration
│   └── TokenBlacklist.java       ← In-memory logout/blacklist
├── HomeController.java
└── JwtDemoApplication.java
src/main/resources/
└── application.properties
screenshots/
├── 1_login_success.png
├── 2_protected_route.png
└── 3_logout.png
```

## How to Run

```bash
mvn spring-boot:run
```

Server starts at: `http://localhost:8083`

**Default credentials:**
- Username: `admin`
- Password: `admin123`

## API Endpoints

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | /login | No | Login and get JWT token |
| GET | /protected | Yes | Access protected route |
| POST | /logout | Yes | Invalidate token |

## Postman Testing

### Step 1 — Login
```
POST http://localhost:8083/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

### Step 2 — Access Protected Route
```
GET http://localhost:8083/protected
Authorization: Bearer <your_jwt_token>
```

### Step 3 — Logout
```
POST http://localhost:8083/logout
Authorization: Bearer <your_jwt_token>
```

## Screenshots

Stored in `screenshots/` folder:
- `1_login_success.png` — Successful login with JWT token
- `2_protected_route.png` — Protected route accessed with token
- `3_logout.png` — Token invalidation on logout
