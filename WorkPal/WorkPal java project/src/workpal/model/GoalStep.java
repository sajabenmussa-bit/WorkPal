
package workpal.model;



public class GoalStep {
    private int stepId;
    private int goalId;
    private String description;
    private boolean completed;

    //Constructor without stepId
    public GoalStep( int goalId, String description) {
        this.goalId = goalId;
        this.description = description;
        this.completed = false;
    }
    //Full Constructor
    public GoalStep(int stepId , int goalId , String description, boolean ccompleted){
        this.stepId=stepId;
        this.goalId = goalId;
        this.description = description;
        this.completed = completed;
    }

    public GoalStep(int aInt, int aInt0, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Getters & Setters
    public int getStepId() { return stepId; }
    public int getGoalId() { return goalId; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }

    public void setDescription(String description) {
        this.description = description;
    }

    // Mark step as complete
    public void markComplete() {
        this.completed = true;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
}

