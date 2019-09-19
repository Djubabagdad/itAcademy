package firstTask.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {
    private List<Integer> list = new ArrayList<>();
    private int inputFrom;
    private int inputTill;

    void getFromKeyboard() {
        Scanner scannerFrom = new Scanner(System.in);
        Scanner scannerTill = new Scanner(System.in);

        inputFrom = scannerFrom.nextInt();
        inputTill = scannerTill.nextInt();
    }

    void sortNumbers(){
        int number = inputFrom;

        do {
            if(number == 2){
                list.add(number);
            }
            if(!(number %2 == 0)){
                list.add(number);
            }
            number++;
        } while (inputTill >= number);
    }

    List<Integer> getList() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "simpleOrPrimary.UserSearchPrimary{" +
                "list=" + list +
                '}';
    }
}
