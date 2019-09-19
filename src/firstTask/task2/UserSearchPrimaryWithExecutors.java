package firstTask.task2;

import java.util.ArrayList;
import java.util.List;


public class UserSearchPrimaryWithExecutors implements Runnable {
    private  List<Integer> input = new ArrayList<>();
    private List<Integer> result = new ArrayList<>();


    public UserSearchPrimaryWithExecutors(List<Integer> input) {
        this.input = input;
    }

    @Override
    public void run() {
        for (Integer n : input) {
            if (checkSimple(n)) {
                result.add(n);

            }
        }
        System.out.println(Thread.currentThread().getName()+result);
    }

    private static boolean checkSimple(int i) {
        if (i <= 1)
            return false;
        else if (i <= 3)
            return true;
        else if (i % 2 == 0 || i % 3 == 0)
            return false;
        int n = 5;
        while (n * n <= i) {
            if (i % n == 0 || i % (n + 2) == 0)
                return false;
            n = n + 6;
        }
        return true;
    }

    public List<Integer> getInput() {
        return input;
    }

    public void setInput(List<Integer> input) {
        this.input = input;
    }

    @Override
    public String toString() {
        System.out.println("List is:" + result);
        return new String();
    }
}
