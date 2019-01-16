/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_my_money;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * View of schendule interface. It's composed by various fiels text to introduce
 * two dates, a button whitch compare the data between the two previus dates,
 * and a panel with a circle graphic,and two bar graphics.
 *
 * @author alexv
 */
public class SchenduleView extends JDialog implements ActionListener {

    private Data data;
    private JTextField dateFromYearTxt, dateFromDayTxt, dateFromMonthTxt, dateToYearTxt, dateToDayTxt, dateToMonthTxt;
    private JButton selectBtn;
    private JFrame f;
    private JLabel dateToLbl, dateFromLbl;
    private PanelBalance panel;

    public SchenduleView(Data data, MainView mainView) {
        this.data = data;
        panel = new PanelBalance(data.categoriesData);
        panel.setSize(new Dimension(1000, 700));
        panel.setLayout(null);
        f = new JFrame();
        f.setTitle("Save my money");
        f.setBackground(Color.white);
        f.setSize(1000, 700);
        f.getContentPane().setLayout(null);
        createButtonsAndFields();
        f.add(panel);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectBtn) {
            String error = "";

            try {
                int from = Integer.parseInt(dateFromDayTxt.getText()) + (Integer.parseInt(dateFromMonthTxt.getText()) * 31) + (Integer.parseInt(dateFromYearTxt.getText()) * 365);
                int to = Integer.parseInt(dateToDayTxt.getText()) + (Integer.parseInt(dateToMonthTxt.getText()) * 31) + (Integer.parseInt(dateToYearTxt.getText()) * 365);

                if (Integer.parseInt(dateFromDayTxt.getText()) < 1 || Integer.parseInt(dateFromDayTxt.getText()) > 31
                        || Integer.parseInt(dateFromMonthTxt.getText()) < 1 || Integer.parseInt(dateFromMonthTxt.getText()) > 12
                        || Integer.parseInt(dateFromYearTxt.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Days have to be between 1 and 31, months have to \n be between 1 and 12 and years must to be major than 0", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(dateToDayTxt.getText()) < 1 || Integer.parseInt(dateToDayTxt.getText()) > 31
                        || Integer.parseInt(dateToMonthTxt.getText()) < 1 || Integer.parseInt(dateToMonthTxt.getText()) > 12
                        || Integer.parseInt(dateToYearTxt.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Days have to be between 1 and 31, months have to \n be between 1 and 12 and years must to be major than 0", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (from > to) {
                    JOptionPane.showMessageDialog(null, "\"Date to\" must to be major than \"date from\"", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    panel.setCategories(createCategories(createDataArray(from, to)));
                    f.repaint();
                }
            } catch (Exception exception) {
                if (exception.getMessage().equals("For input string: \"\"")) {
                    error = "You must to insert dates to compare, in the fields text";
                } else {
                    error = exception.getMessage();
                }
                JOptionPane.showMessageDialog(null, error + "\n Look the fields text and check the data." , "Fatal error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Set the buttons and fields text graphic configuration
     */
    private void createButtonsAndFields() {
        dateFromYearTxt = new JTextField();
        dateToYearTxt = new JTextField();
        dateFromMonthTxt = new JTextField();
        dateToMonthTxt = new JTextField();
        dateFromDayTxt = new JTextField();
        dateToDayTxt = new JTextField();
        selectBtn = new JButton("Check");
        dateFromLbl = new JLabel("Date From (dd/mm/yyy)");
        dateToLbl = new JLabel("Date To (dd/mm/yyyy)");

        dateFromYearTxt.setBounds(150, 75, 60, 30);
        dateToYearTxt.setBounds(150, 150, 60, 30);
        dateFromMonthTxt.setBounds(100, 75, 30, 30);
        dateToMonthTxt.setBounds(100, 150, 30, 30);
        dateFromDayTxt.setBounds(50, 75, 30, 30);
        dateToDayTxt.setBounds(50, 150, 30, 30);
        dateFromLbl.setBounds(50, 40, 200, 30);
        dateToLbl.setBounds(50, 115, 200, 30);

        selectBtn.setBounds(50, 200, 100, 30);
        selectBtn.setBackground(Color.yellow);
        selectBtn.addActionListener(this);

        f.add(dateFromYearTxt);
        f.add(dateToYearTxt);
        f.add(dateFromMonthTxt);
        f.add(dateToMonthTxt);
        f.add(dateFromDayTxt);
        f.add(dateToDayTxt);
        f.add(dateToLbl);
        f.add(dateFromLbl);
        f.add(selectBtn);
    }

    /**
     * Create a Categories array formed from an ArrayList of IncomeOrExpense.
     *
     * @param dateArray Have the data to make the categories array.
     * @return
     */
    private Categories[] createCategories(ArrayList<IncomeOrExpense> dateArray) {
        Categories[] categoriesFinalArray;
        ArrayList<String> categories = new ArrayList<>();

        for (int i = 0; i < dateArray.size(); i++) {
            double incomesTotal = 0, expensesTotal = 0;

            IncomeOrExpense conceptGetted = dateArray.get(i);

            if (conceptGetted.getConcept() == 0) {
                incomesTotal += conceptGetted.getQuantity();
            } else {
                expensesTotal += conceptGetted.getQuantity();
            }

            if (categories.isEmpty()) {
                categories.add(conceptGetted.category);
            } else {
                boolean repeatCategory = false;
                //For o check if a category is new or a repeated
                for (int j = 0; j < categories.size(); j++) {
                    String categoryGetted = categories.get(j);
                    if (categoryGetted.equals(conceptGetted.category)) {
                        repeatCategory = true;
                    }
                }

                if (!repeatCategory) {
                    categories.add(conceptGetted.category);
                }
            }
        }

        categoriesFinalArray = new Categories[categories.size()];

        for (int i = 0; i < categories.size(); i++) {
            categoriesFinalArray[i] = new Categories();
            categoriesFinalArray[i].category = categories.get(i);

            for (int j = 0; j < data.categoriesData.length; j++) {
                if (data.categoriesData[j] == null) {
                    continue;
                }
                if (data.categoriesData[j].category.equals(categoriesFinalArray[i].category)) {
                    categoriesFinalArray[i].color = data.categoriesData[j].color;
                    break;
                }
            }
        }

        for (int i = 0; i < dateArray.size(); i++) {
            for (int j = 0; j < categoriesFinalArray.length; j++) {
                if (categoriesFinalArray[j].category.equals(dateArray.get(i).getCategory())) {
                    if (dateArray.get(i).concept == 0) {
                        categoriesFinalArray[j].income += dateArray.get(i).quantity;
                    } else {
                        categoriesFinalArray[j].expense += dateArray.get(i).quantity;
                    }
                }
            }
        }

        return categoriesFinalArray;
    }

    /**
     * Create a IncomeOrExpense arrayList whitch date are between from variable
     * and to variable.
     *
     * @param from Date since we take data to form the new arrayList.
     * @param to Date until we take data to form the new arrayList.
     * @return An ArrayList formed with the data between date from and date to.
     */
    private ArrayList createDataArray(int from, int to) {
        ArrayList<IncomeOrExpense> dateArray = new ArrayList<>();

        for (int i = 0; i < data.incomeOrExpense.size(); i++) {
            if (data.incomeOrExpense.get(i).dateToInt >= from && data.incomeOrExpense.get(i).dateToInt <= to) {
                dateArray.add(data.incomeOrExpense.get(i));
            }
        }

        return dateArray;
    }

    /**
     * Panel to show the data extract of an array of IncomesAndExpensesDates.
     * This panel will be repainted each time button be selectBtn pressed.
     */
    private class PanelBalance extends JPanel {

        private Categories[] categories;

        public PanelBalance(Categories[] categories) {
            this.categories = categories;
        }

        /**
         * Paint the panel destiny to show the balance graphic ,the two
         * categories graphics bar and a list of the most categories used.
         *
         * @param g The Graphics context in which to paint.
         */
        @Override
        public void paintComponent(Graphics g) {
            if (categories.length == 0) {
                return;
            }

            double multiplier, angleIncome, angleExpense, incomesTotal = 0, expensesTotal = 0, maxIncome = 0, maxExpense = 0;

            for (int i = 0; i < categories.length; i++) {
                if (categories[i] == null) {
                    continue;
                }

                incomesTotal += categories[i].income;
                expensesTotal += categories[i].expense;

                if (categories[i].income > maxIncome) {
                    maxIncome = categories[i].income;
                }

                if (categories[i].expense > maxExpense) {
                    maxExpense = categories[i].expense;
                }
            }

            angleIncome = 360 * (incomesTotal / (incomesTotal + expensesTotal));
            angleExpense = 360 - angleIncome;

            g.setColor(Color.green);
            g.fillArc(30, 300, 300, 300, 0, (int) angleIncome);
            g.fillRect(400, 50, 200, 50);
            g.setColor(Color.red);
            g.fillRect(400, 350, 200, 50);
            g.fillArc(30, 300, 300, 300, (int) angleIncome, (int) angleExpense);
            g.fillArc(30, 300, 300, 300, (int) angleExpense + (int) angleIncome, 360 - ((int) angleIncome + (int) angleExpense));
            g.setColor(Color.black);
            g.drawString("Incomes total : " + incomesTotal, 410, 75);
            g.drawString("Expenses total : " + expensesTotal, 410, 375);

            int index = 0;

            //Draw incomes
            for (int i = 0; i < categories.length; i++) {
                if (categories[i] == null) {
                    continue;
                }

                if (categories[i].income != 0) {
                    g.setColor(categories[i].color);
                    g.fillOval(362, 125 + (((175/categories.length)*2) * index), 175/categories.length, 175/categories.length);
                    g.fillRect(400, 125 + (((175/categories.length)*2) * index), (int) (250 * (categories[i].income / maxIncome)), 175/categories.length);
                    g.setColor(Color.white);
                    g.drawString("" + (int) ((categories[i].income / incomesTotal) * 100) + " %", 405, 138 + (((175/categories.length)*2) * index));
                    index++;
                }
            }

            index = 0;
            
            //Draw expenses
            for (int i = 0; i < categories.length ; i++) {
                if (categories[i] == null) {
                    continue;
                }

                if (categories[i].expense != 0) {
                    g.setColor(categories[i].color);
                    g.fillOval(362, 410 + (((175/categories.length)*2) * index), 175/categories.length, 175/categories.length);
                    g.fillRect(400, 410 + (((175/categories.length)*2) * index), (int) (250 * (categories[i].expense / maxExpense)), 175/categories.length);
                    g.setColor(Color.white);
                    g.drawString("" + (int) ((categories[i].expense / expensesTotal) * 100) + " %", 405, 423 + (((175/categories.length)*2) * index));
                    index++;
                }
            }

            g.setColor(Color.orange);
            g.fillRect(760, 30, 145, 30);
            g.setColor(Color.white);
            g.drawString("Categories", 800, 50);

            //Draw categories
            for (int i = 0; i < categories.length; i++) {
                if (categories[i] == null) {
                    continue;
                }

                g.setColor(categories[i].color);
                g.fillRect(760, 90 + (60 * i), 145, 30);
                g.setColor(Color.white);
                g.drawString(categories[i].category, 800, 110 + (60 * i));
            }

        }

        public void setCategories(Categories[] categories) {
            this.categories = categories;
        }
    }

}
