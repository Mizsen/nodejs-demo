"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class FlashSale {
    constructor(id, productId, startTime, endTime, discount) {
        this.id = id;
        this.productId = productId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
        this.participants = [];
    }
    isActive() {
        const now = new Date();
        return now >= this.startTime && now <= this.endTime;
    }
    validate() {
        if (!this.id || !this.productId || !this.discount) {
            return false;
        }
        if (this.startTime >= this.endTime) {
            return false;
        }
        return true;
    }
}
exports.default = FlashSale;
