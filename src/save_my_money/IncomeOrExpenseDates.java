package save_my_money;

/**
 * Class to clasify incomes and expenses by dates.
 * @author alejavilas92
 */
public class IncomeOrExpenseDates {

    public int balance, day, month, year;

    public IncomeOrExpenseDates(int balance, int day, int month, int year) {
        this.balance = balance;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * 
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * 
     * @param balance 
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * 
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * 
     * @param day 
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * 
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * 
     * @param month 
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * 
     * @param year 
     */
    public void setYear(int year) {
        this.year = year;
    }
}
