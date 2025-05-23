export class Product {
    id: number;
    name: string;
    price: number;
    stock: number;

    constructor(id: number, name: string, price: number, stock: number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    checkStockAvailability(quantity: number): boolean {
        return this.stock >= quantity;
    }

    updateStock(quantity: number): void {
        this.stock -= quantity;
    }
}