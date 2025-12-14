package workpal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import workpal.model.DBConnection;
import workpal.model.Goal;
import workpal.Session.SessionManager;

public class GoalDAO {

    // add a goal
    public boolean addGoal(Goal goal) {

        int userId = SessionManager.getLoggedUserId();
        if (userId <= 0) {
            System.out.println("No logged user!");
            return false;
        }

        String sql = "INSERT INTO goal (title, description, user_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, goal.getTitle());
            stmt.setString(2, goal.getDescription());
            stmt.setInt(3, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // gwt goals
    public List<Goal> getAllGoals() {

        List<Goal> goals = new ArrayList<>();
        int userId = SessionManager.getLoggedUserId();


        String sql = "SELECT goal_id, title, description FROM goal WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setInt(1, userId);
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                goals.add(new Goal(
                        rs.getInt("goal_id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goals;
    }

    // update goal
    public boolean updateGoal(Goal goal) {

        int userId = SessionManager.getLoggedUserId();
        if (userId <= 0) return false;

        String sql = "UPDATE goal SET title = ?, description = ? WHERE goal_id = ? AND user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, goal.getTitle());
            stmt.setString(2, goal.getDescription());
            stmt.setInt(3, goal.getGoalId());
            stmt.setInt(4, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //delete goal
    public boolean removeGoal(int goalId) {

        int userId = SessionManager.getLoggedUserId();
        if (userId <= 0) return false;

        String sql = "DELETE FROM goal WHERE goal_id = ? AND user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, goalId);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Goal getGoalById(int goalId) {
    String sql = "SELECT goal_id, title FROM goal WHERE goal_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, goalId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Goal(
                rs.getString("goal_id"),
                rs.getString("title")
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}


