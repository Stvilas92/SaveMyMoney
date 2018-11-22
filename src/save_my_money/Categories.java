package save_my_money;

import java.awt.Color;

/**Class to order and classify the categories, each category
* have total quantity of incomes and expenses, whitch will be
* drawed on the MainView.java
*/

public class Categories 
{
    public String category;
    public double income ,expense;
    public Color color;
            
    /**
     * Inicializate Categories class
     */
    public Categories()
    {
        category = "";
        income = 0;
        expense = 0;
        color = Color.WHITE;
    }
    
    /**
     * Get a value to category
     * @return variable category
     */
    public String getCategory()
    {
        return category;
    }
    
    /**
     * Get a value to color
     * @return variable color
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Get a value to expense
     * @return variable expense
     */
    public double getExpense()
    {
        return expense;
    }
    
    /**
     * Get a value to income
     * @return variable income
     */
    public double getIncome()
    {
        return income;
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
     * Set a value to color
     * @param colorSetted 
     */
    public void setColor(Color colorSetted)
    {
        color = colorSetted;
    }
    
    /**
     * Set a value to expense
     * @param expenseSetted 
     */
    public void setExpense(double expenseSetted)
    {
        expense = expenseSetted;
    }
    
    /**
     * Set a value to income
     * @param incomeSetted 
     */
    public void setIncome(double incomeSetted)
    {
        income = incomeSetted;
    }
}