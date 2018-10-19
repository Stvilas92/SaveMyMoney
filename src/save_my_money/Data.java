package save_my_money;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static save_my_money.Save_My_Money.data;

public class Data 
{
    private static File file;
    private static String mainDirectory ="/SaveMyMoney",home;
    //Variables to use in getData method
    private String textData = "",fileNameIncomeOrExpense,fileNameCategory;
    private Scanner scFile;
    private String userIdTxt;
    public ArrayList<IncomeOrExpense> incomeOrExpense;
    
    public Data(String id)
    {
        //strFile = new File(home+mainDirectory);
        home=System.getProperty("user.home")+mainDirectory+"/"+id;
        file = new File(home+"/IncOrExp.txt");
        
        //Create txt files
        if(file.exists())
            getData(id);
        else
        {
        
            new File(home).mkdir();
            
            try
            {
                PrintWriter txtIncomeOrExpense = new PrintWriter(new FileWriter(home+"/IncOrExp.txt"));
                PrintWriter txtCategorys = new PrintWriter(home+"/Categorys.txt");
            
                txtIncomeOrExpense.println(id+"%");
                txtCategorys.println(id+"%");
                txtIncomeOrExpense.close();
                txtCategorys.close();
                System.out.println("File created");
            }
            catch(Exception e)
            {
                System.out.println("File not created");
            }
        }
    }
    
    public void getData(String id)
    {
        String incomeOrExpenseStringSplited[],textDataToSplit[];
        fileNameIncomeOrExpense = System.getProperty("user.home")+"/SaveMyMoney/"+id+"/IncOrExp.txt";
        fileNameCategory = System.getProperty("user.home")+"/SaveMyMoney/"+id+"/IncOrExp.txt";
        
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
        incomeOrExpense = new ArrayList<IncomeOrExpense>();
                
        for(int i = 0 ; i<textDataToSplit.length; i++)
        {
            System.out.println(textDataToSplit[i]);
            incomeOrExpenseStringSplited = textDataToSplit[i].split("&");
            IncomeOrExpense incomeOrExpenseToArray = new IncomeOrExpense(id,Integer.parseInt(incomeOrExpenseStringSplited[0]),Double.parseDouble(incomeOrExpenseStringSplited[1]),incomeOrExpenseStringSplited[2],incomeOrExpenseStringSplited[3]);
            incomeOrExpense.add(i,incomeOrExpenseToArray);
        }
    }
}