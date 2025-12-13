
package workpal.model;

import java.util.*;

public class Goal {
    private int goalId;
    private int userId;
    private String title;
    private String description;
    private ArrayList<GoalStep> steps;

    public Goal(int goalId, int userId, String title, String description) {
        this.goalId = goalId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.steps = new ArrayList<>();
    }

    public Goal(int i, String title, String description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters & Setters
    public int getGoalId() { return goalId; }
    public int getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ArrayList<GoalStep> getSteps() { return steps; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Add a step to the goal
    public void addStep(GoalStep step) {
        steps.add(step);
    }

    // Remove a step by stepId
    public void removeStep(int stepId) {
        Iterator<GoalStep> iterator = steps.iterator();
        while (iterator.hasNext()) {
            GoalStep step = iterator.next();
            if (step.getStepId() == stepId) {
                iterator.remove();
                break;
            }
        }
    }
}

