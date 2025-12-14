package workpal.Session;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import workpal.model.DBConnection;


public class SessionManager {
    private static final String SESSION_FILE="session.txt";
    public static void saveLogin(String user_name){
        try(PrintWriter writer = new PrintWriter(new FileWriter(SESSION_FILE))){
            writer.println(user_name);
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
    public static int getLoggedUserId() {
    String username = getLoggedUser();
    if (username == null) return -1;

    String sql = "SELECT user_id FROM user WHERE user_name = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("user_id");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return -1;
}
    
}
