
package workpal.model;

import java.util.*;


public class Expense {
    private int expenseId;
    private int userId;
    private double amount;
    private String category;
    private Date date;
    private String description;
    
    
    //Constructor
    public Expense(int expenseId, int userId , double amount , String category, Date date, String description){
        this.expenseId=expenseId;
        this.userId=userId;
        this.category=category;
        this.amount=amount;
        this.date=date;
        this.description=description;     
    }
    //Methods
    public void updateExpense(double amount ,String category, String description ){
        this.amount=amount;
        this.category=category ;
        this.description=description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
    
}
