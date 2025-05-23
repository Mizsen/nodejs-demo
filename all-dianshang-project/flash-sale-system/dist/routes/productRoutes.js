"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const productController_1 = require("../controllers/productController");
const router = (0, express_1.Router)();
const productController = new productController_1.ProductController();
const setProductRoutes = (app) => {
    app.use('/api/products', router);
    router.post('/', productController.createProduct.bind(productController));
    router.get('/:id', productController.getProduct.bind(productController));
    router.put('/:id', productController.updateProduct.bind(productController));
};
exports.default = setProductRoutes;
