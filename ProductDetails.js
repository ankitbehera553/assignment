import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, Link } from 'react-router-dom';

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/products/${id}`)
      .then(res => setProduct(res.data))
      .catch(err => console.error(err));
  }, [id]);

  if (!product) return <p>Loading...</p>;

  return (
    <div>
      <h2>{product.name}</h2>
      <p><strong>Category:</strong> {product.category}</p>
      <p><strong>Brand:</strong> {product.brand}</p>
      <p><strong>Price:</strong> ₹{product.retailPrice}</p>
      <p><strong>Department:</strong> {product.department}</p>
      <p><strong>SKU:</strong> {product.sku}</p>
      <p><strong>Distribution Center:</strong> {product.distributionCenterId}</p>
      <Link to="/" className="btn btn-secondary">← Back to Products</Link>
    </div>
  );
}

export default ProductDetail;
