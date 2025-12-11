
package workpal.model;

import java.util.*;
import javafx.concurrent.Task;


public class Project {
    private int projectId;
    private int userId;
    private String title;
    private String description ;
    private Date startDate;
    private Date endDate;
    private List<Task> tasks =new ArrayList<> ();
    
    
    //Constructor
    public Project(int projectId , int userId , String title, String description , Date startDate , Date endDate){
        this.projectId=projectId;
        this.userId= userId;
        this.title= title;
        this.description= description ;
        this.startDate= startDate ;
        this.endDate= endDate;     
    }
    
    //getters and setters 
    
    public int getProjectId() {
    return projectId;
}

public void setProjectId(int projectId) {
    this.projectId = projectId;
}

public int getUserId() {
    return userId;
}

public void setUserId(int userId) {
    this.userId = userId;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public Date getStartDate() {
    return startDate;
}

public void setStartDate(Date startDate) {
    this.startDate = startDate;
}

public Date getEndDate() {
    return endDate;
}

public void setEndDate(Date endDate) {
    this.endDate = endDate;
}

public List<Task> getTasks() {
    return tasks;
}

public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
}


    //Methods
    
    public void addTask(Task t){
        tasks.add(t);
    }
    
   public void removeTask (Task t){
       tasks.remove(t);
   } 

    

    
   
  
}
