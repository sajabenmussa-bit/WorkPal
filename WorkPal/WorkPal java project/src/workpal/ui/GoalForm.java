
package workpal.ui;

import java.awt.Color;
import workpal.dao.GoalDAO;
import workpal.model.Goal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;


public class GoalForm extends JFrame{
    private JTable tableGoals;
    private JTextField txtTitle, txtDescription ;
    private JButton btnAdd , btnUpdate, btnDelete;
    private GoalDAO goalDAO = new GoalDAO();
    public GoalForm (){
        setTitle("Goal Manager");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        //Inout Fields
        JLabel lblTitle = new JLabel("Goal Title");
        lblTitle.setBounds(20, 20, 100, 25);
        
        txtTitle=new JTextField();
        txtTitle.setBounds(130, 20, 150, 25);
        
        JLabel lblDescription =new JLabel("Description");
        lblDescription.setBounds(20, 60, 100, 25);
        
        txtDescription =new JTextField();
        txtDescription.setBounds(130, 60, 150, 25);
        
       
        
        
        
        add(lblTitle); add(txtTitle);
        add(lblDescription); add(txtDescription);
        
        //Buttons
        btnAdd=new JButton("Add");
        btnAdd.setBounds(320, 20, 120, 30);
        btnAdd.setBackground(new Color(0, 51, 102));
        btnAdd.setForeground(Color.WHITE);
        add(btnAdd);
        
        btnUpdate= new JButton("Update");
        btnUpdate.setBounds(320, 60, 120, 30);
        btnUpdate.setBackground(new Color(0, 51, 102));
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);
        
        btnDelete =new JButton ("Delete");
        btnDelete.setBounds(320, 100, 120, 30);
        btnDelete.setBackground(new Color(0, 51, 102));
        btnDelete.setForeground(Color.WHITE);
        add(btnDelete);
        
        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        
        
        //Table
        tableGoals = new JTable(new DefaultTableModel(new Object[]{"Goal ID", "Title", "Description"}, 0));
        JScrollPane scrollPane = new JScrollPane(tableGoals);
        scrollPane.setBounds(20, 150, 550, 200);
        add(scrollPane);

        loadGoalsToTable();
        //Button Action
        btnAdd.addActionListener(e -> addGoal());
        btnUpdate.addActionListener(e -> updateGoal());
        btnDelete.addActionListener(e -> deleteGoal());

        tableGoals.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableGoals.getSelectedRow();
                if (row >= 0) {
                    txtTitle.setText(tableGoals.getValueAt(row, 1).toString());
                    txtDescription.setText(tableGoals.getValueAt(row, 2).toString());
                   
                }
            }
        });
    }
    //Load Data
    private void loadGoalsToTable() {
        List<Goal> goals = goalDAO.getAllGoals();
        DefaultTableModel model = (DefaultTableModel) tableGoals.getModel();
        model.setRowCount(0);

        for (Goal g : goals) {
            model.addRow(new Object[]{
                    g.getGoalId(),
                    g.getTitle(),
                    g.getDescription(),
                    
            });
        }
    }
        //Add Goal
        
     private void addGoal() {
        try {
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            

            Goal goal = new Goal(0, title, description);
            boolean success = goalDAO.addGoal(goal);

            if (success) {
                JOptionPane.showMessageDialog(this, "Goal added successfully!");
                loadGoalsToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add goal.");
            }
            loadGoalsToTable();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "User ID must be a number!");
        }
    }
     //Update Goal
     private void updateGoal() {
        int row = tableGoals.getSelectedRow();

        if (row >= 0) {
            try {
                int goalId = (int) tableGoals.getValueAt(row, 0);
                String title = txtTitle.getText();
                String description = txtDescription.getText();
                

                Goal goal = new Goal(goalId, title, description);
                boolean success = goalDAO.updateGoal(goal);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Goal updated successfully!");
                    loadGoalsToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update goal.");
                }

            
            

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Please select a goal to update.");
        }
        }
     }
     //Delete Goal
     private void deleteGoal() {
        int row = tableGoals.getSelectedRow();

        if (row >= 0) {
            int goalId = (int) tableGoals.getValueAt(row, 0);

            boolean success = goalDAO.removeGoal(goalId);

            if (success) {
                JOptionPane.showMessageDialog(this, "Goal deleted successfully!");
                loadGoalsToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete goal.");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select a goal to delete.");
        }
    }
  
     
    }  
     
        
    
   
    
    
    
    
    

