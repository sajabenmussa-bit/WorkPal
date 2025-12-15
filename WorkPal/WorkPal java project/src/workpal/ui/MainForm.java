
package workpal.ui;

import workpal.Session.SessionManager;
import static com.sun.javafx.scene.CameraHelper.project;
import javax.swing.*;
import java.awt.*;
import workpal.dao.ProjectDAO;
import workpal.model.Project;
import workpal.ui.*;


public class MainForm extends JFrame{
    private JButton btnProjects, btnTasks, btnGoals, btnGoalSteps, btnLogout;
    public MainForm(){
        setTitle("WorkPal");
        setSize(400,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel header = new JPanel();
        header.setBackground(new Color(3, 120, 40));
        header.setBounds(0, 0, 400, 50);
        JLabel lblHeader = new JLabel("WorkPal", SwingConstants.CENTER);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        header.setLayout(new BorderLayout());
        header.add(lblHeader, BorderLayout.CENTER);
        add(header);
        
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
        btnLogout.setBackground(new Color(3, 120, 40));
        btnLogout.setForeground(Color.WHITE);
        add(btnLogout);
        
        btnTasks.addActionListener(e -> {
        ProjectDAO dao = new ProjectDAO();
        Project defaultProject = dao.getProjectById(1); 
        TaskForm taskForm = new TaskForm(defaultProject);
        taskForm.setVisible(true);
        });
        
        
        // Actions Buttons
        btnProjects.addActionListener(e -> {
            ProjectForm projectForm = new ProjectForm();
            projectForm.setVisible(true);
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
        