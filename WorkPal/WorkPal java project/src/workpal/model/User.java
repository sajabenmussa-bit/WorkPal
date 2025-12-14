
package workpal.model;

import java.io.*;
import java.util.*;


public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    
    //Constructors
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getId() {
        return id;
    }

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
