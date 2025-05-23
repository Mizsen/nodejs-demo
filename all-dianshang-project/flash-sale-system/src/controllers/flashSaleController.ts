import { Request, Response } from 'express';
import * as fs from 'fs';
import * as path from 'path';

interface FlashSale {
    id: number;
    productId: number;
    price: number;
    stock: number;
    startTime: string;
    endTime: string;
    participants: number[]; // userId 列表
}

const DATA_FILE = path.join(__dirname, '../../data/flashSales.json');

// 读取秒杀活动
function readFlashSales(): FlashSale[] {
    try {
        const data = fs.readFileSync(DATA_FILE, 'utf-8');
        return JSON.parse(data);
    } catch {
        return [];
    }
}

// 写入秒杀活动
function writeFlashSales(flashSales: FlashSale[]) {
    fs.mkdirSync(path.dirname(DATA_FILE), { recursive: true });
    fs.writeFileSync(DATA_FILE, JSON.stringify(flashSales, null, 2), 'utf-8');
}

class FlashSaleController {
    // 创建秒杀活动
    createFlashSale(req: Request, res: Response) {
        const { productId, price, stock, startTime, endTime } = req.body;
        if (!productId || price == null || stock == null || !startTime || !endTime) {
            return res.status(400).json({ message: 'Missing fields' });
        }
        const flashSales = readFlashSales();
        const nextId = flashSales.length > 0 ? Math.max(...flashSales.map(f => f.id)) + 1 : 1;
        const flashSale: FlashSale = {
            id: nextId,
            productId,
            price,
            stock,
            startTime,
            endTime,
            participants: [],
        };
        flashSales.push(flashSale);
        writeFlashSales(flashSales);
        res.status(201).json(flashSale);
    }

    // 获取秒杀详情
    getFlashSale(req: Request, res: Response) {
        const id = parseInt(req.params.id, 10);
        const flashSales = readFlashSales();
        const flashSale = flashSales.find(f => f.id === id);
        if (!flashSale) {
            return res.status(404).json({ message: 'Flash sale not found' });
        }
        res.json(flashSale);
    }

    // 参与秒杀
    participateInFlashSale(req: Request, res: Response) {
        const id = parseInt(req.params.id, 10);
        const { userId } = req.body;
        if (!userId) {
            return res.status(400).json({ message: 'Missing userId' });
        }
        const flashSales = readFlashSales();
        const flashSale = flashSales.find(f => f.id === id);
        if (!flashSale) {
            return res.status(404).json({ message: 'Flash sale not found' });
        }
        // 判断活动时间
        const now = new Date();
        if (now < new Date(flashSale.startTime) || now > new Date(flashSale.endTime)) {
            return res.status(400).json({ message: 'Flash sale not active' });
        }
        // 判断库存
        if (flashSale.stock <= 0) {
            return res.status(400).json({ message: 'Out of stock' });
        }
        // 判断是否已参与
        if (flashSale.participants.includes(userId)) {
            return res.status(400).json({ message: 'User already participated' });
        }
        // 扣减库存并记录参与
        flashSale.stock -= 1;
        flashSale.participants.push(userId);
        writeFlashSales(flashSales);
        res.json({ message: 'Participated successfully', flashSale });
    }
}

export default FlashSaleController;