class FlashSale {
    id: string;
    productId: string;
    startTime: Date;
    endTime: Date;
    discount: number;
    participants: string[];

    constructor(id: string, productId: string, startTime: Date, endTime: Date, discount: number) {
        this.id = id;
        this.productId = productId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
        this.participants = [];
    }

    isActive(): boolean {
        const now = new Date();
        return now >= this.startTime && now <= this.endTime;
    }

    validate(): boolean {
        if (!this.id || !this.productId || !this.discount) {
            return false;
        }
        if (this.startTime >= this.endTime) {
            return false;
        }
        return true;
    }
}

export default FlashSale;