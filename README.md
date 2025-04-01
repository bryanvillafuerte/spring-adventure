# Spring Adventure API

REST API for managing app users with Spring Boot. This application provides endpoints for creating and retrieving user data with validation and documentation.

## Features
- CRUD operations for app users
- Input validation with error messages
- OpenAPI/Swagger documentation
- H2 in-memory database
- Initial sample data loading
- Global exception handling
- Pagination support
- Type-based filtering

## Tech Stack
- Java 17
- Spring Boot 3.x
- Maven
- H2 Database
- SpringDoc OpenAPI

## Getting Started
1. Clone the repository
2. Run `mvn spring-boot:run`
3. Access Swagger UI at `http://localhost:8080/swagger-ui.html`
4. H2 Console available at `http://localhost:8080/h2-console`

### Database Credentials
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## API Endpoints

### Create User
```bash
POST /api/v1/app-users
Content-Type: application/json

{
    "email": "user@example.com",
    "type": "USER"
}
```

### Get All Users (Paginated)
```bash
GET /api/v1/app-users?page=0&size=10
```

### Get User by ID
```bash
GET /api/v1/app-users/{id}
```

### Filter Users by Type
```bash
GET /api/v1/app-users/user?type-filter=USER
```

## Sample Data
The application initializes with 5 sample users:
- john.doe@example.com (USER)
- jane.smith@example.com (ADMIN)
- bob.wilson@example.com (USER)
- alice.brown@example.com (ADMIN)
- charlie.davis@example.com (USER)

## Validation Rules
- Email: Required, must be valid email format
- Type: Required, must be either "USER" or "ADMIN"