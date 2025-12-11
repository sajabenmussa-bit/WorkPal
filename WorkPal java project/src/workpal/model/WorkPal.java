
package workpal.model;
import workpal.ui.RegisterForm;
import workpal.ui.ProjectForm;

public class WorkPal {
    public static void main(String[] args) {
       
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // إنشاء نموذج تسجيل المستخدم
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });
      javax.swing.SwingUtilities.invokeLater(() -> {
            new RegisterForm().setVisible(true);
        });
     
    
}


