
package workpal.model;
import workpal.ui.RegisterForm;
import workpal.ui.ProjectForm;

public class WorkPal {
    public static void main(String[] args) {
       
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // add user
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });
               // add project
                javax.swing.SwingUtilities.invokeLater(() -> {
                new RegisterForm().setVisible(true);
        });
     
    
}
}


