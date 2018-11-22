package save_my_money;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * 
 * @author alejavilas92
 * Interface to add a new data. The user will introduce a income/expense,
 * a quantity, a category and if user wants , a note. 
 */
public class AddView extends JDialog implements ActionListener
{
    private static final int INCOME = 0;
    private static final int EXPENSE = 1;
    private String id;
    private JTextField txtIncomeOrExpense,txtQuantity,txtCategory,txtNote;
    private JLabel lblIncOrExpText,lblQuantityText,lblCategoryText,lblNoteText;
    private JButton btnAdd;
    private MainView mainView;
    public PanelAdd panelAdd;
    private JFrame f = new JFrame();
    
    /**
     * Inizializate the class and chargue the graphic configuration.
     * Chargue labels, text box and categories view.
     * @param userID Used to chargue the user data.
     * @param view It references the previus view.
     */
    public AddView(String userID, MainView view)
    {
        
        id = userID;        
        f.setSize(1000,700);
        f.getContentPane().setLayout(null);
        chargueTextBox();
        chargueLabel();
        buttonSettings();
        mainView = view;
        panelAdd = new PanelAdd(mainView.data);
        panelAdd.setSize(new Dimension (1000, 700));
        panelAdd.setLayout(null);
        f.add(panelAdd);
        f.setVisible(true);
    }
    
    /**
     * When the user clicks on btnAdd, all daqta saved on text fields
     * will be write on .txt file of the user directoy
     * @param e Parameter of actionPerformed method.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnAdd)
        {
            String noteToFile;
            noteToFile = txtNote.getText(); 
           
            if(noteToFile.equals(""))
                noteToFile = " ";
            
            new IncomeOrExpense(id,Integer.parseInt(txtIncomeOrExpense.getText()),Double.parseDouble(txtQuantity.getText()),txtCategory.getText(),noteToFile).saveFile();
            
            f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
            
            mainView.data = new Data(id);
            mainView.panel.panelData = mainView.data ;
            mainView.f.repaint();
        }
    }
    
    /**
     * It configure the button of the view.
     */
    private void buttonSettings()
    {
        btnAdd = new JButton("add");
        btnAdd.setBounds(900,600,50,50);
        btnAdd.setBackground(Color.BLUE);
        btnAdd.addActionListener(this);
        btnAdd.setVisible(true);
        f.add(btnAdd);
    }
    
    /**
     * Creates the labels and set them a configuration.
     */
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
    
    /**
     * Creates the text boxes and set them a configuration.
     */
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
    
    /**
     * Set values to configure the labels
     * @param jLabel Label to configure.
     * @param sizeX Width of the label.
     * @param sizeY Height of the label.
     * @param alignX X coordinate.
     * @param alignY  Y coordinate.
     */
    private void completeLabel(JLabel jLabel,int sizeX, int sizeY, int alignX, int alignY)
    {
        jLabel.setBounds(alignX, alignY, sizeX, sizeY);
        f.add(jLabel);
    }
    
    /**
     * Set values to configure the text box
     * @param jTextField JTextField to configure.
     * @param sizeX Width of the label.
     * @param sizeY Height of the label.
     * @param alignX X coordinate.
     * @param alignY  Y coordinate 
     */
    private void completeTextBox(JTextField jTextField,int sizeX, int sizeY, int alignX, int alignY)
    {
        jTextField.setBounds(alignX, alignY, sizeX, sizeY);
        f.add(jTextField);
        
    }
    
    /**
     * It used for draw the view elements that haven't been configurated, they are
     * used for indicate the common categories which are utilized by the user.
     * 
     */
    public class PanelAdd extends JPanel
    {
        private final Data data;
        public PanelAdd(Data userData)
        {
            data = userData;
        }
        
        /**
         * Paint the rects and text to make visible a view that shows 
         * the common categories which are utilized by the user.
         * @param grphcs The Graphics context in which to paint.
         */
        @Override
        public void paintComponent(Graphics grphcs)
        {
            if(data.categoriesData.length == 0)
                return;
            grphcs.drawString("\bCategories usually used", 600, 50);
            grphcs.drawString("Insert 0 for a income or 1 for a expense", 250, 85);
            grphcs.drawString("Quantity total (Acept double numbers)", 200, 160);
            grphcs.drawString("Category (Salary,Finance etc)", 200, 235);
            grphcs.drawString("Write here a comment. (Is not neccesary)", 180, 335);
            
            for(int i = 0; i<data.categoriesData.length; i++)
            {
                if(i > 11)
                    break;
                grphcs.setColor(data.categoriesData[i].color);
                grphcs.fillRect(600, 80 + (i*50), 250, 30);
                grphcs.setColor(Color.WHITE);
                grphcs.drawString(data.categoriesData[i].category, 610, 100 + (i*50));
            }
        }
    }
}