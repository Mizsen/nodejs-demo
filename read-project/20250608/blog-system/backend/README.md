# Blog System Backend

## Overview
This is the backend for the Blog System project, built using Spring Boot and SQLite3. The backend provides RESTful APIs for user registration, login, blog management, commenting, and liking functionalities.

## Project Structure
- **src/main/java/com/example/blog**: Contains the main application code.
  - **BlogSystemApplication.java**: Entry point of the Spring Boot application.
  - **controller**: Handles HTTP requests and responses.
  - **model**: Represents data structures for users, blogs, comments, and likes.
  - **repository**: Interfaces for data access, extending Spring Data JPA repositories.
  - **service**: Implements business logic for user and blog management.

- **src/main/resources**: Contains configuration and schema files.
  - **application.properties**: Configuration settings for the application.
  - **schema.sql**: SQL statements to create the initial database schema.

## Setup Instructions
1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd blog-system/backend
   ```

2. **Build the project**:
   Use Maven to build the project:
   ```
   mvn clean install
   ```

3. **Run the application**:
   You can run the application using:
   ```
   mvn spring-boot:run
   ```

4. **Database Setup**:
   The application uses SQLite3. Ensure that the database is set up according to the schema defined in `schema.sql`.

## API Endpoints
- **User Registration**: `POST /api/users/register`
- **User Login**: `POST /api/users/login`
- **Create Blog**: `POST /api/blogs`
- **Edit Blog**: `PUT /api/blogs/{id}`
- **Delete Blog**: `DELETE /api/blogs/{id}`
- **Like Blog**: `POST /api/blogs/{id}/like`
- **Comment on Blog**: `POST /api/blogs/{id}/comments`
- **Search Blogs**: `GET /api/blogs/search?query={query}`
- **View User Blogs**: `GET /api/users/{id}/blogs`

## Technologies Used
- Spring Boot
- SQLite3
- Spring Data JPA

## License
This project is licensed under the MIT License. See the LICENSE file for more details.