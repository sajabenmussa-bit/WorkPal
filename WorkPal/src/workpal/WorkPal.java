
package workpal;

public class WorkPal {
    public static void main(String[] args) {
        // تشغيل واجهة Swing في الـ Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // استدعاء شاشة التسجيل
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });
    }
}
