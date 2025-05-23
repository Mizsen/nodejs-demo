"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.FlashSaleService = void 0;
class FlashSaleService {
    constructor() {
        this.flashSales = [];
    }
    createFlashSale(flashSale) {
        this.flashSales.push(flashSale);
    }
    getFlashSale(id) {
        return this.flashSales.find(flashSale => flashSale.id === id);
    }
    participateInFlashSale(id, userId) {
        const flashSale = this.getFlashSale(id);
        if (!flashSale) {
            return 'Flash sale not found';
        }
        // Logic to participate in the flash sale
        return `User ${userId} participated in flash sale ${id}`;
    }
    getAllFlashSales() {
        return this.flashSales;
    }
}
exports.FlashSaleService = FlashSaleService;
