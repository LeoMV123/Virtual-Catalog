package virtual.catalog.dao;

import virtual.catalog.model.Product;
import virtual.catalog.utils.ConnectionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final String GETALL = "SELECT * FROM product";
    private final String GETONE = "SELECT * FROM product WHERE id = ?;";
    private final String INSERT = "INSERT INTO (name, price, id_category, description, image) VALUES (?, ?, ?, ?, ?);";
    private final String COUNT_FOR_CATEGORY = "SELECT COUNT(*) product WHERE id_category = ?;";
    
    private ConnectionMySQL connection = new ConnectionMySQL();
    
    public Product get(long id) {
        PreparedStatement ps = null;
        Product product = null;
        ResultSet rs = null;
        
        try {
            ps = connection.getConnection().prepareStatement(GETONE);
            ps.setLong(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                long id_product = rs.getLong("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int id_category = rs.getInt("id_category");
                String description = rs.getString("description");
                String image = rs.getString("image");
                
                product = new Product(id_product, name, price, id_category, description, image);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return product;
    }    
    
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = connection.getConnection().prepareStatement(GETALL);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int id_category = rs.getInt("id_category");
                String description = rs.getString("description");
                String image = rs.getString("image");
                
                products.add(new Product(id, name, price, id_category, description, image));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return products;
    }
    
    public void insert(Product product) {
        PreparedStatement ps = null;
        
        try {
            ps = connection.getConnection().prepareStatement(INSERT);
            
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getId_category());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImage());
                    
            if (ps.executeUpdate() == 0) {
                System.err.println("Error");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
