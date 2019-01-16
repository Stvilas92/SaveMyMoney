package save_my_money;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

//*****
// En esta clase, no busco excepciones concretas ya que en las interfaces ya se comprueban y se evitan diferentes tipos de errores.
// De todas formas existe un JOptionPane que informa al usuario del error que se pueda dar, si se da el caso.
//*****



/**
 * Class to order clasify and save the data of each income and expense A income
 * or expense have a concept (o == income, 1 == expense), cuantity category and
 * if user wants, a note to explain the income or expense
 *
 * @author alejavilas92
 */
public class IncomeOrExpense {

    public int concept, day, month, year, dateToInt,index;
    public double quantity = 0;
    public String category = "", note = "";
    private String extension;
    private final String id;

    /**
     * Inicializate the class, the public values and the id value.
     *
     * @param userID Use to search a .txt file to write in, the IncomeOrExpense
     * values.
     * @param type Type of concep, it can be a income if value 0, or a expense
     * if value 1.
     * @param amount Quantity of the concept.
     * @param denomination Categorie of the concept.
     * @param mark Note of the user.
     * @param dayActual Day of the income/expense.
     * @param monthActual Month of the income/expense.
     * @param yearActual Year of the income/expense.
     */
    public IncomeOrExpense(String userID, int type, double amount, String denomination, String mark, int dayActual, int monthActual, int yearActual, int conceptIndex) {
        id = userID;
        extension = (System.getProperty("user.home")) + "/SaveMyMoney" + "/" + id + "/IncOrExp.txt";
        concept = type;
        quantity = amount;
        category = denomination;
        note = mark;
        day = dayActual;
        month = monthActual;
        year = yearActual;
        index = conceptIndex;
        dateToInt = (year * 365) + (month * 31) + day;
    }
    
    /**
     * Get the category value
     *
     * @return variable category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the concept value
     *
     * @return variable concept
     */
    public int getConcept() {
        return concept;
    }

    /**
     * Get the category dateToInt
     *
     * @return variable dateToInt
     */
    public int getDateToInt() {
        return dateToInt;
    }

    /**
     * Get the day value
     *
     * @return variable day
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the index value
     * This variable has not setter.
     *
     * @return variable index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Get the month value
     *
     * @return variable month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get the note value
     *
     * @return variable note
     */
    public String getNote() {
        return note;
    }

    /**
     * Get the quantity value
     *
     * @return variable quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Get the year value
     *
     * @return variable year
     */
    public int getYear() {
        return year;
    }

    /**
     * It writes all the public variables of the object into a .txt called
     * IncOrExp.txt, into the user directo
     *
     */
    public void saveFile() {
        try {
            PrintWriter incomeOrExpenseFile = new PrintWriter(new FileWriter(extension, true));
            incomeOrExpenseFile.printf("%d&%.2f&%s&%s&%s&%d&%d&%d%s", concept, quantity, category, note, day, month, year, index, "%");
            incomeOrExpenseFile.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File write. Error : " + e.getMessage(), "Fatal error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set a value to concept
     *
     * @param conceptSetted
     */
    public void setConcept(int conceptSetted) {
        concept = conceptSetted;
    }

    /**
     * Set a value to quantity
     *
     * @param quantitySetted
     */
    public void setQuantity(double quantitySetted) {
        quantity = quantitySetted;
    }

    /**
     * Set a value to category
     *
     * @param categorySetted
     */
    public void setCategory(String categorySetted) {
        category = categorySetted;
    }

    /**
     * Set a value to note
     *
     * @param noteSetted
     */
    public void setNote(String noteSetted) {
        note = noteSetted;
    }

    /**
     * Set a value to day
     *
     * @param daySetted
     */
    public void setDay(int daySetted) {
        day = daySetted;
    }

    /**
     * Set a value to month
     *
     * @param monthSetted
     */
    public void setMonth(int monthSetted) {
        month = monthSetted;
    }

    /**
     * Set a value to year
     *
     * @param yearSetted
     */
    public void setYear(int yearSetted) {
        year = yearSetted;
    }

    /**
     * Set a value to dateToInt
     *
     * @param dateToIntSetted
     */
    public void setDateToInt(int dateToIntSetted) {
        dateToInt = dateToIntSetted;
    }
}