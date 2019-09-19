package firstTask.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchWithExecutors {
    private List<Integer> list = new ArrayList<>();
    private int inputFrom;
    private int inputTill;

    public void getFromKeyboard() {
        Scanner scannerFrom = new Scanner(System.in);
        Scanner scannerTill = new Scanner(System.in);

        inputFrom = scannerFrom.nextInt();
        inputTill = scannerTill.nextInt();
    }

    public void sortNumbers(){
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

    public int getInputFrom() {
        return inputFrom;
    }

    public void setInputFrom(int inputFrom) {
        this.inputFrom = inputFrom;
    }

    public int getInputTill() {
        return inputTill;
    }

    public void setInputTill(int inputTill) {
        this.inputTill = inputTill;
    }

    public List<Integer> getList() {
        return new ArrayList<>(list);
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "simpleOrPrimary.UserSearchPrimary{" +
                "list=" + list +
                '}';
    }
}
