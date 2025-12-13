/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workpal.ui;

import Session.SessionManager;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Bashaer
 */
public class MainForm extends JFrame{
    private JButton btnProjects, btnTasks, btnGoals, btnGoalSteps, btnLogout;
    public MainForm(){
        setTitle("Workpal - Main Dashboard");
        setSize(400,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel title =new JLabel("WorkPal Dashboard",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,22));
        title.setBounds(0, 20, 400, 40);
        //btnContinue.setBounds(130, 250, 140, 40);
        //btnContinue.setBackground(new Color(0, 51, 102));
        //btnContinue.setForeground(Color.WHITE);
        //btnContinue.setFont(new Font("Arial", Font.BOLD, 16));
        btnProjects=new JButton ("Manage Projects");
        btnProjects.setBounds(100, 90, 200, 40);
        btnProjects.setBackground(new Color(0, 51, 102));
        btnProjects.setForeground(Color.WHITE);
        
        add(btnProjects);
        
        
        btnTasks =new JButton("Manage Tasks");
        btnTasks.setBounds(100, 150, 200, 40);
        btnTasks.setBackground(new Color(0, 51, 102));
        btnTasks.setForeground(Color.WHITE);
        add(btnTasks);
        
        
        btnGoals= new JButton("Manage Goals");
        btnGoals.setBackground(new Color(0, 51, 102));
        btnGoals.setForeground(Color.WHITE);
        btnGoals.setBounds(100, 210, 200, 40);
        add(btnGoals);
        
        
        btnGoalSteps =new JButton("Manage Goal Steps");
        btnGoalSteps.setBounds(100, 270, 200, 40);
        btnGoalSteps.setBackground(new Color(0, 51, 102));
        btnGoalSteps.setForeground(Color.WHITE);
        add(btnGoalSteps);
        
        
        btnLogout =new JButton("Logout");
        btnLogout.setBounds(150, 330, 100, 35);
        btnLogout.setBackground(new Color(3, 60, 150));
        btnLogout.setForeground(Color.WHITE);
        add(btnLogout);
        //Button Actions
        btnProjects.addActionListener(e -> {
            ProjectForm projectForm = new ProjectForm();
            projectForm.setVisible(true);
        });

        btnTasks.addActionListener(e -> {
            TaskForm taskForm = new TaskForm();
            taskForm.setVisible(true);
        });

        btnGoals.addActionListener(e -> {
            GoalForm goalForm = new GoalForm();
            goalForm.setVisible(true);
        });

        btnGoalSteps.addActionListener(e -> {
            GoalStepForm stepForm = new GoalStepForm();
            stepForm.setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            SessionManager.logout();
            dispose();
            RegisterForm login = new RegisterForm(); 
            login.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
    }
}
        