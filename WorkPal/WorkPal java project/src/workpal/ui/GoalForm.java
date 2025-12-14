package workpal.ui;

import java.awt.Color;
import workpal.dao.GoalDAO;
import workpal.model.Goal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class GoalForm extends JFrame {

    private JTable tableGoals;
    private JTextField txtTitle, txtDescription;
    private JButton btnAdd, btnUpdate, btnDelete;
    private GoalDAO goalDAO = new GoalDAO();
    private DefaultTableModel model;

    public GoalForm() {
        setTitle("Goal Manager");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        
        JLabel lblTitle = new JLabel("Goal Title");
        lblTitle.setBounds(20, 20, 100, 25);

        txtTitle = new JTextField();
        txtTitle.setBounds(130, 20, 150, 25);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(20, 60, 100, 25);

        txtDescription = new JTextField();
        txtDescription.setBounds(130, 60, 150, 25);

        add(lblTitle);
        add(txtTitle);
        add(lblDescription);
        add(txtDescription);

        //Buttons
        btnAdd = new JButton("Add");
        btnAdd.setBounds(320, 20, 120, 30);
        styleButton(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(320, 60, 120, 30);
        styleButton(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(320, 100, 120, 30);
        styleButton(btnDelete);

        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);

        //table
        model = new DefaultTableModel(new Object[]{"Goal ID", "Title", "Description"}, 0);
        tableGoals = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableGoals);
        scrollPane.setBounds(20, 150, 550, 200);
        add(scrollPane);

        
        loadGoalsToTable();

        //Actions 
        btnAdd.addActionListener(e -> addGoal());
        btnUpdate.addActionListener(e -> updateGoal());
        btnDelete.addActionListener(e -> deleteGoal());

        tableGoals.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableGoals.getSelectedRow();
                if (row >= 0) {
                    txtTitle.setText(model.getValueAt(row, 1).toString());
                    txtDescription.setText(model.getValueAt(row, 2).toString());
                }
            }
        });
    }

        private void styleButton(JButton btn) {
                 btn.setBackground(new Color(0, 51, 102));
                 btn.setForeground(Color.WHITE);
          }

         //  Load Goals
        private void loadGoalsToTable() {
               model.setRowCount(0);
               List<Goal> goals = goalDAO.getAllGoals();

        for (Goal g : goals) {
            model.addRow(new Object[]{
                g.getGoalId(),
                g.getTitle(),
                g.getDescription()
            });
        }
    }

        //Add Goal
        private void addGoal() {
                String title = txtTitle.getText().trim();
                String description = txtDescription.getText().trim();

        if (title.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        Goal goal = new Goal(0, title, description);

        if (goalDAO.addGoal(goal)) {
            JOptionPane.showMessageDialog(this, "Goal added successfully!");
            loadGoalsToTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add goal.");
        }
    }

        //Update Goal
        private void updateGoal() {
            int row = tableGoals.getSelectedRow();
         if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a goal first");
            return;
         }
 
        int goalId = (int) model.getValueAt(row, 0);
        String title = txtTitle.getText();
        String description = txtDescription.getText();

        Goal goal = new Goal(goalId, title, description);

        if (goalDAO.updateGoal(goal)) {
            JOptionPane.showMessageDialog(this, "Goal updated successfully!");
            loadGoalsToTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update goal.");
        }
    }

       //Delete Goal
        private void deleteGoal() {
             int row = tableGoals.getSelectedRow();
           if (row < 0) {
             JOptionPane.showMessageDialog(this, "Select a goal to delete");
             return;
        }

        int goalId = (int) model.getValueAt(row, 0);

        if (goalDAO.removeGoal(goalId)) {
            JOptionPane.showMessageDialog(this, "Goal deleted successfully!");
            loadGoalsToTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete goal.");
        }
    }

        private void clearFields() {
           txtTitle.setText("");
           txtDescription.setText("");
    }
}

    
    
    
    

