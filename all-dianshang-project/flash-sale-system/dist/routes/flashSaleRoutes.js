"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.setFlashSaleRoutes = void 0;
const express_1 = require("express");
const flashSaleController_1 = __importDefault(require("../controllers/flashSaleController"));
const router = (0, express_1.Router)();
const flashSaleController = new flashSaleController_1.default();
function setFlashSaleRoutes(app) {
    app.use('/api/flash-sales', router);
    router.post('/', flashSaleController.createFlashSale.bind(flashSaleController));
    router.get('/:id', flashSaleController.getFlashSale.bind(flashSaleController));
    router.post('/:id/participate', flashSaleController.participateInFlashSale.bind(flashSaleController));
}
exports.setFlashSaleRoutes = setFlashSaleRoutes;
