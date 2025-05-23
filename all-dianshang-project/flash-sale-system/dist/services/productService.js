"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductService = void 0;
class ProductService {
    constructor() {
        this.products = [];
    }
    createProduct(product) {
        this.products.push(product);
        return product;
    }
    getProduct(id) {
        return this.products.find(product => product.id === id.toString());
    }
    updateProduct(id, updatedProduct) {
        const productIndex = this.products.findIndex(product => product.id === id.toString());
        if (productIndex !== -1) {
            this.products[productIndex] = Object.assign(Object.assign({}, this.products[productIndex]), updatedProduct);
            return this.products[productIndex];
        }
        return undefined;
    }
}
exports.ProductService = ProductService;
