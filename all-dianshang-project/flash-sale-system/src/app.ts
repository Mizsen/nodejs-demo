import express from 'express';
import { setFlashSaleRoutes } from './routes/flashSaleRoutes';
import setProductRoutes from './routes/productRoutes';

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());

setFlashSaleRoutes(app);
setProductRoutes(app);

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});