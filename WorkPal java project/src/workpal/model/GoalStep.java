
package workpal.model;



public class GoalStep {
    private int stepId;
    private int goalId;
    private String description;
    private boolean completed;

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

    public void setDescription(String description) {
        this.description = description;
    }

    // Mark step as complete
    public void markComplete() {
        this.completed = true;
    }
}

