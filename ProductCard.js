import React from 'react';
import { Link } from 'react-router-dom';

function ProductCard({ product }) {
  return (
    <div className="card mb-3">
      <div className="card-body">
        <h5 className="card-title">{product.name}</h5>
        <p className="card-text"><strong>Brand:</strong> {product.brand}</p>
        <p className="card-text"><strong>Price:</strong> ₹{product.retailPrice}</p>
        <Link to={`/product/${product.id}`} className="btn btn-primary">View Details</Link>
      </div>
    </div>
  );
}

export default ProductCard;
