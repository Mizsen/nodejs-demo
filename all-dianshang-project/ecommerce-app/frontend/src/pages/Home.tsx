
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ProductList from '../components/ProductList';

const Home: React.FC = () => {
    const [products, setProducts] = useState([]);

    // useEffect(() => {
    //     axios.get('http://localhost:3000/api/products')
    //         .then(res => setProducts(res.data))
    //         .catch(err => console.error(err));
    // }, []);


     useEffect(() => {
        axios.get('/api/products')
            .then(res => setProducts(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <div>
            <h1>Welcome to Our E-commerce Store</h1>
            <ProductList products={products} />
        </div>
    );
};

export default Home;