# KLP Technology API

REST API for managing app users and retrieving Norwegian county information with Spring Boot. This application provides endpoints for user management and county data retrieval with validation and documentation.

## Features
- CRUD operations for app users
- Norwegian county information retrieval
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

### App Users

#### Create User
```bash
curl -X POST http://localhost:8080/api/v1/app-users \
  -H 'Content-Type: application/json' \
  -d '{
    "email": "user@example.com",
    "type": "USER"
  }'
```

#### Get All Users (Paginated)
```bash
# Get first page with default size (10 items)
curl http://localhost:8080/api/v1/app-users

# Get second page with 5 items
curl 'http://localhost:8080/api/v1/app-users?page=1&size=5'
```

#### Get User by ID
```bash
curl http://localhost:8080/api/v1/app-users/10000
```

#### Filter Users by Type
```bash
# Get users with type filter
curl 'http://localhost:8080/api/v1/app-users/user?type-filter=USER'

# Get all users (no filter)
curl http://localhost:8080/api/v1/app-users/user
```

### County Information

#### Get County by county number
```bash
# Get Oslo county
curl http://localhost:8080/county/03

# Get Vestland county
curl http://localhost:8080/county/46
```

## Sample Data
The application initializes with 5 sample users:
- john.doe@example.com (USER)
- jane.smith@example.com (ADMIN)
- bob.wilson@example.com (USER)
- alice.brown@example.com (ADMIN)
- charlie.davis@example.com (USER)

## Validation Rules
### App Users
- Email: Required, must be valid email format
- Type: Required, must be either "USER" or "ADMIN"

### County
- County Number: Must be a valid Norwegian county number (e.g., "03" for Oslo)