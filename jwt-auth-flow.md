
# ✅ JWT Authentication & Authorization in Spring Boot

---

## 🔐 Real-World Analogy: Airport Security Check

| Component                     | Real-Life Equivalent                    |
|------------------------------|------------------------------------------|
| 👤 `Client`                  | Passenger                                |
| 🔐 `AuthController`          | Check-in Counter                         |
| 🧠 `AuthenticationManager`   | Immigration/ID Verification Officer      |
| 🗄️ `UserRepository`          | Government ID Records                    |
| 📦 `JwtUtil`                 | Boarding Pass/Stamp Generator            |
| 🧪 `JwtAuthenticationFilter` | Security Gate checking your pass         |
| 📂 `ProtectedController`     | The Boarding Gate to board your flight   |

---

## 📌 Simulation

- 👤 `bharath` logs in with password → receives JWT (like a boarding pass).
- 🔐 AuthController issues the token after verifying credentials.
- Client stores JWT and uses it to access protected endpoints.
- Token is validated by filter → access is granted only if valid.

---

## 🎯 Example

**Login Request:**

```json
POST /auth/login
{
  "username": "bharath",
  "password": "bharath123"
}
```

**Protected Request:**

```
GET /api/posts
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
```

---

## 🧬 Mermaid Sequence Diagram

### Success Scenario

```mermaid
sequenceDiagram
    actor 👤 Client as 👤 Client (Browser/Postman)
    participant 🔐 AuthController as 🔐 AuthController (Login API)
    participant 🧠 AuthenticationManager as 🧠 AuthenticationManager (Security Core)
    participant 🗄️ UserRepository as 🗄️ UserRepository (DB Access)
    participant 📦 JwtUtil as 📦 JwtUtil (Token Generator)
    participant 🧪 JwtAuthenticationFilter as 🧪 JwtAuthenticationFilter (Security Filter)
    participant 📂 ProtectedController as 📂 PostsController (Secure Resource)

    %% Login Flow
    👤 Client->>🔐 AuthController: POST /auth/login <br>Body: {"username": "bharath", "password": "bharath123"}
    🔐 AuthController->>🧠 AuthenticationManager: authenticate("bharath", "bharath123")
    🧠 AuthenticationManager->>🗄️ UserRepository: findByUsername("bharath")
    🗄️ UserRepository-->>🧠 AuthenticationManager: User found with role USER
    🧠 AuthenticationManager-->>🔐 AuthController: ✅ Authentication Success
    🔐 AuthController->>📦 JwtUtil: generateToken("bharath")
    📦 JwtUtil-->>🔐 AuthController: 🪪 JWT: eyJhbGciOiJIUzI1NiIs...
    🔐 AuthController-->>👤 Client: 200 OK <br>{ "token": "eyJhbGci..." }

    Note over 👤 Client: 💾 Stores JWT in localStorage or header

    %% Access Secure Resource
    👤 Client->>📂 ProtectedController: GET /api/posts <br>Headers: Authorization: Bearer eyJhbGci...
    📂 ProtectedController->>🧪 JwtAuthenticationFilter: Validate Token
    🧪 JwtAuthenticationFilter->>📦 JwtUtil: parse & validate token
    📦 JwtUtil-->>🧪 JwtAuthenticationFilter: ✅ Token valid <br>User: bharath, Role: USER
    🧪 JwtAuthenticationFilter-->>📂 ProtectedController: authenticatedUser = "bharath"
    📂 ProtectedController-->>👤 Client: 200 OK <br>[{"id": 1, "title": "Hello World!"}]
```

### Failure Scenario


```mermaid
sequenceDiagram
    actor 👤 Alice as 👤 Alice (Browser)
    actor 👨‍💻 Bob as 👨‍💻 Bob (Postman)
    participant 🔐 AuthController as 🔐 AuthController (Login API)
    participant 🧠 AuthenticationManager as 🧠 AuthenticationManager (Security Core)
    participant 🗄️ UserRepository as 🗄️ UserRepository (DB Access)
    participant 📦 JwtUtil as 📦 JwtUtil (Token Generator)
    participant 🧪 JwtAuthenticationFilter as 🧪 JwtAuthenticationFilter (Security Filter)
    participant 📂 ProtectedController as 📂 PostsController (Secure Resource)

    %% Alice logs in successfully
    👤 Alice->>🔐 AuthController: POST /auth/login <br>Body: {"username": "alice", "password": "correctpass"}
    🔐 AuthController->>🧠 AuthenticationManager: authenticate("alice", "correctpass")
    🧠 AuthenticationManager->>🗄️ UserRepository: findByUsername("alice")
    🗄️ UserRepository-->>🧠 AuthenticationManager: User found with role ADMIN
    🧠 AuthenticationManager-->>🔐 AuthController: ✅ Authentication Success
    🔐 AuthController->>📦 JwtUtil: generateToken("alice")
    📦 JwtUtil-->>🔐 AuthController: 🪪 JWT: eyJhbGciOiJIUzI1NiIs...
    🔐 AuthController-->>👤 Alice: 200 OK <br>{ "token": "eyJhbGci..." }

    Note over 👤 Alice: Stores JWT in browser storage

    %% Bob logs in with wrong password
    👨‍💻 Bob->>🔐 AuthController: POST /auth/login <br>Body: {"username": "bob", "password": "wrongpass"}
    🔐 AuthController->>🧠 AuthenticationManager: authenticate("bob", "wrongpass")
    🧠 AuthenticationManager->>🗄️ UserRepository: findByUsername("bob")
    🗄️ UserRepository-->>🧠 AuthenticationManager: User found
    🧠 AuthenticationManager-->>🔐 AuthController: ❌ Invalid Credentials
    🔐 AuthController-->>👨‍💻 Bob: 401 Unauthorized <br>{ "error": "Bad credentials" }

    %% Alice accesses secure endpoint
    👤 Alice->>📂 ProtectedController: GET /api/posts <br>Authorization: Bearer eyJhbGci...
    📂 ProtectedController->>🧪 JwtAuthenticationFilter: Intercept & validate token
    🧪 JwtAuthenticationFilter->>📦 JwtUtil: decode and verify token
    📦 JwtUtil-->>🧪 JwtAuthenticationFilter: ✅ Token valid for alice
    🧪 JwtAuthenticationFilter-->>📂 ProtectedController: User alice is authenticated
    📂 ProtectedController-->>👤 Alice: 200 OK <br>[{"id": 101, "title": "Welcome Alice!"}]

    %% Bob tries accessing with no token
    👨‍💻 Bob->>📂 ProtectedController: GET /api/posts
    📂 ProtectedController-->>👨‍💻 Bob: 403 Forbidden <br>{ "error": "Missing or invalid token" }

    %% Bob tries accessing with expired token
    👨‍💻 Bob->>📂 ProtectedController: GET /api/posts <br>Authorization: Bearer expiredToken123
    📂 ProtectedController->>🧪 JwtAuthenticationFilter: Intercept & validate token
    🧪 JwtAuthenticationFilter->>📦 JwtUtil: decode and verify token
    📦 JwtUtil-->>🧪 JwtAuthenticationFilter: ❌ Token expired
    🧪 JwtAuthenticationFilter-->>📂 ProtectedController: User unauthenticated
    📂 ProtectedController-->>👨‍💻 Bob: 401 Unauthorized <br>{ "error": "Token expired" }
```

---

## 🧠 Component Responsibilities

| Icon | Component                 | Responsibility |
|------|---------------------------|----------------|
| 🔐   | `AuthController`          | Login endpoint that issues token |
| 🧠   | `AuthenticationManager`   | Authenticates user credentials   |
| 🗄️   | `UserRepository`          | Fetches user details from DB     |
| 📦   | `JwtUtil`                 | Creates/parses/validates JWT     |
| 🧪   | `JwtAuthenticationFilter` | Intercepts requests and validates token |
| 📂   | `ProtectedController`     | Exposes protected resources      |

---

## ✅ Advantages of JWT

- ✅ **Stateless**: No session storage needed
- ✅ **Secure**: Signed & tamper-proof
- ✅ **Scalable**: No DB lookup needed on each request
- ✅ **Simple**: Easily used in Postman/Swagger

---

## 🔍 Swagger Testing

1. Call `/auth/login` to get a token.
2. Click **Authorize** in Swagger UI.
3. Paste `Bearer <your-jwt>` into the input.
4. Now call secured endpoints like `/api/posts`.
