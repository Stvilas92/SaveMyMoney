package save_my_money;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;


public class Save_My_Money 
{   
    public static Data data;
    private static String id;
    private static File file;
    
    public static void main(String[] args) 
    {
        chargeData();
        MainView mainView = new MainView(id);
    }
    
    // Request a ID user, if is first time to the user, or he wants to create other ID, we proceed to create a new ID 
    //For all previous process, we use the file class
    public static void chargeData() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del programa");
        id = sc.nextLine();
        
        data = new Data(id);
    }
}