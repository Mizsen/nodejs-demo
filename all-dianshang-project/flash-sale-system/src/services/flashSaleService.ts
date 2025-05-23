export class FlashSaleService {
    private flashSales: any[] = [];

    createFlashSale(flashSale: any): void {
        this.flashSales.push(flashSale);
    }

    getFlashSale(id: number): any | undefined {
        return this.flashSales.find(flashSale => flashSale.id === id);
    }

    participateInFlashSale(id: number, userId: number): string {
        const flashSale = this.getFlashSale(id);
        if (!flashSale) {
            return 'Flash sale not found';
        }
        // Logic to participate in the flash sale
        return `User ${userId} participated in flash sale ${id}`;
    }

    getAllFlashSales(): any[] {
        return this.flashSales;
    }
}