
package workpal.model;

import java.util.Date;

public class Task {

    private int taskId;
    private int projectId;
    private String title;
    private String description;
    private Date dueDate;
    private String status;

    // Constructor 
    public Task(int taskId, int projectId, String title, String description) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = "Pending";
    }

   
    public Task(int taskId, String title, String description, int projectId) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = "Pending";
    }

  
    public Task(int taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = "Pending";
    }

   
    public Task(int taskId, int projectId, String title, String description, Date dueDate) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = "Pending";
    }

    // Methods
    public void markComplete() {
        this.status = "Completed";
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Getters
    public int getTaskId() {
        return taskId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    
    @Override
    public String toString() {
        return this.title;
    }
}