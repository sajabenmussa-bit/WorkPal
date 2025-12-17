
package workpal.ui;

import workpal.dao.TaskDAO;
import workpal.dao.ProjectDAO;
import workpal.model.Task;
import workpal.model.Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TaskForm extends JFrame {

    private JComboBox<Project> projectComboBox;
    private JTable tableTasks;
    private JTextField txtTitle, txtDescription;
    private JButton btnAdd, btnUpdate, btnDelete;

    private TaskDAO taskDAO = new TaskDAO();
    private ProjectDAO projectDAO = new ProjectDAO();

    private Project project; 
    
    // Background thread for periodic autorefresh of tasks
    private Thread autoRefreshThread;

    // Constructor 
    public TaskForm(Project project) {
        this.project = project;
        setTitle("Tasks");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        initUI();
        loadProjectsToComboBox();

        if (project != null) {
            projectComboBox.setSelectedItem(project);
        }

        loadTasksToTable();

        // Start background autorefresh thread 
        startAutoRefreshThread();

        // Ensure the background thread stops when the window is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                stopAutoRefreshThread();
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                stopAutoRefreshThread();
            }
        });
    }
    
       public TaskForm() {
           setLocationRelativeTo(null);
    }
   

       // user interface
        private void initUI() {

        JLabel lblTitle = new JLabel("Task Title");
        lblTitle.setBounds(20, 20, 100, 25);
        txtTitle = new JTextField();
        txtTitle.setBounds(130, 20, 150, 25);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(20, 60, 100, 25);
        txtDescription = new JTextField();
        txtDescription.setBounds(130, 60, 150, 25);

        JLabel lblProject = new JLabel("Project");
        lblProject.setBounds(20, 100, 100, 25);
        projectComboBox = new JComboBox<>();
        projectComboBox.setBounds(130, 100, 150, 25);
        projectComboBox.setBackground(new Color(3, 120, 40));
        projectComboBox.setForeground(Color.WHITE);
        add(projectComboBox);

        
         btnAdd =new JButton("Add");
        btnAdd.setBounds(320, 20, 120, 30);
        btnAdd.setBackground(new Color(0, 51, 102));
        btnAdd.setForeground(Color.WHITE);
        add(btnAdd);
        

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(320, 60, 120, 30);
        btnUpdate.setBackground(new Color(0, 51, 102));
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);

        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(320, 100, 120, 30);
        btnDelete.setBackground(new Color(0, 51, 102));
        btnDelete.setForeground(Color.WHITE);
        add(btnDelete);

        
        add(lblTitle); add(txtTitle);
        add(lblDescription); add(txtDescription);
        add(lblProject); add(projectComboBox);
        add(btnAdd); add(btnUpdate); add(btnDelete);

        tableTasks = new JTable(new DefaultTableModel(new Object[]{
            "ID", "Title", "Description", "Project"}, 0));

        JScrollPane scrollPane = new JScrollPane(tableTasks);
        scrollPane.setBounds(20, 150, 550, 200);
        add(scrollPane);

        // Actions
        btnAdd.addActionListener(e -> addTask());
        btnUpdate.addActionListener(e -> updateTask());
        btnDelete.addActionListener(e -> deleteTask());

        tableTasks.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableTasks.getSelectedRow();
                if (row >= 0) {
                    txtTitle.setText(tableTasks.getValueAt(row, 1).toString());
                    txtDescription.setText(tableTasks.getValueAt(row, 2).toString());
                    projectComboBox.setSelectedItem(tableTasks.getValueAt(row, 3));
                }
            }
        });
    }

    // load projects
    private void loadProjectsToComboBox() {
        projectComboBox.removeAllItems();
        projectComboBox.setForeground(Color.WHITE);
        
        List<Project> projects = projectDAO.getAllProjects();
        for (Project p : projects) {
            projectComboBox.addItem(p);
        }
    }

    //load tasks
    private void loadTasksToTable() {

       SwingWorker<Void, Task> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() throws Exception {
            List<Task> tasks = taskDAO.getAllTasks();
            for (Task t : tasks) {
                publish(t);
            }
            return null;
        }

        @Override
        protected void process(List<Task> chunks) {
            DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
            model.setRowCount(0); 
            for (Task t : chunks) {
                Project p = projectDAO.getProjectById(t.getProjectId());
                model.addRow(new Object[]{
                    t.getTaskId(),
                    t.getTitle(),
                    t.getDescription(),
                    p
                });
            }
        }
    };
    worker.execute();
}

    // Multithreading
    private void startAutoRefreshThread() {
        autoRefreshThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                   
                    SwingUtilities.invokeLater(this::loadTasksToTable);
                    Thread.sleep(10_000); 
                }
            } catch (InterruptedException ex) {
             
                Thread.currentThread().interrupt();
            }
        });
        autoRefreshThread.setDaemon(true);
        autoRefreshThread.start();
    }

    private void stopAutoRefreshThread() {
        if (autoRefreshThread != null && autoRefreshThread.isAlive()) {
            autoRefreshThread.interrupt();
        }
    }

    //add task
    private void addTask() {

        Project selectedProject = (Project) projectComboBox.getSelectedItem();
        if (selectedProject == null) {
            JOptionPane.showMessageDialog(this, "Please select a project!");
            return;
        }

        Task task = new Task(
                0,
                txtTitle.getText(),
                txtDescription.getText(),
                selectedProject.getProjectId()
        );

        if (taskDAO.addTask(task)) {
            JOptionPane.showMessageDialog(this, "Task added successfully!");
            txtTitle.setText("");
            txtDescription.setText("");
            loadTasksToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add task!");
        }
        
    }

    // update task
    private void updateTask() {

        int row = tableTasks.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a task first!");
            return;
        }

        Project selectedProject = (Project) projectComboBox.getSelectedItem();
        if (selectedProject == null) {
            JOptionPane.showMessageDialog(this, "Please select a project!");
            return;
        }

        Task task = new Task(
                (int) tableTasks.getValueAt(row, 0),
                txtTitle.getText(),
                txtDescription.getText(),
                selectedProject.getProjectId()
        );

        if (taskDAO.updateTask(task)) {
            JOptionPane.showMessageDialog(this, "Task updated successfully!");
            loadTasksToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update task!");
        }
    }

    // delete task
    private void deleteTask() {

        int row = tableTasks.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a task first!");
            return;
        }

        int taskId = (int) tableTasks.getValueAt(row, 0);

        if (taskDAO.removeTask(taskId)) {
            JOptionPane.showMessageDialog(this, "Task deleted successfully!");
            loadTasksToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete task!");
        }
    }
}
