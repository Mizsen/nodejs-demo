# Flash Sale System

This project is an e-commerce flash sale system that allows users to create and participate in flash sales for various products. It is built using TypeScript and Express.js.

## Features

- Create and manage flash sales
- Create and manage products
- Participate in flash sales
- RESTful API for easy integration

## Project Structure

```
flash-sale-system
├── src
│   ├── app.ts                     # Entry point of the application
│   ├── controllers
│   │   ├── flashSaleController.ts # Controller for flash sale operations
│   │   └── productController.ts    # Controller for product operations
│   ├── models
│   │   ├── flashSale.ts           # Model for flash sale data
│   │   └── product.ts             # Model for product data
│   ├── routes
│   │   ├── flashSaleRoutes.ts     # Routes for flash sale endpoints
│   │   └── productRoutes.ts       # Routes for product endpoints
│   ├── services
│   │   ├── flashSaleService.ts    # Service for flash sale business logic
│   │   └── productService.ts      # Service for product business logic
│   └── types
│       └── index.ts               # Type definitions for flash sales and products
├── package.json                   # NPM package configuration
├── tsconfig.json                  # TypeScript configuration
└── README.md                      # Project documentation
```

## Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd flash-sale-system
   ```

3. Install the dependencies:
   ```
   npm install
   ```

## Usage

1. Start the application:
   ```
   npm start
   ```

2. The API will be available at `http://localhost:3000`.

## API Endpoints

- **Flash Sale Endpoints**
  - `POST /flash-sales` - Create a new flash sale
  - `GET /flash-sales/:id` - Retrieve details of a flash sale
  - `POST /flash-sales/:id/participate` - Participate in a flash sale

- **Product Endpoints**
  - `POST /products` - Create a new product
  - `GET /products/:id` - Retrieve details of a product
  - `PUT /products/:id` - Update product information

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or features.

## License

This project is licensed under the MIT License.