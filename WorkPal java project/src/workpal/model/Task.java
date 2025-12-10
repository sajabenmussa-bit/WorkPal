
package workpal.model;

import java.util.*;


public class Task {
    private int taskId;
    private int projectId;
    private String title;
    private String description;
    private Date dueDate;
    private String status;
    
    
    //Constructor
    public Task(int taskId , int projectId , String title, String description , Date dueDate){
        this.taskId=taskId;
        this.projectId= projectId;
        this.title=title;
        this.description=description;
        this.dueDate=dueDate;
        this.status="Pending";
    }
    //Methods
    public void markComplete(){
        this.status="Completed";
    }
    public void updateStatus(String newStatus){
        this.status=newStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
    
    
    
}
