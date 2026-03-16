# Experiment 6 — JWT Authentication Backend

**Student Name:** Vaidehi Sharma  

---

📌 Overview
A secure backend authentication system built with Spring Boot and Spring Security that demonstrates JWT-based login, protected route access, and token invalidation — tested end-to-end using Postman.

⚙️ Tech Stack
TechnologyVersionPurposeJava17Core languageSpring Boot3.2.3Backend frameworkSpring Security6.2.2Security layerJWT (jjwt)0.11.5Token generation & validationH2 DatabaseRuntimeIn-memory user storeMaven3.9.xBuild tool

🗂️ Project Structure
src/main/java/com/example/jwt_demo/
│
├── controllers/
│   └── AuthController.java        ← /login  /protected  /logout
│
├── security/
│   ├── JwtUtil.java               ← Token generation & validation
│   ├── JwtFilter.java             ← Intercepts every request
│   ├── SecurityConfig.java        ← Spring Security config
│   └── TokenBlacklist.java        ← In-memory token blacklist
│
└── JwtDemoApplication.java

screenshots/
├── 1_login_success.png
├── 2_protected_route.png
└── 3_logout.png

🔄 JWT Flow
User → POST /login (credentials)
             ↓
       Server validates
             ↓
       JWT Token generated ───────────────────────┐
             ↓                                    │
       Token returned to client                   │
             ↓                                    │
User → GET /protected                             │
       Authorization: Bearer <token> ←────────────┘
             ↓
       JwtFilter validates token
             ↓
       ✅ Access Granted

🚀 How to Run
bashmvn spring-boot:run
Server → http://localhost:8083
CredentialValueUsernameadminPasswordadmin123

📡 API Endpoints
MethodEndpointAuthDescriptionPOST/login❌Login → get JWT tokenGET/protected✅Access protected routePOST/logout✅Invalidate token

🧪 Postman Testing
Step 1 — Login
jsonPOST http://localhost:8083/login

{
  "username": "admin",
  "password": "admin123"
}
json{ "token": "eyJhbGciOiJIUzI1NiJ9..." }
```

**Step 2 — Protected Route**
```
GET http://localhost:8083/protected
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
json{ "message": "Access granted to protected route!", "status": "authenticated" }
```

**Step 3 — Logout**
```
POST http://localhost:8083/logout
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
json{ "message": "Logged out successfully. Token invalidated." }


🎯 Key Concepts

🔑 JWT Generation — HS256 signed, expires in 1 hour
🛡️ JWT Validation — Every request intercepted by JwtFilter
🚫 Token Blacklisting — Logout adds token to in-memory blacklist
⚡ Stateless — Zero server-side session storage
🔒 Spring Security — CSRF disabled, routes properly secured

