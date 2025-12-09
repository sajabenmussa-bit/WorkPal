/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workpal;

import java.io.*;
import java.util.*;


public class User implements Serializable {
    private int userId;
    private String username;
    private String passwordHash; 
    private String email;
    
    public User(int userId, String username, String passwordHash, String email) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email; 
    }
    
   public String getUsername() {
        return username;
    }
   
   public String getEmail() {
        return email;
    }
  public void setUsername(String username) {
        this.username = username;
  }
  //this is a email setter
  public void setEmail(String email) {
        this.email = email;
    }

    User(int i, String saja_freelancer, String hash_value, String sajaworkcom, String saja_J, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
