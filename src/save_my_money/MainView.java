

package save_my_money;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MainView extends Window implements ActionListener 
{  
    private String id;
    private JButton schenduleButton,incomeButton ,expensesButton ,balanceButton ,addIncOrExpButton ;
    private Window openWindow;
    
    public MainView(String userId)
    {
        id = userId;
                
        setWindowSize(1000,700);
        getContentPane().setLayout(null); 
        chargueButtons();
        chargueGraphics();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == addIncOrExpButton)
        {
            //Remember this setVisible() when we implements backs.
            setVisible(false);
            AddView addView = new AddView(id);
        }
    }
    
    
    private void chargueButtons()
    {
        schenduleButton = new JButton("<html>Schendule <br> 0000");
        incomeButton  = new JButton("Incomes");
        expensesButton  = new JButton("Expenses");
        balanceButton  = new JButton("Balance");
        addIncOrExpButton  = new JButton("+");
        //addIncOrExpButton.setBorder(new RoundedBorder(20));
        
        completeButton(schenduleButton,150,40,Color.yellow,60,10);
        completeButton(incomeButton,150,40,Color.green,60,80);
        completeButton(expensesButton,150,40,Color.red,400,80);
        completeButton(balanceButton,150,40,Color.white,800,80);
        completeButton(addIncOrExpButton,50,50,Color.blue,900,600);
    }
    
    
    //this method will used by us in the future
    //it will draw the graphics on the main view
    private void chargueGraphics()
    {
        Charts charts = new Charts();
        charts.drawCircleGraphic();
    }
    
    private void completeButton(JButton button,int sizeX, int sizeY,Color color, int alignX, int alignY)
    {
        button.setBackground(color);
        button.setBounds(alignX,alignY,sizeX,sizeY);
        button.addActionListener(this);
        add(button);
    }
   
}