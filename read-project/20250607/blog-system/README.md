# Blog System

This project is a full-stack blog system built with Vue 3 for the frontend and Java with Spring Boot for the backend, using SQLite as the database. The application includes features for user authentication, blog management, and commenting.

## Project Structure

```
blog-system
├── frontend          # Frontend application
│   ├── src
│   │   ├── assets                # Static assets (images, stylesheets)
│   │   ├── components            # Vue components
│   │   ├── views                 # Vue views
│   │   ├── router                # Vue Router configuration
│   │   ├── store                 # Vuex store for state management
│   │   ├── App.vue               # Root component
│   │   └── main.ts               # Entry point of the Vue application
│   ├── public                    # Public assets
│   ├── package.json              # Frontend dependencies and scripts
│   ├── tsconfig.json             # TypeScript configuration
│   └── README.md                 # Frontend documentation
├── backend                     # Backend application
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── blog
│   │   │   │               ├── BlogApplication.java  # Main entry point
│   │   │   │               ├── controller           # Controllers for handling requests
│   │   │   │               ├── model                # Data model classes
│   │   │   │               ├── repository           # Repository interfaces
│   │   │   │               └── service              # Service classes for business logic
│   │   │   └── resources
│   │   │       ├── application.properties  # Configuration properties
│   │   │       └── schema.sql            # Database schema
│   ├── pom.xml                  # Maven configuration
│   └── README.md                # Backend documentation
├── mock-server                  # Mock server for returning mock data
│   ├── src
│   │   └── index.js             # Mock server setup
│   ├── package.json             # Mock server dependencies and scripts
│   └── README.md                # Mock server documentation
└── README.md                   # Overall project documentation
```

## Features

- **User Authentication**: Registration and login functionality for users.
- **Personal Homepage**: Users can view their own blogs and profile information.
- **Blog Management**: Users can create, edit, and delete blog posts.
- **Blog List**: A main page displaying a list of all blogs.
- **Blog Details**: View individual blog posts along with comments.
- **Commenting**: Users can comment on blog posts.

## Getting Started

1. Clone the repository.
2. Navigate to the `frontend` directory and run `npm install` to install frontend dependencies.
3. Navigate to the `backend` directory and run `mvn install` to install backend dependencies.
4. Start the mock server in the `mock-server` directory using `npm start`.
5. Run the backend application and access the frontend application in your browser.

## Technologies Used

- **Frontend**: Vue 3, Vue Router, Vuex, TypeScript
- **Backend**: Java, Spring Boot, SQLite
- **Mock Server**: Node.js

## License

This project is licensed under the MIT License.