package virtual.catalog.dao;

import virtual.catalog.utils.ConnectionMySQL;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import virtual.catalog.model.Category;

public class CategoryDAO {
    private final String INSERT = "INSERT INTO category (name) VALUE (?);";
    private final String DELETE = "DELETE FROM category WHERE id = ?;";
    private final String UPDATE = "UPDATE category SET name = ? WHERE id = ?;";
    private final String GETONE = "SELECT * FROM category WHERE id = ?;";
    private final String GETALL = "SELECT * FROM category;";
    
    private ConnectionMySQL connection = new ConnectionMySQL();
    
    public void insert (String name) {
        PreparedStatement ps = null;
        
        try {
            ps = connection.getConnection().prepareStatement(INSERT);
            ps.setString(1, name);
            
            if (ps.executeUpdate() == 0) {
                System.err.println("Error");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public boolean delete(int id) {
        boolean flag = true;
        PreparedStatement ps = null;
        
        try {
            ps = connection.getConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            
            if (ps.executeUpdate() == 0) {
                System.err.println("Error");
                flag = false;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return flag;
    }
    
    public void update(int id, String name) {
        PreparedStatement ps = null;
        
        try {
            ps = connection.getConnection().prepareStatement(UPDATE);
            
            ps.setString(1, name);
            ps.setInt(2, id);
            
            if (ps.executeUpdate() == 0) {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public List<Category> getCategories() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> categories = new ArrayList<>();
        
        try {
            ps = connection.getConnection().prepareStatement(GETALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                categories.add(convert(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return categories;
    }
    
    // Method for ProductDAO
    /* public int count(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            ps = connection.getConnection().prepareStatement(COUNT);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return count;
    } */
    
    private static Category convert (ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        
        return new Category(id, name);
    }
}
