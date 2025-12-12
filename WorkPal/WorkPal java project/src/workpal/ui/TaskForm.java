package workpal.ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import workpal.dao.TaskDAO;
import workpal.model.Task;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
/**
 *
 * @author Bashaer
 */
public class TaskForm extends JFrame{
    private JTable tableTasks;
    private JTextField txtTitle, txtDescription, txtProjectId ;
    private JButton btnAdd, btnUpdate , btnDelete;
    private TaskDAO taskDAO = new TaskDAO (); 
    public TaskForm(){
        setTitle("Task Manager");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        //Labels & Fields
        JLabel lblTitle =new JLabel("Title");
        lblTitle.setBounds(20, 20, 80, 25);
        txtTitle=new JTextField();
        txtTitle.setBounds(100, 20, 150, 25);
        JLabel lblDescription=new JLabel("Description");
        lblDescription.setBounds(20, 60, 80, 25);
        txtDescription=new JTextField();
        txtDescription.setBounds(100, 60, 150, 25);
        JLabel lblProjectId = new JLabel("Project ID");
        lblProjectId.setBounds(20, 100, 80, 25);
        txtProjectId =new JTextField();
        txtProjectId.setBounds(100, 100, 150, 25);
        btnAdd=new JButton("Add");
        btnAdd.setBounds(300, 20, 100, 30);
        btnUpdate =new JButton("Update");
        btnUpdate.setBounds(300, 60, 100, 30);
        btnDelete =new JButton("Delete");
        btnDelete.setBounds(300, 100, 100, 30);
        add(lblTitle);add(txtTitle);
        add(lblDescription);add(txtDescription);
        add(lblProjectId); add(txtProjectId);
        add(btnAdd);add(btnUpdate);add(btnDelete);
        tableTasks =new JTable(new DefaultTableModel(new Object[]{"Task ID","Title", "Description","Project ID"},0));
        JScrollPane scrollPane = new JScrollPane(tableTasks);
        scrollPane.setBounds(20, 150, 550, 200);
        add(scrollPane);
        //Load Data
        loadTasksToTable();
        //Button Actions
        btnAdd.addActionListener(e -> addTask());
        btnUpdate.addActionListener(e -> updateTask());
        btnDelete.addActionListener(e -> deleteTask());
        // Table click
        tableTasks.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableTasks.getSelectedRow();
                if(selectedRow >= 0){
                    txtTitle.setText(tableTasks.getValueAt(selectedRow,1).toString());
                    txtDescription.setText(tableTasks.getValueAt(selectedRow,2).toString());
                    txtProjectId.setText(tableTasks.getValueAt(selectedRow,3).toString());
                }
            }
        });
    }
    
    private void loadTasksToTable() {
        List<Task> tasks = taskDAO.getAllTasks(); 
        DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
        model.setRowCount(0);
        for(Task t : tasks){
            model.addRow(new Object[]{t.getTaskId(), t.getTitle(), t.getDescription(), t.getProjectId()});
        }
    }
    private void addTask() {
        try {
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            int projectId = Integer.parseInt(txtProjectId.getText());

            Task task = new Task(0, title, description, projectId);
            boolean success = taskDAO.addTask(task);
            if(success){
                JOptionPane.showMessageDialog(this, "Task added successfully!");
                loadTasksToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add task.");
            }
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Project ID must be a number!");
        }
    }
    
    private void updateTask() {
        int selectedRow = tableTasks.getSelectedRow();
        if(selectedRow >= 0){
            try {
                int taskId = (int) tableTasks.getValueAt(selectedRow,0);
                String title = txtTitle.getText();
                String description = txtDescription.getText();
                int projectId = Integer.parseInt(txtProjectId.getText());

                Task task = new Task(taskId, title, description, projectId);
                boolean success = taskDAO.updateTask(task);
                if(success){
                    JOptionPane.showMessageDialog(this, "Task updated successfully!");
                    loadTasksToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update task.");
                }
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Project ID must be a number!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to update.");
        }
    }

    private void deleteTask() {
        int selectedRow = tableTasks.getSelectedRow();
        if(selectedRow >= 0){
            int taskId = (int) tableTasks.getValueAt(selectedRow,0);
            boolean success = taskDAO.removeTask(taskId);
            if(success){
                JOptionPane.showMessageDialog(this, "Task deleted successfully!");
                loadTasksToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete task.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    // Main method لتشغيل الواجهة
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new TaskForm().setVisible(true));
    }
}

