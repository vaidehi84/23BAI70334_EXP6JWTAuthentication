# Experiment 6 — JWT Authentication Backend

**Student Name:** Vaidehi Sharma  

📌 Overview
A secure backend authentication system built with Spring Boot and Spring Security demonstrating JWT-based login, protected route access, and token invalidation — tested end-to-end using Postman.

⚙️ Tech Stack
TechnologyVersionPurposeJava17Core languageSpring Boot3.2.3Backend frameworkSpring Security6.2.2Security layerJWT (jjwt)0.11.5Token generation & validationH2 DatabaseRuntimeIn-memory user storeMaven3.9.xBuild tool

🗂️ Project Structure
src/main/java/com/example/jwt_demo/
│
├── controllers/
│   └── AuthController.java       ← /login · /protected · /logout
│
├── security/
│   ├── JwtUtil.java              ← Token generation & validation
│   ├── JwtFilter.java            ← Intercepts every request
│   ├── SecurityConfig.java       ← Spring Security configuration
│   └── TokenBlacklist.java       ← In-memory logout blacklist
│
└── JwtDemoApplication.java       ← Entry point

src/main/resources/
└── application.properties

screenshots/
├── 1_login_success.png
├── 2_protected_route.png
└── 3_logout.png

🔄 JWT Authentication Flow
  Client                          Server
    │                               │
    │── POST /login ───────────────▶│
    │   { username, password }      │ validate credentials
    │                               │ generate JWT token
    │◀── { token: "eyJ..." } ───────│
    │                               │
    │── GET /protected ────────────▶│
    │   Authorization: Bearer <token>│ JwtFilter validates
    │                               │ token signature + expiry
    │◀── { "Access Granted" } ──────│
    │                               │
    │── POST /logout ──────────────▶│
    │   Authorization: Bearer <token>│ token → blacklist
    │◀── { "Token Invalidated" } ───│

🚀 How to Run
bash# 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/Vaidehi_Exp6.git
cd Vaidehi_Exp6

# 2. Run the application
mvn spring-boot:run

Server starts at http://localhost:8083

ValueUsernameadminPasswordadmin123

📡 API Endpoints
MethodEndpointAuth RequiredDescriptionPOST/login❌ NoAuthenticate and receive JWTGET/protected✅ BearerAccess secured routePOST/logout✅ BearerInvalidate JWT token

🧪 Postman Testing Guide
1️⃣ Login — Get JWT Token
httpPOST http://localhost:8083/login
Content-Type: application/json
json{
  "username": "admin",
  "password": "admin123"
}
✅ Response
json{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi..."
}

2️⃣ Protected Route — Access with Token
httpGET http://localhost:8083/protected
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
✅ Response
json{
  "message": "Access granted to protected route!",
  "user": "admin",
  "status": "authenticated"
}

3️⃣ Logout — Invalidate Token
httpPOST http://localhost:8083/logout
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
✅ Response
json{
  "message": "Logged out successfully. Token invalidated."
}
