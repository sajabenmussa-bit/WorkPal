
package workpal.ui;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import workpal.model.Project;
import workpal.dao.ProjectDAO;

public class ProjectForm extends JFrame {
    private JTextField txtTitle, txtDescription, txtStartDate, txtEndDate;
    private JButton btnContinue;

    public ProjectForm() {
        setTitle("Project Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Header
        JLabel lblHeader = new JLabel("project", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setBounds(0, 10, 400, 30);
        add(lblHeader);

        // Labels and Fields
        JLabel lblTitle = new JLabel("Project name:");
        lblTitle.setBounds(40, 60, 100, 30);
        txtTitle = new JTextField();
        txtTitle.setBounds(150, 60, 200, 30);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(40, 100, 100, 30);
        txtDescription = new JTextField();
        txtDescription.setBounds(150, 100, 200, 30);

        JLabel lblStartDate = new JLabel("Start date:");
        lblStartDate.setBounds(40, 140, 100, 30);
        txtStartDate = new JTextField("yyyy-MM-dd");
        txtStartDate.setBounds(150, 140, 200, 30);

        JLabel lblEndDate = new JLabel("End date:");
        lblEndDate.setBounds(40, 180, 100, 30);
        txtEndDate = new JTextField("yyyy-MM-dd");
        txtEndDate.setBounds(150, 180, 200, 30);

        btnContinue = new JButton("continue");
        btnContinue.setBounds(130, 250, 140, 40);
        btnContinue.setBackground(new Color(0, 51, 102));
        btnContinue.setForeground(Color.WHITE);
        btnContinue.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components
        add(lblTitle); add(txtTitle);
        add(lblDescription); add(txtDescription);
        add(lblStartDate); add(txtStartDate);
        add(lblEndDate); add(txtEndDate);
        add(btnContinue);

        // Action Listener
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProject();
            }
        });
    }

    private void saveProject() {
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        String startDateStr = txtStartDate.getText();
        String endDateStr = txtEndDate.getText();

        if (title.isEmpty() || description.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);

            Project project = new Project(0, 1, title, description, startDate, endDate); 
            ProjectDAO dao = new ProjectDAO();
            boolean success = dao.addProject(project);

            if (success) {
                JOptionPane.showMessageDialog(this, "Project saved successfully!");
                txtTitle.setText("");
                txtDescription.setText("");
                txtStartDate.setText("yyyy-MM-dd");
                txtEndDate.setText("yyyy-MM-dd");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save project.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid date format. Use yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
