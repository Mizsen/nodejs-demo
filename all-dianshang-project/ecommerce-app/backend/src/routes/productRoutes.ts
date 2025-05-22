import { Router } from 'express';
import ProductController from '../controllers/productController';

const router = Router();
const productController = new ProductController();

export function setProductRoutes(app: Router) {
    app.get('/api/products', productController.getAllProducts.bind(productController));
    app.get('/api/products/:id', productController.getProductById.bind(productController));
}

export default router;