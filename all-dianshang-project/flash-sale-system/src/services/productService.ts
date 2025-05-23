
import { Product } from '../types';
export class ProductService {
    private products: Product[] = [];

    createProduct(product: Product): Product {
        this.products.push(product);
        return product;
    }

    getProduct(id: number): Product | undefined {
        return this.products.find(product => product.id === id.toString());
    }

    updateProduct(id: number, updatedProduct: Partial<Product>): Product | undefined {
        const productIndex = this.products.findIndex(product => product.id === id.toString());
        if (productIndex !== -1) {
            this.products[productIndex] = { ...this.products[productIndex], ...updatedProduct };
            return this.products[productIndex];
        }
        return undefined;
    }
}