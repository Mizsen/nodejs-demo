# Blog System Project

## Overview
This project is a blog system that allows users to register, log in, manage their personal homepage, create, edit, delete, like, and comment on blog articles. Users can also search for blogs and view others' blogs. The project is built using Vue.js and Element UI for the frontend, and Spring Boot with SQLite3 for the backend.

## Project Structure
The project is organized into two main parts: the backend and the frontend.

### Backend
- **Language**: Java (Spring Boot)
- **Database**: SQLite3
- **Main Files**:
  - `BlogSystemApplication.java`: Entry point of the Spring Boot application.
  - `controller/`: Contains controller classes for handling HTTP requests.
  - `model/`: Contains model classes representing data structures.
  - `repository/`: Contains repository interfaces for data access.
  - `service/`: Contains service classes implementing business logic.
  - `application.properties`: Configuration settings for the application.
  - `schema.sql`: SQL statements for creating the initial database schema.

### Frontend
- **Framework**: Vue.js with Element UI
- **Main Files**:
  - `index.html`: Main HTML file for the Vue.js application.
  - `api/`: Contains API service files for handling HTTP requests to the backend.
  - `assets/`: Contains static assets such as images and stylesheets.
  - `components/`: Contains reusable Vue components.
  - `router/`: Contains routing configuration for the application.
  - `store/`: Contains Vuex store files for state management.
  - `views/`: Contains view components for different pages.
  - `App.vue`: Root Vue component for the application.

## Setup Instructions

### Backend Setup
1. Clone the repository.
2. Navigate to the `backend` directory.
3. Ensure you have Java and Maven installed.
4. Run the application using the command:
   ```
   mvn spring-boot:run
   ```
5. The backend will be available at `http://localhost:8080`.

### Frontend Setup
1. Navigate to the `frontend` directory.
2. Ensure you have Node.js and npm installed.
3. Install the dependencies using the command:
   ```
   npm install
   ```
4. Start the development server using the command:
   ```
   npm run dev
   ```
5. The frontend will be available at `http://localhost:3000`.

## Features
- User registration and login
- Personal homepage management
- Blog article creation, editing, and deletion
- Liking and commenting on blog articles
- Searching for blogs
- Viewing others' blogs

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

## License
This project is licensed under the MIT License.