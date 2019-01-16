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
 * @author alejavilas92 Interface to add a new data. The user will introduce a
 * income/expense, a quantity, a category and if user wants , a note.
 */
public class AddView extends JDialog implements ActionListener {

    private final String id;
    private JTextField txtIncomeOrExpense, txtQuantity, txtCategory, txtNote, txtDay, txtMonth, txtYear;
    private JLabel lblIncOrExpText, lblQuantityText, lblCategoryText, lblNoteText, lblDateText;
    private JButton btnAdd;
    private final MainView mainView;
    public PanelAdd panelAdd;
    private JFrame f = new JFrame();

    /**
     * Inizializate the class and chargue the graphic configuration. Chargue
     * labels, text box and categories view.
     *
     * @param userID Used to chargue the user data.
     * @param view It references the previus view.
     */
    public AddView(String userID, MainView view) {

        id = userID;
        f.setSize(1000, 700);
        f.setTitle("Save my money");
        f.getContentPane().setLayout(null);
        f.setBackground(Color.white);
        chargueTextBox();
        chargueLabel();
        buttonSettings();
        mainView = view;
        panelAdd = new PanelAdd(mainView.data);
        panelAdd.setSize(new Dimension(1000, 700));
        panelAdd.setLayout(null);
        f.add(panelAdd);
        f.setVisible(true);
    }

    /**
     * When the user clicks on btnAdd, all daqta saved on text fields will be
     * write on .txt file of the user directoy
     *
     * @param e Parameter of actionPerformed method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String noteToFile, error = "";
            noteToFile = txtNote.getText();

            try {
                //Check errors
                if ((Integer.parseInt(txtIncomeOrExpense.getText()) < 0 || Integer.parseInt(txtIncomeOrExpense.getText()) > 1) || txtIncomeOrExpense.getText() == null) {
                    JOptionPane.showMessageDialog(null, "Insert 0 for a income or 1 for a expense", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (Integer.parseInt(txtDay.getText()) > 31 || Integer.parseInt(txtDay.getText()) < 0
                        || Integer.parseInt(txtMonth.getText()) > 12 || Integer.parseInt(txtMonth.getText()) < 0
                        || Integer.parseInt(txtYear.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Days have to be between 1 and 31, months have to \n be between 1 and 12 and years must to be major than 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (noteToFile.equals("")) {
                    noteToFile = " ";
                }

                boolean equals = false;
                int index;

                do {
                    index = (int) (Math.random() * 1000000 + 1);

                    for (int i = 0; i < mainView.data.incomeOrExpense.size(); i++) {
                        for (int j = 0; j < mainView.data.incomeOrExpense.size() && j != i; j++) {
                            if (mainView.data.incomeOrExpense.get(i).index == mainView.data.incomeOrExpense.get(j).index) {
                                equals = true;
                            }
                        }
                    }
                } while (equals);

                new IncomeOrExpense(id, Integer.parseInt(txtIncomeOrExpense.getText()), Double.parseDouble(txtQuantity.getText()), txtCategory.getText(), noteToFile, Integer.parseInt(txtDay.getText()), Integer.parseInt(txtMonth.getText()), Integer.parseInt(txtYear.getText()), index).saveFile();

                f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));

                mainView.data = new Data(id);
                mainView.panel.panelData = mainView.data;
                mainView.f.repaint();
            } catch (Exception exception) {
                if (exception.getMessage().equals("For input string: \"\"")) {
                    error = "You must to insert dates to save, in the fields text";
                }else {
                    error = exception.getMessage();
                }
                System.out.println(exception.getMessage());
                JOptionPane.showMessageDialog(null, error + "\n Look the fields text and check the data." , "Fatal error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * It configure the button of the view.
     */
    private void buttonSettings() {
        btnAdd = new JButton("add");
        btnAdd.setBounds(900, 600, 50, 50);
        btnAdd.setBackground(Color.BLUE);
        btnAdd.addActionListener(this);
        btnAdd.setVisible(true);
        f.add(btnAdd);
    }

    /**
     * Creates the labels and set them a configuration.
     */
    private void chargueLabel() {
        lblIncOrExpText = new JLabel();
        lblQuantityText = new JLabel();
        lblCategoryText = new JLabel();
        lblNoteText = new JLabel();
        lblDateText = new JLabel();

        lblIncOrExpText.setText("Income/Expense");
        lblQuantityText.setText("Quantity");
        lblCategoryText.setText("Category");
        lblNoteText.setText("Note");
        lblDateText.setText("Date");

        completeLabel(lblIncOrExpText, 200, 30, 100, 65);
        completeLabel(lblQuantityText, 200, 30, 100, 140);
        completeLabel(lblCategoryText, 200, 30, 100, 215);
        completeLabel(lblNoteText, 200, 30, 100, 365);
        completeLabel(lblDateText, 200, 30, 100, 290);
    }

    /**
     * Creates the text boxes and set them a configuration.
     */
    private void chargueTextBox() {

        txtIncomeOrExpense = new JTextField();
        txtQuantity = new JTextField();
        txtCategory = new JTextField();
        txtNote = new JTextField();
        txtDay = new JTextField();
        txtMonth = new JTextField();
        txtYear = new JTextField();

        completeTextBox(txtIncomeOrExpense, 200, 30, 100, 100);
        completeTextBox(txtQuantity, 200, 30, 100, 175);
        completeTextBox(txtCategory, 200, 30, 100, 250);
        completeTextBox(txtDay, 30, 30, 100, 325);
        completeTextBox(txtMonth, 30, 30, 150, 325);
        completeTextBox(txtYear, 50, 30, 200, 325);
        completeTextBox(txtNote, 250, 150, 100, 400);
    }

    /**
     * Set values to configure the labels
     *
     * @param jLabel Label to configure.
     * @param sizeX Width of the label.
     * @param sizeY Height of the label.
     * @param alignX X coordinate.
     * @param alignY Y coordinate.
     */
    private void completeLabel(JLabel jLabel, int sizeX, int sizeY, int alignX, int alignY) {
        jLabel.setBounds(alignX, alignY, sizeX, sizeY);
        f.add(jLabel);
    }

    /**
     * Set values to configure the text box
     *
     * @param jTextField JTextField to configure.
     * @param sizeX Width of the label.
     * @param sizeY Height of the label.
     * @param alignX X coordinate.
     * @param alignY Y coordinate
     */
    private void completeTextBox(JTextField jTextField, int sizeX, int sizeY, int alignX, int alignY) {
        jTextField.setBounds(alignX, alignY, sizeX, sizeY);
        f.add(jTextField);

    }

    /**
     * It used for draw the view elements that haven't been configurated, they
     * are used for indicate the common categories which are utilized by the
     * user.
     *
     */
    public class PanelAdd extends JPanel {

        private final Data data;

        public PanelAdd(Data userData) {
            data = userData;
        }

        /**
         * Paint the rects and text to make visible a view that shows the common
         * categories which are utilized by the user.
         *
         * @param grphcs The Graphics context in which to paint.
         */
        @Override
        public void paintComponent(Graphics grphcs) {
            grphcs.drawString("\bCategories usually used", 600, 50);
            grphcs.drawString("Insert 0 for a income or 1 for a expense", 250, 85);
            grphcs.drawString("Quantity total (Acept double numbers)", 200, 160);
            grphcs.drawString("Category (Salary,Finance etc)", 200, 235);
            grphcs.drawString("Write here a comment. (Is not neccesary)", 180, 385);
            grphcs.drawString("dd/mm/yyyy", 180, 310);

            if (data.categoriesData.length == 0) {
                return;
            }

            for (int i = 0; i < data.categoriesData.length && i <= 11; i++) {
                if (data.categoriesData[i] == null) {
                    continue;
                }
                grphcs.setColor(data.categoriesData[i].color);
                grphcs.fillRect(600, 80 + (i * 50), 250, 30);
                grphcs.setColor(Color.WHITE);
                grphcs.drawString(data.categoriesData[i].category, 610, 100 + (i * 50));
            }
        }
    }
}
