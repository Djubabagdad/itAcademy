package firstTask.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class UserSearchPrimaryWithMany implements Runnable {
    private  List<Integer> input;
    final List<Integer> result = new ArrayList<>();
    private CountDownLatch countDownLatch;

    UserSearchPrimaryWithMany(List<Integer> input, CountDownLatch countDownLatch) {
        this.input = input;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (Integer n : input) {
            if (checkSimple(n)) {
                result.add(n);

            }
        }
        System.out.println(Thread.currentThread().getName()+result);
        countDownLatch.countDown();
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

    @Override
    public String toString() {
        return "List is:" + result;
    }
}
