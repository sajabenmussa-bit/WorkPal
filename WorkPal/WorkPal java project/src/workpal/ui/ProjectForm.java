
package workpal.ui;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import workpal.model.Project;
import workpal.dao.ProjectDAO;

public class ProjectForm extends JFrame {
    private JTextField txtTitle, txtDescription;
    private JSpinner startDatePicker, endDatePicker;
    private JButton btnContinue;

    public ProjectForm() {
        setTitle("Project Form");
        setSize(400, 400);
        setLocationRelativeTo(null); // هذا هو السطر الذي يضع النافذة في المنتصف
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 51, 102));
        header.setBounds(0, 0, 400, 50);
        JLabel lblHeader = new JLabel("Project", SwingConstants.CENTER);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        header.setLayout(new BorderLayout());
        header.add(lblHeader, BorderLayout.CENTER);
        add(header);;

        // Project name
        JLabel lblTitle = new JLabel("Project name :");
        lblTitle.setBounds(40, 60, 100, 30);
        txtTitle = new JTextField();
        txtTitle.setBounds(150, 60, 200, 30);

        // Description
        JLabel lblDescription = new JLabel("Description :");
        lblDescription.setBounds(40, 100, 100, 30);
        txtDescription = new JTextField();
        txtDescription.setBounds(150, 100, 200, 30);

        // Start date
        JLabel lblStartDate = new JLabel("Start date :");
        lblStartDate.setBounds(40, 140, 100, 30);
        SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        startDatePicker = new JSpinner(startModel);
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDatePicker, "yyyy-MM-dd");
        startDatePicker.setEditor(startEditor);
        startDatePicker.setBounds(150, 140, 200, 30);

        // End date
        JLabel lblEndDate = new JLabel("End date :");
        lblEndDate.setBounds(40, 180, 100, 30);
        SpinnerDateModel endModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        endDatePicker = new JSpinner(endModel);
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDatePicker, "yyyy-MM-dd");
        endDatePicker.setEditor(endEditor);
        endDatePicker.setBounds(150, 180, 200, 30);

        // Continue button
        btnContinue = new JButton("countinue"); // كما هو مكتوب في الصورة
        btnContinue.setBounds(130, 250, 140, 40);
        btnContinue.setBackground(new Color(0, 51, 102));
        btnContinue.setForeground(Color.WHITE);
        btnContinue.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components
        add(lblTitle); add(txtTitle);
        add(lblDescription); add(txtDescription);
        add(lblStartDate); add(startDatePicker);
        add(lblEndDate); add(endDatePicker);
        add(btnContinue);

        // Action
        btnContinue.addActionListener(e -> saveProject());
    }

    private void saveProject() {
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        Date startDate = (Date) startDatePicker.getValue();
        Date endDate = (Date) endDatePicker.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Project project = new Project(0, 1, title, description, startDate, endDate); // userId = 1 مؤقتًا
        ProjectDAO dao = new ProjectDAO();
        boolean success = dao.addProject(project);

        if (success) {
            JOptionPane.showMessageDialog(this, "Project saved successfully!");
            txtTitle.setText("");
            txtDescription.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save project.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
