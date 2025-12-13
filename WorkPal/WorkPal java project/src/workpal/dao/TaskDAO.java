/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workpal.dao;

import workpal.model.Task;
import workpal.model.DBConnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Bashaer
 */
public class TaskDAO {


    // CREATE - إضافة مهمة
    public boolean addTask(Task task) {
        String sql = "INSERT INTO tasks(title, description, projectId) VALUES (?, ?, ?)";
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

    // READ - جلب كل المهام
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("taskId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("projectId")
                );
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // UPDATE - تعديل مهمة
    public boolean updateTask(Task task) {
        String sql = "UPDATE tasks SET title=?, description=?, projectId=? WHERE taskId=?";
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

    // DELETE - حذف مهمة
    public boolean removeTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE taskId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, taskId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Task> getTasksByProject(int projectId) {
    List<Task> tasks = new ArrayList<>();
    String sql = "SELECT * FROM tasks WHERE projectId = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, projectId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Task t = new Task(
                rs.getInt("taskId"),
                rs.getInt("projectId"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("dueDate")
            );
            tasks.add(t);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tasks;
}
}

