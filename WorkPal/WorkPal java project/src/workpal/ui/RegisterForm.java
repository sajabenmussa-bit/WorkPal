
package workpal.ui;
import Session.SessionManager;
import workpal.ui.ProjectForm;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import workpal.model.DBConnection;

public class RegisterForm extends JFrame {
    private JTextField txtUsername, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnConfirm;

    public RegisterForm() {
        setTitle("Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Header Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 51, 102));
        header.setBounds(0, 0, 400, 60);
        JLabel lblHeader = new JLabel("Login form", SwingConstants.CENTER);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        header.setLayout(new BorderLayout());
        header.add(lblHeader, BorderLayout.CENTER);
        add(header);

        // Labels and Fields
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 100, 30);
        txtUsername = new JTextField();
        txtUsername.setBounds(150, 80, 200, 30);
        txtUsername.setBackground(new Color(204, 229, 255));
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 130, 100, 30);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 130, 200, 30);
        txtPassword.setBackground(new Color(204, 229, 255));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 180, 100, 30);
        txtEmail = new JTextField();
        txtEmail.setBounds(150, 180, 200, 30);
        txtEmail.setBackground(new Color(204, 229, 255));
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        // Confirm Button
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(130, 240, 140, 40);
        btnConfirm.setBackground(new Color(0, 51, 102));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 16));

        btnConfirm.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));

        // Add components
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(lblEmail);
        add(txtEmail);
        add(btnConfirm);

        // Action Listener
        btnConfirm.addActionListener(new ActionListener() {
        @Override
         public void actionPerformed(ActionEvent e) {
          registerUser();
        }
        });

          
    }

    private void registerUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String email = txtEmail.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO user (user_name, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                SessionManager.saveLogin(username);
                new MainForm().setVisible(true);
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                new ProjectForm().setVisible(true); 
                this.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving user!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterForm().setVisible(true);
        });
        
    }

    private void ispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

