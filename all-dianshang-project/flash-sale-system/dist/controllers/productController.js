"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductController = void 0;
const fs = __importStar(require("fs"));
const path = __importStar(require("path"));
const DATA_FILE = path.join(__dirname, '../../data/products.json');
// 读取产品数据
function readProducts() {
    try {
        const data = fs.readFileSync(DATA_FILE, 'utf-8');
        return JSON.parse(data);
    }
    catch (_a) {
        return [];
    }
}
// 写入产品数据
function writeProducts(products) {
    fs.mkdirSync(path.dirname(DATA_FILE), { recursive: true });
    fs.writeFileSync(DATA_FILE, JSON.stringify(products, null, 2), 'utf-8');
}
class ProductController {
    // 创建产品
    createProduct(req, res) {
        const { name, price, stock } = req.body;
        if (!name || price == null || stock == null) {
            return res.status(400).json({ message: 'Missing fields' });
        }
        const products = readProducts();
        const nextId = products.length > 0 ? Math.max(...products.map(p => p.id)) + 1 : 1;
        const product = { id: nextId, name, price, stock };
        products.push(product);
        writeProducts(products);
        res.status(201).json(product);
    }
    // 获取产品
    getProduct(req, res) {
        const id = parseInt(req.params.id, 10);
        const products = readProducts();
        const product = products.find(p => p.id === id);
        if (!product) {
            return res.status(404).json({ message: 'Product not found' });
        }
        res.json(product);
    }
    // 更新产品
    updateProduct(req, res) {
        const id = parseInt(req.params.id, 10);
        const products = readProducts();
        const product = products.find(p => p.id === id);
        if (!product) {
            return res.status(404).json({ message: 'Product not found' });
        }
        const { name, price, stock } = req.body;
        if (name !== undefined)
            product.name = name;
        if (price !== undefined)
            product.price = price;
        if (stock !== undefined)
            product.stock = stock;
        writeProducts(products);
        res.json(product);
    }
}
exports.ProductController = ProductController;
