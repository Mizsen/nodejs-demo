# Mock Server for Blog System

This mock server is designed to simulate the backend API for the blog system built with Vue 3, Java, and SQLite. It provides endpoints for user authentication, blog management, and commenting functionalities.

## Features

- **User Authentication**: Mock endpoints for user registration and login.
- **Blog Management**: Endpoints to create, edit, delete, and retrieve blog posts.
- **Commenting System**: Endpoints to add and retrieve comments for blog posts.

## Getting Started

1. **Clone the Repository**:
   ```
   git clone <repository-url>
   cd blog-system/mock-server
   ```

2. **Install Dependencies**:
   ```
   npm install
   ```

3. **Run the Mock Server**:
   ```
   npm start
   ```

   The server will start on `http://localhost:3000` by default.

## API Endpoints

- **User Registration**: `POST /api/register`
- **User Login**: `POST /api/login`
- **Get All Blogs**: `GET /api/blogs`
- **Get Blog by ID**: `GET /api/blogs/:id`
- **Create Blog**: `POST /api/blogs`
- **Edit Blog**: `PUT /api/blogs/:id`
- **Delete Blog**: `DELETE /api/blogs/:id`
- **Add Comment**: `POST /api/blogs/:id/comments`
- **Get Comments for Blog**: `GET /api/blogs/:id/comments`

## Contributing

Feel free to submit issues or pull requests to improve the mock server functionality.

## License

This project is licensed under the MIT License.