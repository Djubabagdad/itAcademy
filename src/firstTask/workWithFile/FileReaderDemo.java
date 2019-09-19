package firstTask.workWithFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileReaderDemo {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ThreadParameters threadParameters;
    private static List<Byte> list = new ArrayList<>();
    private static StringBuilder string = new StringBuilder();
    private static StringBuilder s = new StringBuilder();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        fileStream();
        result(executorService);
    }

    private static void result(ExecutorService executorService) throws InterruptedException, ExecutionException {
        Future<List<Byte>> bytes = executorService.submit(threadParameters);
        for (byte b : bytes.get()) {
            string.append((char) b);
            System.out.println(ThreadParameters.atom);
        }
        executorService.shutdown();
        int first = s.indexOf(string.toString());
        int second = s.indexOf(string.toString(), first + 1);
        System.out.println(bytes.get().size());
        System.out.println(first + " " + second);
    }

    private static void fileStream() {
        try {
            String fileName = reader.readLine();
            InputStream fileIn = new FileInputStream(fileName);
            System.out.println(Thread.currentThread().getName() + "ready to work");
            int size = fileIn.available();
            for (int i = 0; i < size; i++) {
                list.add((byte) fileIn.read());
                System.out.println();
            }
            for(byte a : list){
                s.append((char)a);
            }
            System.out.println(list);
            fileIn.close();
            threadParameters = new ThreadParameters(list);
        } catch (IOException ignored) {
        }
    }
}
