package save_my_money;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Class to order clasify and save the data of each income and expense
 * A income or expense have a concept (o == income, 1 == expense), cuantity
 * category and if user wants, a note to explain the income or expense
 * 
 * @author alejavilas92
 */
public class IncomeOrExpense
{
    private static final int INCOME = 0;
    private static final int EXPENSE = 1;
    public int concept = 0;
    public double quantity = 0;
    public String category = "",note = "",extension;
    private final String id;

    /**
     * Inicializate the class, the public values and the id value.
     * @param userID Use to search a .txt file to write in, the IncomeOrExpense values.
     * @param type Type of concep, it can be a income if value 0, or a expense if value 1. 
     * @param amount Quantity of the concept.
     * @param denomination Categorie of the concept.
     * @param mark Note of the user.
     */
    public IncomeOrExpense(String userID,int type, double amount, String denomination, String mark)
    {
        id = userID;
        extension=(System.getProperty("user.home"))+"/SaveMyMoney"+"/"+id+"/IncOrExp.txt";
        concept = type;
        quantity = amount;
        category = denomination;
        note = mark; 
    }
    
    /**
     * Get the concept value
     * @return variable concept
     */
    public int getConcept()
    {
        return concept;
    }
    
    /**
     * Get the quantity value
     * @return variable quantity
     */
    public double getQuantity()
    {
        return quantity;
    }
    
    /**
     * Get the category value
     * @return variable category
     */
    public String getCategory()
    {
        return category;
    }
    
    /**
     * Get the note value
     * @return variable note
     */
    public String getNote()
    {
        return note;
    }
    
    /**
     * It writes all the public variables of the object into a .txt called IncOrExp.txt, into the user directo
     * 
     */
    public void saveFile()
    {
        try
        {
            PrintWriter incomeOrExpenseFile = new PrintWriter(new FileWriter(extension,true));
            incomeOrExpenseFile.printf("%d&%.2f&%s&%s%s",concept,quantity,category,note,"%");
            incomeOrExpenseFile.close();
        }
        catch(Exception e)
        {
            System.out.println("File write failed");
        }
    }
    
    /**
     * Set a value to concept
     * @param conceptSetted 
     */
    public void setConcept(int conceptSetted)
    {
        concept = conceptSetted;
    }
    
    /**
     * Set a value to quantity
     * @param quantitySetted 
     */
    public void setQuantity(double quantitySetted)
    {
        quantity = quantitySetted;
    }
    
    /**
     * Set a value to category
     * @param categorySetted 
     */
    public void setCategory(String categorySetted)
    {
        category = categorySetted;
    }
    
    /**
     * Set a value to note
     * @param noteSetted 
     */
    public void setNote(String noteSetted)
    {
        note = noteSetted;
    }
}