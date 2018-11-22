package save_my_money;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is used to save,load and manage the user data.
 * All user have an id to load a directory that contains a .txt file where his data is alocated
 * 
 */
public class Data 
{
    private static File file,mainDirectoryFile;
    private static String mainDirectory ="/SaveMyMoney",home;
    
    //Variables to use in getData method
    private String textData = "",fileNameIncomeOrExpense;
    private Scanner scFile;
    private String userIdTxt;
    public ArrayList<IncomeOrExpense> incomeOrExpense;
    private ArrayList<String> categories = new ArrayList<String>();
    public Categories categoriesData[];
    public double incomesTotal,expensesTotal,percentExpense,percentIncome,maxIncome,maxExpense;
    public int angleExtra;
    private Color color[];
    
    /**
     * 
     * @param id String value that reference a directory where .txt is alocated
     */
    public Data(String id)
    {
        //strFile = new File(home+mainDirectory);
        home=System.getProperty("user.home")+mainDirectory+"/"+id;
        mainDirectoryFile = new File(mainDirectory);
        file = new File(home+"/IncOrExp.txt");
        incomeOrExpense = new ArrayList<IncomeOrExpense>();
        incomesTotal = 0;
        expensesTotal = 0;
        percentExpense = 0;
        percentIncome = 0;
        
        //Create txt files
        if(file.exists())
            getData(id);
        else
        {
            if(!mainDirectoryFile.exists())
                new File(System.getProperty("user.home")+mainDirectory).mkdir();
                
            new File(home).mkdir();
            
            try
            {
                PrintWriter txtIncomeOrExpense = new PrintWriter(new FileWriter(home+"/IncOrExp.txt"));
                txtIncomeOrExpense.println(id+"%");
                txtIncomeOrExpense.close();
            }
            catch(Exception e)
            {
                System.out.println("File not created");
            }
        }
        
        getIncomesAndExpensesStats();
    }
    
    /**
     * Get the array categoriesData
     * @return categoriesData array
     */
    public Categories[] getCategories()
    {
        return categoriesData;
    }
    
    /** It generates different color for the categories, each category have got a different color
    * @param index It indicates the number of colors that the method have to generate.
    * 
    */
    public void getCategoryColor(int index)
    {
        boolean newColor = false;
        float r = 0;
        float g = 0;
        float b = 0;
        
        while(!newColor)
        {
            r = (float)(Math.random());
            g = (float)(Math.random());
            b = (float)(Math.random());
            
            if(index == 0)
                newColor = true;
            
            for(int i = 0; i<index; i++)
            {
                if(r != color[i].getRed() || b != color[i].getBlue() || g != color[i].getGreen())
                    newColor = true;
            }
        }
        
        color[index] = new Color(r,g,b);
    }
    
    /**
     * Take the data of the user. Read the user file from a .txt referenced 
     * with an id and take the data in a String variable
     * Through split method, it puts the data on IncomeOrExpense array type.
     * 
     * @param id String value that reference a directory where .txt is alocated
    */ 
    public void getData(String id)
    {
        String incomeOrExpenseStringSplited[],textDataToSplit[];
        fileNameIncomeOrExpense = System.getProperty("user.home")+"/SaveMyMoney/"+id+"/IncOrExp.txt";
        
        try
        {
            scFile = new Scanner(new File(fileNameIncomeOrExpense));
            userIdTxt = scFile.nextLine();
            boolean isNewLine = true;
            
            while(isNewLine)
            {
                try
                {
                    textData += scFile.nextLine();
                }
                catch(Exception e)
                {
                    isNewLine = false;
                }
            }   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        textData = textData.replace(",", ".");
        textDataToSplit = textData.split("%");
          
        try
        {
            for(int i = 0 ; i<textDataToSplit.length; i++)
            {
                incomeOrExpenseStringSplited = textDataToSplit[i].split("&");
                IncomeOrExpense incomeOrExpenseToArray = new IncomeOrExpense(id,Integer.parseInt(incomeOrExpenseStringSplited[0]),Double.parseDouble(incomeOrExpenseStringSplited[1]),incomeOrExpenseStringSplited[2],incomeOrExpenseStringSplited[3]);
                incomeOrExpense.add(i,incomeOrExpenseToArray);
            }
        }
        catch(Exception e)
        {
            return;
        }
    }
    
    
    public double getExpensesTotal()
    {
        return expensesTotal;
    }
    
    /**
    * This method has the function of,Get the total quantity of incomes,
    *  get the total quantity of exspenses, set a categories array
    *  get the extra angle to draw a circle graph, order the incomes or expenses in categories,
    *  get the highest expense and the highest income.
    */
    private void getIncomesAndExpensesStats()
    {
        
        for(int i=0; i<incomeOrExpense.size(); i++)
        {
            IncomeOrExpense conceptGetted = incomeOrExpense.get(i);
            
            if(conceptGetted.getConcept() == 0)
                incomesTotal += conceptGetted.getQuantity();
            
            else
                expensesTotal += conceptGetted.getQuantity();
            
            
            if(categories.isEmpty())
                categories.add(conceptGetted.category);
            else
            {
                boolean repeatCategory = false;
                //For o check if a category is new or a repeated
                for(int j = 0; j < categories.size(); j++)
                {
                    String categoryGetted = categories.get(j);
                    if(categoryGetted.equals(conceptGetted.category))
                        repeatCategory = true;
                }
                
                if(!repeatCategory)
                    categories.add(conceptGetted.category);
            }
        }
        
        percentIncome = incomesTotal/(incomesTotal + expensesTotal);
        percentExpense = expensesTotal/(incomesTotal + expensesTotal);
        
        int angleArcIncome = ((int)(percentIncome * 360));
        int angleArcExpense = (int)(percentExpense*360);
            
        if(angleArcIncome + angleArcExpense != 360)
            angleExtra = 360 - (angleArcIncome + angleArcExpense);
        
        categoriesData = new Categories [categories.size()];
        color = new Color[categories.size()];
        
        for(int i=0; i<categories.size(); i++)
        {
            categoriesData [i] = new Categories();
            categoriesData [i].category = categories.get(i);
            getCategoryColor(i);
            categoriesData [i].color = color[i];
        }
        
        
        for(int i=0; i<incomeOrExpense.size(); i++)
        {
            for(int j=0; j<categoriesData.length; j++)
            {
                if(categoriesData[j].category.equals(incomeOrExpense.get(i).getCategory()))
                {
                    if(incomeOrExpense.get(i).concept == 0)
                        categoriesData[j].income += incomeOrExpense.get(i).quantity;
                    else
                        categoriesData[j].expense += incomeOrExpense.get(i).quantity;
                }
            }
        }
        
        maxExpense = 0;
        maxIncome = 0;
        
        for(int i=0; i<categoriesData.length; i++)
        {
            if(categoriesData[i].expense >= maxExpense)
                maxExpense = categoriesData[i].expense;
            
            if(categoriesData[i].income >= maxIncome)
                maxIncome = categoriesData[i].income;
        } 
    }
    
    /**
     * Get the variable incomesTotal
     * @return incomesTotal
     */
    public double getIncomesTotal()
    {
        return incomesTotal;
    }
    
    /**
     * Get the variable maxExpense
     * @return maxExpense
     */
    public double getMaxExpense()
    {
        return maxExpense;
    }
    
    /**
     * Get the variable maxIncome
     * @return maxIncome
     */
    public double getMaxIncome()
    {
        return maxIncome;
    }
    
    /**
     * Get the variable percentIncome
     * @return percentIncome
     */
    public double getPercentIncome()
    {
        return percentIncome;
    }
    
    /**
     * Get the variable percentExpense
     * @return percentExpense
     */
    public double getPercentExpense()
    {
        return percentExpense;
    }
    
    /** Method to order the IncomesOrExpenses array by incomes
    *   First go the income with a highest quantity until minor
    */
    public void orderCategoriesByIncome()
    {
        int index = 0;
        Categories categoriesSwaping[] = new Categories[categoriesData.length];
        
        for(int i=0; i<categoriesData.length; i++)
        {
            for(int j=0; j<categoriesData.length; j++)
            {
                if(categoriesData[i].income < categoriesData[j].income && j!= i)
                    index++;
            }
            
            while(categoriesSwaping[index] != null)
                index++;
            
            categoriesSwaping[index] = categoriesData[i];
            index = 0;
        }
        
        categoriesData = categoriesSwaping;
    }
    
    
    /** Method to order the IncomesOrExpenses array by expenses
    * First go the expenses with a highest quantity until minor
    */
    public void orderCategoriesByExpense()
    {
        int index = 0;
        Categories categoriesSwaping[] = new Categories[categoriesData.length];
        
        for(int i=0; i<categoriesData.length; i++)
        {
            for(int j=0; j<categoriesData.length; j++)
            {
                if(categoriesData[i].expense < categoriesData[j].expense && j!= i)
                    index++;
            }
            
            while(categoriesSwaping[index] != null)
                index++;
            
            categoriesSwaping[index] = categoriesData[i];
            index = 0;
        }
        
        categoriesData = categoriesSwaping;
    }
}