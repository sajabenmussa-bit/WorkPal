
package workpal.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

import workpal.Session.SessionManager;
import workpal.dao.ProjectDAO;
import workpal.model.Project;

public class ProjectForm extends JFrame {
    private JTextField txtTitle, txtDescription;
    private JSpinner startDatePicker, endDatePicker;
    private JButton btnContinue;
    private Project project;

    public ProjectForm() {
        setTitle("Project Form");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(null);

        JPanel header = new JPanel();
        header.setBackground(new Color(0, 51, 102));
        header.setBounds(0, 0, 400, 50);
        JLabel lblHeader = new JLabel("Project", SwingConstants.CENTER);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        header.setLayout(new BorderLayout());
        header.add(lblHeader, BorderLayout.CENTER);
        add(header);

        JLabel lblTitle = new JLabel("Project name :");
        lblTitle.setBounds(40, 60, 100, 30);
        txtTitle = new JTextField();
        txtTitle.setBounds(150, 60, 200, 30);
        add(lblTitle); 
        add(txtTitle);

        JLabel lblDescription = new JLabel("Description :");
        lblDescription.setBounds(40, 100, 100, 30);
        txtDescription = new JTextField();
        txtDescription.setBounds(150, 100, 200, 30);
        add(lblDescription); 
        add(txtDescription);

        JLabel lblStartDate = new JLabel("Start date :");
        lblStartDate.setBounds(40, 140, 100, 30);
        SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        startDatePicker = new JSpinner(startModel);
        startDatePicker.setEditor(new JSpinner.DateEditor(startDatePicker, "yyyy-MM-dd"));
        startDatePicker.setBounds(150, 140, 200, 30);
        add(lblStartDate); 
        add(startDatePicker);

        JLabel lblEndDate = new JLabel("End date :");
        lblEndDate.setBounds(40, 180, 100, 30);
        SpinnerDateModel endModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        endDatePicker = new JSpinner(endModel);
        endDatePicker.setEditor(new JSpinner.DateEditor(endDatePicker, "yyyy-MM-dd"));
        endDatePicker.setBounds(150, 180, 200, 30);
        add(lblEndDate); 
        add(endDatePicker);

        btnContinue = new JButton("countinue");
        btnContinue.setBounds(130, 250, 140, 40);
        btnContinue.setBackground(new Color(3, 120, 40));
        btnContinue.setForeground(Color.WHITE);
        btnContinue.setFont(new Font("Arial", Font.BOLD, 16));
        add(btnContinue);

        btnContinue.addActionListener(e -> saveProjectAndGoToTaskForm());
    }

    private void saveProjectAndGoToTaskForm() {
        String title = txtTitle.getText().trim();
        String description = txtDescription.getText().trim();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Project name is required!");
            return;
        }

        int userId = SessionManager.getLoggedUserId();
        if (userId == -1) {
            JOptionPane.showMessageDialog(this, "No logged-in user found. Please log in again.");
            return;
        }

        Date startDate = (Date) startDatePicker.getValue();
        Date endDate = (Date) endDatePicker.getValue();

        project = new Project(0, userId, title, description, startDate, endDate);

        ProjectDAO projectDAO = new ProjectDAO();
        if (projectDAO.addProject(project)) {
            JOptionPane.showMessageDialog(this, "Project saved successfully!");
            TaskForm taskForm = new TaskForm(project);
            taskForm.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save project!");
        }
    }
}
