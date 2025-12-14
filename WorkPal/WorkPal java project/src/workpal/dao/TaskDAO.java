
package workpal.dao;

import workpal.model.Task;
import workpal.model.DBConnection;
import java.sql.*;
import java.util.*;

public class TaskDAO {


    //add a task
    public boolean addTask(Task task) {
        String sql = "INSERT INTO tasks(title, description, project_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setInt(3, task.getProjectId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // get task
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("project_id")
                );
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // update task
    public boolean updateTask(Task task) {
        String sql = "UPDATE tasks SET title=?, description=?, project_id=? WHERE task_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setInt(3, task.getProjectId());
            stmt.setInt(4, task.getTaskId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // dalate task
    public boolean removeTask(int task_id) {
        String sql = "DELETE FROM tasks WHERE task_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, task_id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Task> getTasksByProject(int projectId) {
    List<Task> tasks = new ArrayList<>();
    String sql = "SELECT * FROM tasks WHERE project_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, projectId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Task task = new Task(
                rs.getInt("task_id"),
                rs.getInt("project_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("due_date") 
            );
            tasks.add(task);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tasks;
}
}

