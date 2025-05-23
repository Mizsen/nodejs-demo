import {  Express,Router } from 'express';
import { ProductController } from '../controllers/productController';

const router = Router();
const productController = new ProductController();

const setProductRoutes = (app: Express) => {
    app.use('/api/products', router);
    router.post('/', productController.createProduct.bind(productController));
    router.get('/:id', productController.getProduct.bind(productController));
    router.put('/:id', productController.updateProduct.bind(productController));
};

export default setProductRoutes;