
package save_my_money;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddView extends Window implements ActionListener
{
    private static final int INCOME = 0;
    private static final int EXPENSE = 1;
    private String id;
    private JTextField txtIncomeOrExpense,txtQuantity,txtCategory,txtNote;
    private JLabel lblIncOrExpText,lblQuantityText,lblCategoryText,lblNoteText;
    private JButton btnAdd;
    
    public AddView(String userID)
    {
        id = userID;        
        setWindowSize(1000,700);
        getContentPane().setLayout(null);
        chargueTextBox();
        chargueLabel();
        buttonSettings();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnAdd)
        {
            String noteToFile = "";
            noteToFile = txtNote.getText(); 
           
            if(noteToFile.equals(""))
                noteToFile = " ";
            
            new IncomeOrExpense(id,Integer.parseInt(txtIncomeOrExpense.getText()),Double.parseDouble(txtQuantity.getText()),txtCategory.getText(),noteToFile).saveFile();
        }
    }
    
    private void buttonSettings()
    {
        btnAdd = new JButton("add");
        btnAdd.setBounds(900,600,50,50);
        btnAdd.setBackground(Color.BLUE);
        btnAdd.addActionListener(this);
        btnAdd.setVisible(true);
        add(btnAdd);
    }
    
    private void chargueLabel()
    {
        lblIncOrExpText = new JLabel();
        lblQuantityText = new JLabel();
        lblCategoryText = new JLabel();
        lblNoteText = new JLabel();
        
        lblIncOrExpText.setText("Income/Expense");
        lblQuantityText.setText("Quantity");
        lblCategoryText.setText("Category");
        lblNoteText.setText("Note");
        
        completeLabel(lblIncOrExpText,200,30,100,65);
        completeLabel(lblQuantityText,200,30,100,140);
        completeLabel(lblCategoryText,200,30,100,215);
        completeLabel(lblNoteText,200,30,100,315);
    }
    
    private void chargueTextBox()
    {
        
        txtIncomeOrExpense = new JTextField();
        txtQuantity = new JTextField();
        txtCategory = new JTextField();
        txtNote     = new JTextField();
        
        completeTextBox(txtIncomeOrExpense,200,30,100,100);
        completeTextBox(txtQuantity,200,30,100,175);
        completeTextBox(txtCategory,200,30,100,250);
        completeTextBox(txtNote,250,150,100,350);
    }
    
    
    private void completeLabel(JLabel jLabel,int sizeX, int sizeY, int alignX, int alignY)
    {
        jLabel.setBounds(alignX, alignY, sizeX, sizeY);
        add(jLabel);
    }
    
    private void completeTextBox(JTextField jTextField,int sizeX, int sizeY, int alignX, int alignY)
    {
        jTextField.setBounds(alignX, alignY, sizeX, sizeY);
        add(jTextField);
    }
}