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
import workpal.model.Project;
/**
 *
 * @author Bashaer
 */
public class TaskForm extends JFrame{
    private Project project;
    private JTable tableTasks;
    private JTextField txtTitle, txtDescription, txtProjectId ;
    private JButton btnAdd, btnUpdate , btnDelete;
    private TaskDAO taskDAO = new TaskDAO (); 
    public TaskForm(Project project){
        this.project=project;   //project passed from projectForm
        loadTasksToTable();
        setTitle("Tasks for Project :"+project.getTitle());
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        //Labels & Fields
        JLabel lblTitle =new JLabel("Task Title");
        lblTitle.setBounds(20, 20, 100, 25);
        txtTitle=new JTextField();
        txtTitle.setBounds(130, 20, 150, 25);
        JLabel lblDescription=new JLabel("Description");
        lblDescription.setBounds(20, 60, 100, 25);
        txtDescription=new JTextField();
        txtDescription.setBounds(130, 60, 150, 25);
        
        btnAdd=new JButton("Add");
        btnAdd.setBounds(320, 20, 120, 30);
        btnUpdate =new JButton("Update");
        btnUpdate.setBounds(320, 60, 120, 25);
        btnDelete =new JButton("Delete");
        btnDelete.setBounds(320, 100, 120, 25);
        add(lblTitle);add(txtTitle);
        add(lblDescription);add(txtDescription);
        add(btnAdd);add(btnUpdate);add(btnDelete);
        //Table
        tableTasks =new JTable();
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
                }
            }
        });
    }
    
    private void loadTasksToTable() {
        List<Task> tasks = taskDAO.getTasksByProject(project.getProjectId()); 
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Task ID","Title","Description"},0);
        
        for(Task t : tasks){
            model.addRow(new Object[]{t.getTaskId(), t.getTitle(), t.getDescription()});
        }
        tableTasks.setModel(model);
    }
    private void addTask() {
        try {
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            if(title.isEmpty()|| description.isEmpty()){
                JOptionPane.showMessageDialog(this, "Fill all Fields!");
                return;
            } 
            Task task = new Task(0, title, description,project.getProjectId());
            boolean success=taskDAO.addTask(task);
            if(success){
                 JOptionPane.showMessageDialog(this, "Task added successfully!");
                 txtTitle.setText("");
                 txtDescription.setText("");
                 loadTasksToTable();
            }else{
                JOptionPane.showMessageDialog(this, "Failed to add Task !");
            }
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Project ID must be a number!");
        }
    }
    
    private void updateTask() {
        int selectedRow = tableTasks.getSelectedRow();
        if(selectedRow >= 0){
            
                int taskId = (int) tableTasks.getValueAt(selectedRow,0);
                String title = txtTitle.getText();
                String description = txtDescription.getText();

                Task task = new Task(taskId, title, description);
                boolean success = taskDAO.updateTask(task);
                if(success){
                    JOptionPane.showMessageDialog(this, "Task updated successfully!");
                    loadTasksToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update task.");
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
}

