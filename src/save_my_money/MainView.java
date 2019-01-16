package save_my_money;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to chargue the graphic interface of the program. The user will can
 * change the interface view cliking on differents buttons.
 *
 */
public class MainView extends Window implements ActionListener {

    private static final int GENERAL = 0;
    private static final int INCOME = 1;
    private static final int EXPENSE = 2;
    private static final int BALANCE = 3;

    private String id;
    private JButton schenduleButton, incomeButton, expensesButton, balanceButton, addIncOrExpButton, btnGeneral;
    private Graphics graphics;
    public Data data;
    public GeneralPanel panel;

    /**
     * Inicializate the class, load the user data and configure the visual
     * interface.Data will be chargued with an id, that the user will write on a
     * JOptionPane. If id introducted not refence any data, it creates a new
     * directory in SaveMyMoney directory, and put in a new .txt to save the
     * future data.
     */
    public MainView() {
        id = chargeData();
        data = new Data(id);

        f.setSize(new Dimension(1000, 700));
        getContentPane().setLayout(null);
        chargueButtons();

        panel = new GeneralPanel(data, GENERAL);
        f.add(panel);
        f.setVisible(true);
    }

    /**
     * If user clicks on addIncOrExpButton, a new window will be opened to add
     * new data. If user clicks on If user clicks on, btnGeneral,
     * expensesButton, incomeButton, balanceButton or schenduleButton, the
     * program will chage the visual panel dependding on the button selected.
     *
     * @param e Parameter of actionPerformed method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIncOrExpButton) {
            //Remember this setVisible() when we implements backs.
            setVisible(false);
            AddView addView = new AddView(id, this);
        }

        if (e.getSource() == balanceButton) {
            panel.panelType = BALANCE;
            f.add(panel);
            f.repaint();
        }

        if (e.getSource() == btnGeneral) {
            panel.panelType = GENERAL;
            f.add(panel);
            f.repaint();
        }

        if (e.getSource() == expensesButton) {
            panel.panelType = EXPENSE;
            f.add(panel);
            f.repaint();
        }

        if (e.getSource() == incomeButton) {
            panel.panelType = INCOME;
            f.add(panel);
            f.repaint();
        }

        if (e.getSource() == schenduleButton) {
            setVisible(false);
            SchenduleView schenduleView = new SchenduleView(data, this);
        }
    }

    /**
     * Set the buttons graphic configuration
     */
    private void chargueButtons() {
        schenduleButton = new JButton("Schendule"); //("<html>Schendule <br> 0000");
        incomeButton = new JButton("Incomes");
        expensesButton = new JButton("Expenses");
        balanceButton = new JButton("Balance");
        addIncOrExpButton = new JButton("+");
        btnGeneral = new JButton("General");
        //addIncOrExpButton.setBorder(new RoundedBorder(20));

        completeButton(schenduleButton, 150, 40, Color.yellow, 830, 50);
        completeButton(incomeButton, 150, 40, Color.green, 230, 50);
        completeButton(expensesButton, 150, 40, Color.red, 430, 50);
        completeButton(balanceButton, 150, 40, Color.orange, 630, 50);
        completeButton(btnGeneral, 150, 40, Color.white, 30, 50);
        completeButton(addIncOrExpButton, 50, 50, Color.blue, 900, 600);
    }

    /**
     * Chargue the user data to analyze and in the program. Data will be
     * chargued with an id, that the user will write on a JOptionPane.
     *
     * @return
     */
    private String chargeData() {
        return JOptionPane.showInputDialog(f, "Write a file to load, also you can \ncreate a file writing his name here");
    }

    /**
     * Complete button's configuration.
     *
     * @param button JButton to configure
     * @param sizeX Width of the button.
     * @param sizeY Height of the button.
     * @param color Color of the button.
     * @param alignX X coordinate.
     * @param alignY Y coordinate.
     */
    private void completeButton(JButton button, int sizeX, int sizeY, Color color, int alignX, int alignY) {
        button.setBackground(color);
        button.setBounds(alignX, alignY, sizeX, sizeY);
        button.addActionListener(this);
        f.add(button);
    }

    /**
     * 
     * @return data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data 
     */
    public void setData(Data data) {
        this.data = data;
    }
    
    

    /**
     * On this class we configure the graphics part of the interface.
     */
    public class GeneralPanel extends JPanel {

        Data panelData;
        int panelType;

        /**
         * Inicializate the GeneralPanel class.
         *
         * @param dataGetted Data of the user.
         * @param panelToDraw Type the panel to draw. There are four panel
         * types; incomes, expenses,general,balance and schendule.
         */
        public GeneralPanel(Data dataGetted, int panelToDraw) {
            panelData = dataGetted;
            panelType = panelToDraw;
        }

        /**
         * Paint the panel type chosen by user.
         *
         * @param g The Graphics context in which to paint.
         */
        @Override
        public void paintComponent(Graphics g) {
            switch (panelType) {
                case GENERAL: {
                    drawGeneralPanel(g);
                    break;
                }

                case INCOME: {
                    drawIncomePanel(g);
                    break;
                }

                case EXPENSE: {
                    drawExpensePanel(g);
                    break;
                }

                case BALANCE: {
                    drawBalancePanel(g);
                    break;
                }
            }
        }

        /**
         * Draw the balance panel. It composes by a circle graph of the total
         * balance, a line graph of the balance evolution, and a bar horizontal
         * graph of the incomes and the expenses.
         *
         * @param g The Graphics context in which to paint.
         */
        private void drawBalancePanel(Graphics g) {
            if (panelData.categoriesData.length == 0) {
                return;
            }

            double multiplierIncome, multiplierExpense, multiplier, multiplierBar;
            if (panelData.maxExpense > data.maxIncome) {
                multiplier = 360 / panelData.maxExpense;
                multiplierBar = 100 / panelData.maxExpense;
            } else {
                multiplier = 360 / panelData.maxIncome;
                multiplierBar = 100 / panelData.maxIncome;
            }

            //Draw a rounded graph
            //Draw the incomes part of the graph
            int angleArcIncome = ((int) (panelData.percentIncome * 360)) + panelData.angleExtra;
            int angleArcExpense = (int) (panelData.percentExpense * 360);
            g.setColor(Color.green);
            g.fillArc(650, 200, 300, 300, 0, angleArcIncome);
            //Draw the expense part of the graph
            g.setColor(Color.red);
            g.fillArc(650, 200, 300, 300, angleArcIncome, angleArcExpense);

            /*//Draw bar graph
            int n = (700 / data.categoriesData.length) / 2;
            int lastIncomeDraw = 0;
            data.orderCategoriesByIncome();

            for (int i = 0; i < data.categoriesData.length; i++) {
                if (data.categoriesData[i] == null) {
                    continue;
                }
                if (data.categoriesData[i].income != 0) {
                    lastIncomeDraw = 150 + (n * i);
                    g.setColor(data.categoriesData[i].color);
                    g.fillRect(500, lastIncomeDraw, (int) (data.categoriesData[i].income * multiplierBar), n);
                }
            }

            int expenseToDraw, sizeExpense = 0;
            data.orderCategoriesByExpense();
            for (int i = data.categoriesData.length - 1; i >= 0; i--) {
                sizeExpense++;
            }

            int contExp = sizeExpense;
            for (int i = data.categoriesData.length - 1; i >= 0; i--) {
                if (data.categoriesData[i] == null) {
                    continue;
                }
                if (data.categoriesData[i].expense != 0) {
                    contExp--;
                    expenseToDraw = (int) (data.categoriesData[i].expense * multiplierBar);
                    g.setColor(data.categoriesData[i].color);
                    g.fillRect(500 - expenseToDraw, lastIncomeDraw + (n * (sizeExpense - contExp)), expenseToDraw, n);
                }
            }*/

            // Draw line graph
            g.setColor(Color.black);
            g.drawLine(50, 500, 50, 150);
            g.drawLine(50, 500, 400, 500);

            Schendule sch = new Schendule();
            IncomeOrExpenseDates[] dataSchendule = sch.balanceAlongTime(data.incomeOrExpense);
            double maxBalance = 0;
            
            for (int i = 0; i < dataSchendule.length; i++) {
                if(dataSchendule[i].balance > maxBalance)
                    maxBalance = dataSchendule[i].balance;
            }
            
            g.drawString("0", 50, 530);
            g.drawString("" + maxBalance, 50, 135);

            double horizontalMultiplier = 340 / dataSchendule.length;
            double verticalMultiplier = 0;

            for(int i = 0; i < dataSchendule.length; i++) {
                if (dataSchendule[i].balance > verticalMultiplier) {
                    verticalMultiplier = dataSchendule[i].balance;
                }
            }

            verticalMultiplier = 340 / verticalMultiplier;

            for (int i = 0; i < dataSchendule.length - 1; i++) {
                int x1, x2, y1, y2;
                x1 = (int) (60 + i * horizontalMultiplier);
                x2 = (int) (60 + ((i + 1) * horizontalMultiplier));
                y1 = (int) (490 - (dataSchendule[i].balance * verticalMultiplier));
                y2 = (int) (490 - (dataSchendule[i + 1].balance * verticalMultiplier));
                if (y2 < y1) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }
                g.drawLine(x1, y1, x2, y2);
            }
            int a = 1;
        }

        /**
         * Draw the expenses interface.It composes by a circle graph of the
         * expenses categories and a horizontal bar graph of expenses,ordered
         * descendantly.
         *
         * @param g The Graphics context in which to paint.
         */
        private void drawExpensePanel(Graphics g) {
            if (panelData.categoriesData.length == 0) {
                return;
            }

            g.setColor(Color.RED);
            g.fillRect(60, 150, 250, 50);
            g.setColor(Color.BLACK);
            g.drawString("Expenses total: " + data.expensesTotal, 64, 170);

            int xPadding = 30, yPadding = 250, drawCont = -1, angle = 0, lastAngle = 0;
            double multiplier = 250 / data.maxExpense;
            panelData.orderCategoriesByExpense();

            for (int i = 0; i < panelData.categoriesData.length && i <= 13; i++) {
                if (panelData.categoriesData[i] == null) {
                    continue;
                }

                if (panelData.categoriesData[i].expense != 0) {
                    drawCont++;
                    g.setColor(Color.BLACK);
                    g.drawString(data.categoriesData[i].category, xPadding, (yPadding - 20) + (drawCont * 75));
                    g.setColor(data.categoriesData[i].color);
                    g.fillOval(xPadding, yPadding + (drawCont * 75), 23, 23);
                    g.fillRect(xPadding + 40, yPadding + (drawCont * 75), (int) (multiplier * data.categoriesData[i].expense), 23);

                    if (drawCont > 4 && xPadding == 30) {
                        xPadding = 400;
                        yPadding = -200;
                    }

                    if (drawCont > 0) {
                        lastAngle += angle;
                    }

                    angle = (int) ((data.categoriesData[i].expense / data.expensesTotal) * 360);
                    g.fillArc(650, 200, 300, 300, lastAngle, angle);
                }
            }

            int lastIndexAngle = angle;
            g.setColor(data.categoriesData[0].color);
            //angle = (int) ((data.categoriesData[0].expense / data.expensesTotal) * 360);
            g.fillArc(650, 200, 300, 300, lastAngle + lastIndexAngle, (360 - lastAngle - lastIndexAngle));

        }

        /**
         * Draw the general interface.It composes by a circle graph and two
         * horizontal bar graphs ordered descendantly, one for the incomes and
         * the other for the expenses.
         *
         * @param g The Graphics context in which to paint.
         */
        private void drawGeneralPanel(Graphics g) {
            if (panelData.categoriesData.length == 0) {
                return;
            }

            double multiplier;
            if (panelData.maxExpense > data.maxIncome) {
                multiplier = 250 / panelData.maxExpense;
            } else {
                multiplier = 250 / panelData.maxIncome;
            }

            //Draw a rounded graph
            //Draw the incomes part of the graph
            int angleArcIncome = ((int) (panelData.percentIncome * 360)) + panelData.angleExtra;
            int angleArcExpense = (int) (panelData.percentExpense * 360);
            g.setColor(Color.green);
            g.fillArc(650, 200, 300, 300, 0, angleArcIncome);
            //Draw the expense part of the graph
            g.setColor(Color.red);
            g.fillArc(650, 200, 300, 300, angleArcIncome, angleArcExpense);

            //Income line graph
            int heightRect = 0;
            panelData.orderCategoriesByIncome();

            for (int i = 0; i < panelData.categoriesData.length && i <= 8; i++) {
                if (panelData.categoriesData[i] == null) {
                    continue;
                }
                if (panelData.categoriesData[i].income != 0) {
                    heightRect++;
                    g.setColor(Color.BLACK);
                    g.drawString(panelData.categoriesData[i].category, 60, 100 + heightRect * 60);
                    g.setColor(panelData.categoriesData[i].getColor());
                    g.fillRect(60, 110 + heightRect * 60, (int) (multiplier * panelData.categoriesData[i].income), 20);
                }
            }

            //Expense line graph
            heightRect = 0;
            panelData.orderCategoriesByExpense();

            for (int i = 0; i < panelData.categoriesData.length && i <= 8; i++) {
                if (panelData.categoriesData[i] == null) {
                    continue;
                }

                if (panelData.categoriesData[i].expense != 0) {
                    heightRect++;
                    g.setColor(Color.BLACK);
                    g.drawString(panelData.categoriesData[i].category, 400, 100 + heightRect * 60);
                    g.setColor(panelData.categoriesData[i].getColor());
                    g.fillRect(400, 110 + heightRect * 60, (int) (multiplier * panelData.categoriesData[i].expense), 20);
                }
            }
        }

        /**
         * Draw the incomes interface.It composes by a circle graph of the
         * incomes categoreies and a horizontal bar graph of incomes,ordered
         * descendantly.
         *
         * @param g The Graphics context in which to paint.
         */
        private void drawIncomePanel(Graphics g) {
            if (panelData.categoriesData.length == 0) {
                return;
            }

            g.setColor(Color.GREEN);
            g.fillRect(60, 150, 250, 50);
            g.setColor(Color.BLACK);
            g.drawString("Incomes total: " + data.incomesTotal, 64, 170);

            int xPadding = 30, yPadding = 250, drawCont = -1, angle = 0, lastAngle = 0;
            double multiplier = 250 / data.maxIncome;
            panelData.orderCategoriesByIncome();

            for (int i = 0; i < panelData.categoriesData.length && i <= 13; i++) {
                if (panelData.categoriesData[i] == null) {
                    continue;
                }

                if (panelData.categoriesData[i].income != 0) {
                    drawCont++;
                    g.setColor(Color.BLACK);
                    g.drawString(data.categoriesData[i].category, xPadding, (yPadding - 20) + (drawCont * 75));
                    g.setColor(data.categoriesData[i].color);
                    g.fillOval(xPadding, yPadding + (drawCont * 75), 23, 23);
                    g.fillRect(xPadding + 40, yPadding + (drawCont * 75), (int) (multiplier * data.categoriesData[i].income), 23);

                    if (drawCont > 4 && xPadding == 30) {
                        xPadding = 400;
                        yPadding = -200;
                    }

                    lastAngle += angle;

                    angle = (int) ((data.categoriesData[i].income / data.incomesTotal) * 360);
                    g.fillArc(650, 200, 300, 300, lastAngle, angle);
                }
            }

            int lastIndexAngle = angle;
            g.setColor(data.categoriesData[0].color);
            //angle = (int) ((data.categoriesData[0].income / data.incomesTotal) * 360);
            g.fillArc(650, 200, 300, 300, lastAngle + lastIndexAngle, (360 - lastAngle - lastIndexAngle));
        }
    }
}
