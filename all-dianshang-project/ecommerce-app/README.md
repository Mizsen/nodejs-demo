# Ecommerce App

This is a full-stack e-commerce application built with TypeScript, Express for the backend, and React for the frontend. The application allows users to view a list of products and retrieve details about specific products.

## Project Structure

The project is organized into two main directories: `backend` and `frontend`.

### Backend

The backend is built using Express and TypeScript. It handles API requests related to products.

- **src/app.ts**: Entry point of the backend application. Sets up the Express app, middleware, and routes.
- **src/controllers/productController.ts**: Contains the `ProductController` class with methods to get all products and a specific product by ID.
- **src/models/product.ts**: Defines the `Product` model with properties such as id, name, description, price, and stock.
- **src/routes/productRoutes.ts**: Exports a function to set up product-related routes using the `ProductController`.
- **package.json**: Lists the dependencies and scripts for the backend.
- **tsconfig.json**: TypeScript configuration file specifying compilation options.

### Frontend

The frontend is built using React and TypeScript. It provides a user interface for interacting with the product data.

- **src/App.tsx**: Entry component of the frontend application. Sets up routing and global state management.
- **src/components/ProductList.tsx**: Exports the `ProductList` component, responsible for displaying a list of products.
- **src/pages/Home.tsx**: Exports the `Home` component, which serves as the main page and includes the `ProductList` component.
- **package.json**: Lists the dependencies and scripts for the frontend.
- **tsconfig.json**: TypeScript configuration file specifying compilation options.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the backend directory and install dependencies:
   ```
   cd ecommerce-app/backend
   npm install
   ```

3. Start the backend server:
   ```
   npm start
   ```

4. In a new terminal, navigate to the frontend directory and install dependencies:
   ```
   cd ecommerce-app/frontend
   npm install
   ```

5. Start the frontend application:
   ```
   npm start
   ```

## Features

- View a list of products.
- Retrieve details of a specific product by ID.

## Technologies Used

- TypeScript
- Express
- React
- Node.js

## License

This project is licensed under the MIT License.