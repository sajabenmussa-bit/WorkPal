package workpal.ui;

import workpal.dao.GoalDAO;
import workpal.dao.GoalStepDAO;
import workpal.dao.TaskDAO; 
import workpal.model.Goal;
import workpal.model.GoalStep;
import workpal.model.Task; 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GoalStepForm extends JFrame {
    private JComboBox<Goal> goalComboBox;
    private JComboBox<Task> taskComboBox; 
    private JTable stepsTable;
    private DefaultTableModel model;
    
    private GoalDAO goalDAO = new GoalDAO();
    private GoalStepDAO goalStepDAO = new GoalStepDAO();
    private TaskDAO taskDAO = new TaskDAO();

    public GoalStepForm() {
        setTitle("Goal Steps Manager");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // select goal and tasks
        goalComboBox = new JComboBox<>();
        taskComboBox = new JComboBox<>(); 

        topPanel.add(new JLabel("Select Goal:"));
        topPanel.add(goalComboBox);
        topPanel.add(new JLabel("Select Task to Add:"));
        topPanel.add(taskComboBox);
        
        add(topPanel, BorderLayout.NORTH);

        
        String[] cols = {"Step ID", "Goal ID", "Description", "Completed"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public Class<?> getColumnClass(int col) {
                return col == 3 ? Boolean.class : Object.class;
            }
        };
        stepsTable = new JTable(model);
        add(new JScrollPane(stepsTable), BorderLayout.CENTER);
        

       
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Step");
        JButton deleteBtn = new JButton("Delete");
        JButton completeBtn = new JButton("Mark Completed");

        styleButton(addBtn, new Color(0, 51, 102));
        styleButton(deleteBtn, new Color(0, 51, 102));
        styleButton(completeBtn, new Color(3, 120, 40));

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(completeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        //load data
        loadGoals();
        loadTasks(); 

        
        //update table
        goalComboBox.addActionListener(e -> {
            Goal selected = (Goal) goalComboBox.getSelectedItem();
            if (selected != null) loadSteps(selected.getGoalId());
        });

         loadSteps(((Goal) goalComboBox.getSelectedItem()).getGoalId());
         
       addBtn.addActionListener(e -> {
    Goal selectedGoal = (Goal) goalComboBox.getSelectedItem();
    Task selectedTask = (Task) taskComboBox.getSelectedItem(); 

    if (selectedGoal != null && selectedTask != null) {
       
        GoalStep step = new GoalStep(selectedGoal.getGoalId(), selectedTask.getTitle()); 
        
        if (goalStepDAO.addGoalStep(step)) {
            loadSteps(selectedGoal.getGoalId()); 
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save to database");
        }
    }
});
 
        // delete task buttun
        deleteBtn.addActionListener(e -> {
            int row = stepsTable.getSelectedRow();
            if (row == -1) return;
            int stepId = (int) model.getValueAt(row, 0);
            if (goalStepDAO.deleteStep(stepId)) {
                loadSteps(((Goal) goalComboBox.getSelectedItem()).getGoalId());
            }
        });

        //add task
        completeBtn.addActionListener(e -> {
            int row = stepsTable.getSelectedRow();
            if (row == -1) return;
            int stepId = (int) model.getValueAt(row, 0);
            if (goalStepDAO.markStepCompleted(stepId)) {
                loadSteps(((Goal) goalComboBox.getSelectedItem()).getGoalId());
            }
        });
    }

    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }

    private void loadGoals() {
        goalComboBox.removeAllItems();
        List<Goal> goals = goalDAO.getAllGoals();
        for (Goal g : goals) goalComboBox.addItem(g);
    }

    private void loadTasks() {
        taskComboBox.removeAllItems();
      
        List<Task> tasks = taskDAO.getAllTasks(); 
        for (Task t : tasks) taskComboBox.addItem(t);
    }

    private void loadSteps(int goalId) {
    DefaultTableModel model = (DefaultTableModel) stepsTable.getModel();
    model.setRowCount(0); 

    List<GoalStep> steps = goalStepDAO.getStepsByGoalId(goalId);
    for (GoalStep s : steps) {
        model.addRow(new Object[]{
            s.getStepId(), 
            s.getGoalId(), 
            s.getDescription(), 
            s.isCompleted()
        });
    }
}
}