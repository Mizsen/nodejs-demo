# Blog System Backend

This is the backend component of the Blog System project, built using Java with Spring Boot and SQLite as the database.

## Features

- User authentication (login and registration)
- CRUD operations for blog posts
- Commenting system for blog posts
- Personal homepage for users to view their published blogs

## Project Structure

- `src/main/java/com/example/blog`: Contains the main application code.
  - `controller`: Handles HTTP requests and responses.
  - `model`: Defines the data structure of the application.
  - `repository`: Interfaces for database operations.
  - `service`: Contains business logic.
- `src/main/resources`: Contains configuration files.
  - `application.properties`: Configuration properties for the Spring Boot application.
  - `schema.sql`: SQL statements for initializing the database schema.
- `pom.xml`: Maven configuration file for dependencies and build settings.

## Getting Started

1. Clone the repository.
2. Navigate to the `backend` directory.
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn spring-boot:run
   ```
5. The application will be available at `http://localhost:8080`.

## Database

The application uses SQLite as the database. The schema can be initialized using the `schema.sql` file located in `src/main/resources`.

## API Endpoints

- **User Registration**: `POST /api/register`
- **User Login**: `POST /api/login`
- **Get All Blogs**: `GET /api/blogs`
- **Get Blog by ID**: `GET /api/blogs/{id}`
- **Create Blog**: `POST /api/blogs`
- **Update Blog**: `PUT /api/blogs/{id}`
- **Delete Blog**: `DELETE /api/blogs/{id}`
- **Add Comment**: `POST /api/blogs/{id}/comments`

## License

This project is licensed under the MIT License. See the LICENSE file for details.