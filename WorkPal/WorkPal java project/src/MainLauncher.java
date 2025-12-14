

import workpal.Session.SessionManager;
import workpal.ui.*;

public class MainLauncher {
    public static void main(String[] args){
        String loggedUser=SessionManager.getLoggedUser();
        if(loggedUser!=null){
            new MainForm().setVisible(true);
        }else{
            new RegisterForm().setVisible(true);
        }
    }
}
