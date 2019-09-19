package firstTask.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchWithExecutorsDemo {
    private static SearchWithExecutors search = new SearchWithExecutors();
    private static int threadNum;
    private static int countOfParts = 0;
    private static List<List<Integer>> partsOfList = new ArrayList<>(new ArrayList<>());
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        search.getFromKeyboard();
        search.sortNumbers();
        splitNumbers();
        result();
    }

    private static void result() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (List list : partsOfList) {
            UserSearchPrimaryWithExecutors userSearchPrimary = new UserSearchPrimaryWithExecutors(list);
            executorService.execute(userSearchPrimary);
        }
        executorService.shutdown();
    }

    private static void splitNumbers() {
        threadNum = sc.nextInt();
        for (int i = 0; i < threadNum; i++) {
            countOfParts = search.getList().size() / threadNum;
            partsOfList.add(search.getList().subList(i * countOfParts, countOfParts * (i + 1)));
        }
        if (search.getList().size() % threadNum != 0) {
            countOfParts = search.getList().size() % threadNum;
            List<Integer> integers = partsOfList.get(0);
            integers.addAll(search.getList().subList(search.getList().size() - countOfParts, search.getList().size()));
        }
    }
}
