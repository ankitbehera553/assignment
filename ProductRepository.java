package com.upskill.upskill.ProductRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.upskill.upskill.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products", new ProductRowMapper());
    }

    public Product findById(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM products WHERE id = ?",
            new ProductRowMapper(), id
        );
    }

    static class ProductRowMapper implements RowMapper<Product> {
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setCost(rs.getDouble("cost"));
            p.setCategory(rs.getString("category"));
            p.setName(rs.getString("name"));
            p.setBrand(rs.getString("brand"));
            p.setRetailPrice(rs.getDouble("retail_price"));
            p.setDepartment(rs.getString("department"));
            p.setSku(rs.getString("sku"));
            p.setDistributionCenterId(rs.getInt("distribution_center_id"));
            return p;
        }
    }
}
