package workpal.model;

public class GoalStep {
    private int stepId;
    private int goalId;
    private String description;
    private boolean completed;

    //Constructors
    public GoalStep(int goalId, String description) {
        this.goalId = goalId;
        this.description = description;
        this.completed = false;
    }

   
    public GoalStep(int stepId, int goalId, String description, boolean completed) {
        this.stepId = stepId;
        this.goalId = goalId;
        this.description = description;
        this.completed = completed;
    }

    
    public GoalStep(int stepId, int goalId, String description) {
        this.stepId = stepId;
        this.goalId = goalId;
        this.description = description;
        this.completed = false;
    }

    // Getters & Setters
    public int getStepId() { return stepId; }
    public int getGoalId() { return goalId; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setStepId(int stepId) { this.stepId = stepId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }
    public void setDescription(String description) { this.description = description; }
}
