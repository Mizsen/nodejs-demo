export class ProductController {
    private products: Array<{ id: number; name: string; description: string; price: number; stock: number }> = [
        { id: 1, name: "Product 1", description: "Description for Product 1", price: 100, stock: 10 },
        { id: 2, name: "Product 2", description: "Description for Product 2", price: 200, stock: 5 },
        { id: 3, name: "Product 3", description: "Description for Product 3", price: 300, stock: 0 },
    ];

    public getAllProducts(req: any, res: any): void {
        res.json(this.products);
    }

    public getProductById(req: any, res: any): void {
        const productId = parseInt(req.params.id);
        const product = this.products.find(p => p.id === productId);
        if (product) {
            res.json(product);
        } else {
            res.status(404).send("Product not found");
        }
    }
}