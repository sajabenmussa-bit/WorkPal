
package workpal.dao;

import workpal.model.User;
import workpal.model.DBConnection;
import java.sql.*;
import java.util.*;


public class UserDAO {
    
    // add user
    public boolean addUser(User user) {
        String sql = "INSERT INTO users(username, passwordHash, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


