export interface FlashSale {
    id: string;
    productId: string;
    startTime: Date;
    endTime: Date;
    discount: number;
}

export interface Product {
    id: string;
    name: string;
    price: number;
    stock: number;
}