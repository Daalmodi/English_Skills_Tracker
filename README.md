# English Skills Tracker

**English Skills Tracker** is a full-stack platform designed to help learners monitor, evaluate, and improve their English proficiency across the four core competencies: **Reading**, **Listening**, **Writing**, and **Speaking**—with personalized learning paths, instructor evaluations, and role-based access control (RBAC).

---

## Key Features

- **Core Competency Tracking:** Monitor progress across Reading, Listening, Writing, and Speaking skills.
- **Role-Based Access Control (RBAC):** Granular roles (`ROLE_ADMIN`, `ROLE_INSTRUCTOR`, `ROLE_STUDENT`) and permissions automatically seeded on startup via [`DataInitializer`](Backend/EnglishSkillTrackerCRUD/src/main/java/com/example/EnglishSkillTrackerCRUD/config/DataInitializer.java).
- **JWT Authentication & Security:** Stateless authentication powered by Spring Security, BCrypt password hashing, and JSON Web Tokens (`jjwt`).
- **User & Role Management:** Full CRUD operations for managing users, roles, and instructor-learner assignments.
- **Modern Angular Frontend:** Built with Angular 21, Angular Material, and SASS, featuring responsive views (`home-page`, `roles-page`) and interactive Material dialogs for user creation and updates.
- **Containerized Dev & Prod Workflows:** Multi-stage Dockerfiles and Docker Compose with live hot-reloading (`docker compose watch`) and a browser-accessible **MySQL Workbench** instance (via KasmVNC).

---

## Tech Stack

| Layer | Technology |
| :--- | :--- |
| **Frontend** | Angular 21, Angular Material, TypeScript 5.9, RxJS, SASS, Nginx (prod) |
| **Backend** | Java 21, Spring Boot 4.1.2 (Web, Data JPA, Security, Validation, DevTools), MapStruct, Lombok, JJWT |
| **Database** | MySQL 8.0, LinuxServer MySQL Workbench (Web UI) |
| **DevOps** | Docker, Docker Compose (with `develop.watch` hot reload) |
| **Documentation** | LaTeX RBAC & Agile Scrum specification (`latex/`) |

---

## Project Structure

```text
English_Skills_Tracker/
├── Backend/
│   └── EnglishSkillTrackerCRUD/        # Spring Boot 4 / Java 21 REST API
│       ├── src/main/java/.../
│       │   ├── auth/                   # JWT service, Auth controller & DTOs
│       │   ├── config/                 # Spring Security & RBAC DataInitializer
│       │   ├── controller/             # REST controllers (UserController)
│       │   ├── dto/                    # Data Transfer Objects
│       │   ├── entity/                 # JPA Entities (User, Role, Permission, Assignments)
│       │   ├── exceptions/             # Global exception handling
│       │   ├── model/                  # RoleEnum & PermissionEnum definitions
│       │   ├── repository/             # Spring Data JPA repositories
│       │   └── service/                # Business logic & MapStruct mappers
│       ├── Dockerfile                  # Multi-stage Dockerfile (dev, build, prod)
│       └── pom.xml
├── Frontend/
│   └── English-Skills-Tracker/         # Angular 21 Single Page Application
│       ├── src/app/
│       │   ├── components/             # Header, Footer, Card Template, User Table & Dialogs
│       │   ├── models/                 # TypeScript interfaces (users, skills)
│       │   ├── Pages/                  # Home page & Roles management page
│       │   └── services/               # HTTP services for API integration
│       ├── Dockerfile                  # Multi-stage Dockerfile (dev, build, Nginx prod)
│       └── package.json
├── latex/                              # RBAC Specification & Jira User Stories (LaTeX/PDF)
├── docker-compose.yml                  # Full-stack orchestration (MySQL, Workbench, Backend, Frontend)
└── .env.example                        # Environment variable template
```

---

## Role-Based Access Control (RBAC)

Roles and permissions are defined in [`RoleEnum`](Backend/EnglishSkillTrackerCRUD/src/main/java/com/example/EnglishSkillTrackerCRUD/model/RoleEnum.java) and [`PermissionEnum`](Backend/EnglishSkillTrackerCRUD/src/main/java/com/example/EnglishSkillTrackerCRUD/model/PermissionEnum.java) and automatically initialized on application startup:

| Role | Description | Assigned Permissions |
| :--- | :--- | :--- |
| **`ROLE_ADMIN`** | Administrator with full access to manage users, roles, and system settings | `USER_READ`, `USER_CREATE`, `USER_UPDATE`, `USER_DELETE`, `ROLE_ASSIGN`, `ASSIGNMENT_CREATE`, `ASSIGNMENT_READ`, `ASSIGNMENT_DELETE` |
| **`ROLE_INSTRUCTOR`** | Instructor who manages assigned learners, evaluates skills, and tracks progress | `ASSIGNMENT_READ`, `SKILL_EVALUATE`, `SKILL_READ`, `SKILL_UPDATE`, `PROGRESS_READ_ALL`, `LESSON_MANAGE`, `LESSON_READ` |
| **`ROLE_STUDENT`** | Learner who accesses skill tracking, views assigned lessons, and tracks personal progress | `SKILL_READ`, `PROGRESS_READ_OWN`, `LESSON_READ` |

> Detailed RBAC user stories and permission matrices are documented in [`latex/english_skills_tracker_rbac_user_stories.pdf`](latex/english_skills_tracker_rbac_user_stories.pdf).

---

## Getting Started

### Prerequisites

- **Docker** & **Docker Compose** (recommended for running the full stack)
- *Optional (for local non-Docker development):*
  - **Java 21** & **Maven 3.9+**
  - **Node.js 22+** & **npm**
  - **MySQL 8.0**

### 1. Configure Environment Variables

Copy the `.env.example` file to `.env` and customize the credentials and ports as needed:

```bash
cp .env.example .env
```

| Variable | Default (`.env.example`) | Description |
| :--- | :--- | :--- |
| `MYSQL_ROOT_PASSWORD` | `tu_contraseña_segura_aqui` | MySQL root password |
| `MYSQL_DATABASE` | `englishskilltracker` | Database name |
| `MYSQL_USER` | `daniel` | Application database user |
| `MYSQL_PASSWORD` | `tu_contraseña_segura_aqui` | Application database password |
| `MYSQL_PORT` | `3307` | Host port mapped to MySQL `3306` |
| `WORKBENCH_HTTP_PORT` | `3000` | MySQL Workbench Web UI (HTTP) |
| `WORKBENCH_HTTPS_PORT` | `3001` | MySQL Workbench Web UI (HTTPS) |
| `BACKEND_PORT` | `8080` | Spring Boot REST API port |
| `FRONTEND_PORT` | `4200` | Angular Frontend port |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` | Hibernate schema generation strategy |
| `JWT_SECRET` | `tu_clave_secreta_jwt_aqui` | Secret key (Base64) for signing JWT tokens |
| `JWT_EXPIRATION` | `36000` | JWT expiration time in milliseconds |
| `TZ` | `America/Bogota` | Container timezone |

### 2. Run with Docker Compose

Start all services (`mysql`, `mysql-workbench`, `backend`, `frontend`):

```bash
docker compose up --build -d
```

To enable real-time file syncing and automatic rebuilds during development:

```bash
docker compose watch
```

#### Service URLs

- **Frontend (Angular):** `http://localhost:4200`
- **Backend API (Spring Boot):** `http://localhost:8080`
- **MySQL Workbench (Browser UI):** `http://localhost:3000` (or `https://localhost:3001`)
- **MySQL Database:** `localhost:3307` (internal Docker network host: `mysql:3306`)

---

## Running Locally (Without Docker)

### Backend (`Backend/EnglishSkillTrackerCRUD`)

```bash
cd Backend/EnglishSkillTrackerCRUD
./mvnw spring-boot:run
```

Run backend unit tests:

```bash
./mvnw test
```

### Frontend (`Frontend/English-Skills-Tracker`)

```bash
cd Frontend/English-Skills-Tracker
npm install
npm start
```

Run frontend unit tests:

```bash
npm test
```

---

## REST API Endpoints

### Authentication (`/api/v1/auth`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/auth/loggin` | Authenticate user and receive a JWT (`AuthResponseDTO`) |
| `POST` | `/api/v1/auth/register` | Register a new user account (`ROLE_STUDENT` by default) |
| `GET` | `/api/v1/auth/profile` | Retrieve the authenticated user's profile |

### Users (`/api/v1/users`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/users` | Retrieve all users |
| `POST` | `/api/v1/users` | Create a new user |
| `PUT` | `/api/v1/users/{id}` | Update an existing user by UUID |
| `DELETE` | `/api/v1/users/{id}` | Delete a user by UUID |
