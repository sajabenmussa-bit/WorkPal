/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workpal.ui;

import workpal.dao.GoalDAO;
import workpal.dao.GoalStepDAO;
import workpal.model.Goal;
import workpal.model.GoalStep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Bashaer
 */
public class GoalStepForm extends JFrame{
    private JComboBox<Goal> goalComboBox;
    private JTextField descriptionField;
    private JTable stepsTable;

    private GoalDAO goalDAO = new GoalDAO();
    private GoalStepDAO goalStepDAO = new GoalStepDAO();

    public GoalStepForm() {
        setTitle("Goal Steps Manager");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    
    //Top panel
    JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        goalComboBox = new JComboBox<>();
        descriptionField = new JTextField();

        topPanel.add(new JLabel("Select Goal:"));
        topPanel.add(goalComboBox);

        topPanel.add(new JLabel("Step Description:"));
        topPanel.add(descriptionField);

        add(topPanel, BorderLayout.NORTH);
        //Table
        stepsTable = new JTable();
        JScrollPane tablePane = new JScrollPane(stepsTable);
        add(tablePane, BorderLayout.CENTER);

        // BUTTONS PANEL --------------------------------------------------
        JPanel buttonPanel = new JPanel();

        JButton addBtn = new JButton("Add Step");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton completeBtn = new JButton("Mark Completed");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(completeBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load data
        loadGoals();
        //Button Action
        addBtn.addActionListener(e -> {
            Goal selected = (Goal) goalComboBox.getSelectedItem();
            String desc = descriptionField.getText();

            if (selected == null || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields");
                return;
            }

            GoalStep step = new GoalStep(0, selected.getGoalId(), desc);
            if (goalStepDAO.addGoalStep(step)) {
                JOptionPane.showMessageDialog(this, "Step added!");
                loadSteps(selected.getGoalId());
            }
        });

        // تحديث خطوة
        updateBtn.addActionListener(e -> {
            int row = stepsTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a step first");
                return;
            }

            int stepId = (int) stepsTable.getValueAt(row, 0);
            String newDesc = descriptionField.getText();
            boolean completed = (boolean) stepsTable.getValueAt(row, 3);

            GoalStep step = new GoalStep(stepId, ((Goal) goalComboBox.getSelectedItem()).getGoalId(), newDesc);
            if (completed) step.markComplete();

            if (goalStepDAO.updateStep(step)) {
                JOptionPane.showMessageDialog(this, "Updated!");
                loadSteps(step.getGoalId());
            }
        });

        // حذف خطوة
        deleteBtn.addActionListener(e -> {
            int row = stepsTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a step");
                return;
            }

            int stepId = (int) stepsTable.getValueAt(row, 0);

            if (goalStepDAO.deleteStep(stepId)) {
                JOptionPane.showMessageDialog(this, "Deleted!");
                Goal g = (Goal) goalComboBox.getSelectedItem();
                loadSteps(g.getGoalId());
            }
        });

        // تعليم كمكتملة
        completeBtn.addActionListener(e -> {
            int row = stepsTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a step");
                return;
            }

            int stepId = (int) stepsTable.getValueAt(row, 0);

            if (goalStepDAO.markStepCompleted(stepId)) {
                JOptionPane.showMessageDialog(this, "Marked as completed!");
                Goal g = (Goal) goalComboBox.getSelectedItem();
                loadSteps(g.getGoalId());
            }
        });

        // تحميل الخطوات عند تغيير الهدف
        goalComboBox.addActionListener(e -> {
            Goal selected = (Goal) goalComboBox.getSelectedItem();
            if (selected != null)
                loadSteps(selected.getGoalId());
        });
    
        setVisible(true);
    }
        //Methods
        private void loadGoals() {
        List<Goal> goals = goalDAO.getAllGoals();
        for (Goal g : goals) {
            goalComboBox.addItem(g);
        }
    }
        private void loadSteps(int goalId) {
        List<GoalStep> steps = goalStepDAO.getStepsByGoalId(goalId);

        String[] cols = {"ID", "Goal ID", "Description", "Completed"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (GoalStep s : steps) {
            model.addRow(new Object[]{
                s.getStepId(),
                s.getGoalId(),
                s.getDescription(),
                s.isCompleted()
            });
        }

        stepsTable.setModel(model);
    }
}
    
        
        
    

