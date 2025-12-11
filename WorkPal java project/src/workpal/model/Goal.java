/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workpal.model;



import java.util.ArrayList;
import java.util.Iterator;

public class Goal {
    private int goalId;
    private int userId;
    private String title;
    private String description;
    private String targetDate;
    private ArrayList<GoalStep> steps;

    public Goal(int goalId, int userId, String title, String description, String targetDate) {
        this.goalId = goalId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.steps = new ArrayList<>();
    }

    // Getters & Setters
    public int getGoalId() { return goalId; }
    public int getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTargetDate() { return targetDate; }
    public ArrayList<GoalStep> getSteps() { return steps; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
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

