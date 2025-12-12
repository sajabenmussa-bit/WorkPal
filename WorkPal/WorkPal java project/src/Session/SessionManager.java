package Session;


import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bashaer
 */
public class SessionManager {
    private static final String SESSION_FILE="session.txt";
    public static void saveLogin(String username){
        try(PrintWriter writer = new PrintWriter(new FileWriter(SESSION_FILE))){
            writer.println(username);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static String getLoggedUser(){
        File file =new File(SESSION_FILE);
        if(!file.exists())return null;
        try(BufferedReader reader =new BufferedReader(new FileReader(file))){
            return reader.readLine();
        }catch(IOException e){
            return null;
        }
    }
    public static void logout(){
        File file =new File(SESSION_FILE);
        if(file.exists())file.delete();
               
    }
    
}
