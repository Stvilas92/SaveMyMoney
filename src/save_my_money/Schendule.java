package save_my_money;

import java.util.ArrayList;

/**
 *
 * @author alejavilas92
 */
public class Schendule {

    public Schendule() {
    }

    /**
     * Transfor the array list of IncomeOrExpense in a IncomeOrExpenseDates array. Each index of new array, corresponds to one date.
     * @param balanceArray ArrayList to transform
     * @return IncomeOrExpenseDates array.
     */
    public IncomeOrExpenseDates[] balanceAlongTime(ArrayList<IncomeOrExpense> balanceArray) {
        ArrayList<Integer> indexToCompare = new ArrayList<>();
        indexToCompare = makeDatesArray(balanceArray);
        double balance = 0;
        int day, month, year;
        IncomeOrExpenseDates balanceAlongTime[] = new IncomeOrExpenseDates[indexToCompare.size()];

        for (int i = 0; i < indexToCompare.size(); i++) {
            for (int j = 0; j < balanceArray.size(); j++) {
                if (balanceArray.get(j).dateToInt == indexToCompare.get(i)) {
                    if (balanceArray.get(j).concept == 0) {
                        balance += balanceArray.get(j).quantity;
                    } else {
                        balance -= balanceArray.get(j).quantity;
                    }
                }
            }

            day = balanceArray.get(i).day;
            month = balanceArray.get(i).month;
            year = balanceArray.get(i).year;
            balanceAlongTime[i] = new IncomeOrExpenseDates((int) balance, day, month, year);
        }

        return balanceAlongTime;
    }

    /**
     * Check if two IncomeOrExpense have the same dates.
     *
     * @param inc1 IncomeOrExpense to compare.
     * @param inc2 IncomeOrExpense to compare.
     * @return If the dates are the sames, return true, else return false.
     */
    public boolean compareData(IncomeOrExpense inc1, IncomeOrExpense inc2) {
        return inc1.dateToInt == inc2.dateToInt;
    }

    /**
     * Make an Interger array whitch each Integer corresponds to sum of the total days count since year 0 of a date. 
     * @param balanceArray IncomeOrExpense ArrayList to extract the dates to form a new integer array.
     * @return Integer ArrayList,geted from the dates of balanceArray.
     */
    public ArrayList<Integer> makeDatesArray(ArrayList<IncomeOrExpense> balanceArray) {
        ArrayList<Integer> indexToCompare = new ArrayList<>();

        for (int i = 0; i < balanceArray.size(); i++) {
            if (indexToCompare.isEmpty()) {
                indexToCompare.add(balanceArray.get(i).dateToInt);
                continue;
            }

            int index = 0;
            boolean add = false;

            for (int j = 0; j < indexToCompare.size(); j++) {
                if (balanceArray.get(i).dateToInt == indexToCompare.get(j)) {
                    index++;
                }
                if (j == indexToCompare.size() - 1 && index == 0) {
                    add = true;
                }
            }

            if (add) {
                indexToCompare.add(balanceArray.get(i).dateToInt);
            }
        }

        int index = 0;
        int indexToCompareFinal[] = new int[indexToCompare.size()];

        for (int i = 0; i < indexToCompare.size(); i++) {
            for (int j = 0; j < indexToCompare.size(); j++) {
                if (indexToCompare.get(i) > indexToCompare.get(j)) {
                    index++;
                }
            }

            indexToCompareFinal[index] = indexToCompare.get(i);

            index = 0;
        }

        indexToCompare = new ArrayList<>();

        for (int i = 0; i < indexToCompareFinal.length; i++) {
            indexToCompare.add(i, indexToCompareFinal[i]);
        }

        return indexToCompare;
    }
}
