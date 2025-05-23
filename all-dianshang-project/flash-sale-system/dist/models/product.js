"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Product = void 0;
class Product {
    constructor(id, name, price, stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    checkStockAvailability(quantity) {
        return this.stock >= quantity;
    }
    updateStock(quantity) {
        this.stock -= quantity;
    }
}
exports.Product = Product;
