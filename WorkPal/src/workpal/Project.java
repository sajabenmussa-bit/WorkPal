/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workpal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author Bashaer
 */
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
    //Methods
    
    public void addTask(Task t){
        tasks.add(t);
    }
    
   public void removeTask (Task t){
       tasks.remove(t);
   } 

    public String getTitle() {
        return title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    
   
  
}
