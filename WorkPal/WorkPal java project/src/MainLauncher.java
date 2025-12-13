

import Session.SessionManager;
import workpal.ui.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bashaer
 */
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
