package workpal.dao;

import workpal.model.Project;
import workpal.model.DBConnection;
import java.sql.*;
import java.util.*;

public class ProjectDAO {
    

   //add a new project
   public boolean addProject(Project project) {
        String sql = "INSERT INTO project (user_id, title, description, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, project.getUserId());
            stmt.setString(2, project.getTitle());
            stmt.setString(3, project.getDescription());
            stmt.setDate(4, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(project.getEndDate().getTime()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        project.setProjectId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
         e.printStackTrace();
            return false;
        }
    }

    // get all the user's projects
    public List<Project> getProjectsByUser(int userId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Project project = new Project(
                        rs.getInt("project_id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                projects.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    // update project info
    public boolean updateProject(Project project) {
        String sql = "UPDATE project SET title = ?, description = ?, start_date = ?, end_date = ? WHERE project_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            stmt.setInt(5, project.getProjectId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // delete the project
    public boolean removeProject(int projectId) {
        String sql = "DELETE FROM project WHERE project_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, projectId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //All projects
    public List<Project> getAllProjects() {
     List<Project> projects = new ArrayList<>();
     String sql = "SELECT * FROM project";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
             Project project = new Project(
                rs.getInt("project_id"),
                rs.getInt("user_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("start_date"),
                rs.getDate("end_date")
            );
            projects.add(project);
        }

     } catch (SQLException e) {
        e.printStackTrace();
        }

     return projects;
}
    public Project getProjectById(int projectId) {
    String sql = "SELECT * FROM project WHERE project_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, projectId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Project(
                rs.getInt("project_id"),
                rs.getInt("user_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("start_date"),
                rs.getDate("end_date")
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; 
}
 

}



