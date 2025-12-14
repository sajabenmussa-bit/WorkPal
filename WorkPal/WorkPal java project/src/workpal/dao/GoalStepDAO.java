package workpal.dao;

import workpal.model.GoalStep;
import workpal.model.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalStepDAO {

    // Add new step
    public boolean addGoalStep(GoalStep step) {
        
        String sql = "INSERT INTO goal_steps (goal_id, description, completed) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, step.getGoalId());
            stmt.setString(2, step.getDescription());
            stmt.setBoolean(3, step.isCompleted());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println(" SQL Error while inserting GoalStep");
            e.printStackTrace();
        }

        return false;
    }
    

    // Get steps by goalId
    public List<GoalStep> getStepsByGoalId(int goalId) {
        List<GoalStep> steps = new ArrayList<>();

        String sql = "SELECT * FROM goal_steps WHERE goal_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, goalId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                GoalStep step = new GoalStep(
                  rs.getInt("step_id"),
                  rs.getInt("goal_id"),
                  rs.getString("description") 
               );   
                step.setCompleted(rs.getBoolean("completed"));
                steps.add(step);
         }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return steps;
    }

    // Update goal step
    public boolean updateStep(GoalStep step) {
        String sql = "UPDATE goal_steps SET description = ?, completed = ? WHERE step_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, step.getDescription());
            stmt.setBoolean(2, step.isCompleted());
            stmt.setInt(3, step.getStepId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Mark step as completed
    public boolean markStepCompleted(int stepId) {
        String sql = "UPDATE goal_steps SET completed = TRUE WHERE step_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stepId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Mark step as pending
    public boolean markStepPending(int stepId) {
        String sql = "UPDATE goal_steps SET completed = FALSE WHERE step_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stepId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete step
    public boolean deleteStep(int stepId) {
        String sql = "DELETE FROM goal_steps WHERE step_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stepId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
}


