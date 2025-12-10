
package workpal.model;

public class WorkPal {
    public static void main(String[] args) {
       
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });
    
    }
}


