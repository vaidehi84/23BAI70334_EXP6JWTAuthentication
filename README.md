<div align="center">

<img src="https://img.shields.io/badge/Experiment-6-ff6b35?style=for-the-badge" />
<img src="https://img.shields.io/badge/Status-Completed-success?style=for-the-badge" />

# 🔐 JWT Authentication System

> *Secure. Stateless. Production-Ready.*

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-0.11.5-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.2.2-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![H2](https://img.shields.io/badge/H2-Database-0000BB?style=for-the-badge&logo=h2&logoColor=white)

<br/>

**👩‍💻 Vaidehi Sharma &nbsp;|&nbsp; FullStack Development 2026**

</div>

---

## 📌 What is this?

A **secure REST API backend** implementing JWT Authentication from scratch using Spring Boot + Spring Security. Users can log in, receive a signed token, access protected routes, and invalidate tokens on logout.

---

## ✨ Features

- 🔑 &nbsp; **Login** with username & password → receive signed JWT
- 🛡️ &nbsp; **Protected routes** accessible only with valid token
- 🚪 &nbsp; **Logout** with token blacklisting (in-memory)
- ⚡ &nbsp; **Stateless** — zero server-side sessions
- 🔒 &nbsp; **Spring Security** with custom JWT filter chain

---

## ⚙️ Tech Stack

| Technology | Version | Role |
|:---|:---:|:---|
| ☕ Java | 17 | Core language |
| 🍃 Spring Boot | 3.2.3 | Backend framework |
| 🔒 Spring Security | 6.2.2 | Security layer |
| 🔑 JWT (jjwt) | 0.11.5 | Token generation & validation |
| 🗄️ H2 Database | Runtime | In-memory user store |
| 🔧 Maven | 3.9.x | Build tool |

---

## 🗂️ Project Structure
```
📦 jwt-demo
 ┣ 📂 src/main/java/com/example/jwt_demo
 ┃ ┣ 📂 controllers
 ┃ ┃ ┗ 📄 AuthController.java     ← /login · /protected · /logout
 ┃ ┣ 📂 security
 ┃ ┃ ┣ 📄 JwtUtil.java            ← Token generate & validate
 ┃ ┃ ┣ 📄 JwtFilter.java          ← Intercepts every request
 ┃ ┃ ┣ 📄 SecurityConfig.java     ← Spring Security setup
 ┃ ┃ ┗ 📄 TokenBlacklist.java     ← Logout blacklist
 ┃ ┗ 📄 JwtDemoApplication.java   ← Entry point
 ┣ 📂 src/main/resources
 ┃ ┗ 📄 application.properties
 ┗ 📂 screenshots
   ┣ 🖼️ 1_login_success.png
   ┣ 🖼️ 2_protected_route.png
   ┗ 🖼️ 3_logout.png
```

---

## 🔄 JWT Flow
```
  CLIENT                          SERVER
    │                               │
    │──── POST /login ─────────────▶│
    │   { username + password }     │  ✅ validate credentials
    │                               │  🔑 generate JWT token
    │◀─── { token: "eyJ..." } ──────│
    │                               │
    │──── GET /protected ──────────▶│
    │   Authorization: Bearer <jwt> │  🛡️ JwtFilter validates
    │                               │  ✅ signature + expiry OK
    │◀─── { "Access Granted" } ─────│
    │                               │
    │──── POST /logout ────────────▶│
    │   Authorization: Bearer <jwt> │  🚫 token → blacklist
    │◀─── { "Token Invalidated" } ──│
```

---

## 🚀 Getting Started
```bash
# Clone
git clone https://github.com/YOUR_USERNAME/Vaidehi_Exp6.git
cd Vaidehi_Exp6

# Run
mvn spring-boot:run
```

> 🌐 Server starts at **`http://localhost:8083`**
```
Username  →  admin
Password  →  admin123
```

---

## 📡 API Reference

| Method | Endpoint | Auth | Description |
|:---:|:---|:---:|:---|
| `POST` | `/login` | ❌ | Authenticate → receive JWT |
| `GET` | `/protected` | ✅ Bearer | Access secured endpoint |
| `POST` | `/logout` | ✅ Bearer | Blacklist & invalidate token |

---

## 🧪 Postman Guide

<details>
<summary><b>1️⃣ Login — Get JWT Token</b></summary>
```http
POST http://localhost:8083/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

✅ **Response**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi..."
}
```
</details>

<details>
<summary><b>2️⃣ Protected Route — Access with Token</b></summary>
```http
GET http://localhost:8083/protected
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

✅ **Response**
```json
{
  "message": "Access granted to protected route!",
  "user": "admin",
  "status": "authenticated"
}
```
</details>

<details>
<summary><b>3️⃣ Logout — Invalidate Token</b></summary>
```http
POST http://localhost:8083/logout
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

✅ **Response**
```json
{
  "message": "Logged out successfully. Token invalidated."
}
```
</details>

---

## 📸 Screenshots

| Login — JWT Token | Protected Route | Logout |
|:---:|:---:|:---:|
| ![Login](screenshots/1_login_success.png) | ![Protected](screenshots/2_protected_route.png) | ![Logout](screenshots/3_logout.png) |

---

## 🎯 Key Concepts

| | Concept | How |
|:---:|:---|:---|
| 🔑 | Token Generation | HS256 signed, 1 hour expiry |
| 🛡️ | Token Validation | `JwtFilter` on every request |
| 🚫 | Token Blacklisting | In-memory `HashSet` on logout |
| ⚡ | Stateless Auth | No server-side sessions |
| 🔒 | Route Security | Spring Security filter chain |

---

<div align="center">

*Experiment 6 &nbsp;·&nbsp; FullStack Development 2026 &nbsp;·&nbsp; Vaidehi Sharma*

</div>
