import { Request, Response } from 'express';
import * as fs from 'fs';
import * as path from 'path';

interface Product {
    id: number;
    name: string;
    price: number;
    stock: number;
}

const DATA_FILE = path.join(__dirname, '../../data/products.json');

// 读取产品数据
function readProducts(): Product[] {
    try {
        const data = fs.readFileSync(DATA_FILE, 'utf-8');
        return JSON.parse(data);
    } catch {
        return [];
    }
}

// 写入产品数据
function writeProducts(products: Product[]) {
    fs.mkdirSync(path.dirname(DATA_FILE), { recursive: true });
    fs.writeFileSync(DATA_FILE, JSON.stringify(products, null, 2), 'utf-8');
}

export class ProductController {
    // 创建产品
    createProduct(req: Request, res: Response) {
        const { name, price, stock } = req.body;
        if (!name || price == null || stock == null) {
            return res.status(400).json({ message: 'Missing fields' });
        }
        const products = readProducts();
        const nextId = products.length > 0 ? Math.max(...products.map(p => p.id)) + 1 : 1;
        const product: Product = { id: nextId, name, price, stock };
        products.push(product);
        writeProducts(products);
        res.status(201).json(product);
    }

    // 获取产品
    getProduct(req: Request, res: Response) {
        const id = parseInt(req.params.id, 10);
        const products = readProducts();
        const product = products.find(p => p.id === id);
        if (!product) {
            return res.status(404).json({ message: 'Product not found' });
        }
        res.json(product);
    }

    // 更新产品
    updateProduct(req: Request, res: Response) {
        const id = parseInt(req.params.id, 10);
        const products = readProducts();
        const product = products.find(p => p.id === id);
        if (!product) {
            return res.status(404).json({ message: 'Product not found' });
        }
        const { name, price, stock } = req.body;
        if (name !== undefined) product.name = name;
        if (price !== undefined) product.price = price;
        if (stock !== undefined) product.stock = stock;
        writeProducts(products);
        res.json(product);
    }
}