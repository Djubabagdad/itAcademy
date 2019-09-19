package firstTask.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class SearchDemo {
    private static Search search = new Search();
    private static int threadNum;
    private static List<List<Integer>> partOfList = new ArrayList<>(new ArrayList<>());
    private static List<Integer> listForPrint = new ArrayList<>();
    private static UserSearchPrimaryWithMany[] usersArray;


    public static void main(String[] args) throws InterruptedException {
        search.getFromKeyboard();
        search.sortNumbers();
        searchWithOne();
        searchWithMany();
    }

    private static void searchWithOne() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        result();
        splitNumbers();
        long time = System.currentTimeMillis() - startTime;
        System.out.println(time);
    }

    private static void splitNumbers() {
        int countOfNumbers;
        for (int i = 0; i < threadNum; i++) {
            countOfNumbers = search.getList().size() / threadNum;
            partOfList.add(search.getList().subList(i * countOfNumbers, countOfNumbers * (i + 1)));
        }
        if (search.getList().size() % threadNum != 0) {
            countOfNumbers = search.getList().size() % threadNum;
            List<Integer> integers = partOfList.get(0);
            integers.addAll(search.getList().subList(search.getList().size() - countOfNumbers, search.getList().size()));
        }
    }

    private static void result() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (List<Integer> list : partOfList) {
            UserSearchPrimary userSearchPrimary = new UserSearchPrimary(list, countDownLatch);
            new Thread(userSearchPrimary).start();
        }
        countDownLatch.await();
    }

    private static void searchWithMany() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        splitNumbersForMany();
        shareToThreads();
        resultWithMany();
        long time = System.currentTimeMillis() - startTime;
        System.out.println(time);
    }

    private static void splitNumbersForMany() {
        Scanner sc = new Scanner(System.in);
        threadNum = sc.nextInt();
        int countOfParts;
        for (int i = 0; i < threadNum; i++) {
            countOfParts = search.getList().size() / threadNum;
            partOfList.add(search.getList().subList(i * countOfParts, countOfParts * (i + 1)));
        }
        if (search.getList().size() % threadNum != 0) {
            countOfParts = search.getList().size() % threadNum;
            List<Integer> integers = partOfList.get(0);
            integers.addAll(search.getList().subList(search.getList().size() - countOfParts, search.getList().size()));
        }
    }

    private static void shareToThreads() throws InterruptedException {
        usersArray = new UserSearchPrimaryWithMany[threadNum];
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        int i = 0;
        for (List<Integer> list : partOfList) {
            UserSearchPrimaryWithMany userSearchPrimaryWithMany = new UserSearchPrimaryWithMany(list, countDownLatch);
            new Thread(userSearchPrimaryWithMany).start();
            usersArray[i] = userSearchPrimaryWithMany;
            i++;
        }
        countDownLatch.await();
    }

    private static void resultWithMany() {
        for (UserSearchPrimaryWithMany userSearchPrimaryWithMany : usersArray) {
            listForPrint.addAll(userSearchPrimaryWithMany.result);
        }
        System.out.println(listForPrint);
    }
}
