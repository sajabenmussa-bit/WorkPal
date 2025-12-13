
package workpal.dao;


import java.sql.*;
import java.util.*;
import workpal.model.DBConnection;
import workpal.model.Goal;

public class GoalDAO {

    // add goal
    public boolean addGoal(Goal goal) {
        String sql = "INSERT INTO goal (user_id, title, description, target_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, goal.getUserId());
            stmt.setString(2, goal.getTitle());
            stmt.setString(3, goal.getDescription());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // get the uuser's goals
    public List<Goal> getGoalsByUser(int userId) {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM goal WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Goal goal = new Goal(
                        rs.getInt("goal_id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
                goals.add(goal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }

    // update info
    public boolean updateGoal(Goal goal) {
        String sql = "UPDATE goal SET title = ?, description = ?, target_date = ? WHERE goal_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, goal.getTitle());
            stmt.setString(2, goal.getDescription());
            stmt.setInt(4, goal.getGoalId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // remove the goal
    public boolean removeGoal(int goalId) {
        String sql = "DELETE FROM goal WHERE goal_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, goalId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Get all Goals Method
    public List<Goal> getAllGoals() {
    List<Goal> goals = new ArrayList<>();

    String sql = "SELECT * FROM goals";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
                Goal goal = new Goal(
                        rs.getInt("goal_id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
                goals.add(goal);
            }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return goals;
}
}


