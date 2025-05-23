import { Router , Express} from 'express';
import FlashSaleController from '../controllers/flashSaleController';

const router = Router();
const flashSaleController = new FlashSaleController();

export function setFlashSaleRoutes(app: Express) {
    app.use('/api/flash-sales', router);
    
    router.post('/', flashSaleController.createFlashSale.bind(flashSaleController));
    router.get('/:id', flashSaleController.getFlashSale.bind(flashSaleController));
    router.post('/:id/participate', flashSaleController.participateInFlashSale.bind(flashSaleController));
}