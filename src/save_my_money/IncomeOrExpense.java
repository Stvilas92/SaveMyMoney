
package save_my_money;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class IncomeOrExpense
{
    private static final int INCOME = 0;
    private static final int EXPENSE = 1;
    public int concept = 0;
    public double quantity = 0;
    public String category = "",note = "",extension;
    private final String id;

    public IncomeOrExpense(String userID,int type, double amount, String denomination, String mark)
    {
        id = userID;
        extension=(System.getProperty("user.home"))+"/SaveMyMoney"+"/"+id+"/IncOrExp.txt";
        concept = type;
        quantity = amount;
        category = denomination;
        note = mark; 
    }
    
    public int getConcept()
    {
        return concept;
    }
    
    public double getQuantity()
    {
        return quantity;
    }
    
    public String getCategory(String categorySetted)
    {
        return category;
    }
    
    public String getNote()
    {
        return note;
    }
    
    public void saveFile()
    {
        try
        {
            PrintWriter incomeOrExpenseFile = new PrintWriter(new FileWriter(extension,true));
            incomeOrExpenseFile.printf("%d&%.2f&%s&%s%s",concept,quantity,category,note,"%");
            incomeOrExpenseFile.close();
            System.out.println("File write success");
        }
        catch(Exception e)
        {
            System.out.println("File write failed");
        }
    }
    
    public void setConcept(int conceptSetted)
    {
        concept = conceptSetted;
    }
    
    public void setQuantity(double quantitySetted)
    {
        quantity = quantitySetted;
    }
    
    public void setCategory(String categorySetted)
    {
        category = categorySetted;
    }
    
    public void setNote(String noteSetted)
    {
        note = noteSetted;
    }
}