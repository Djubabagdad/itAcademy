package firstTask.workWithFile;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadParameters implements Callable<List<Byte>> {
    private List<Byte> list;
    static AtomicInteger atom;

    ThreadParameters(List<Byte> list) {
        this.list = list;
        atom = null;
    }

    @Override
    public List<Byte> call() {
        int n = list.size();
        Set<List<Byte>> seen = new HashSet<>();
        List<Byte> max = Collections.emptyList();
        for (int i = 0; i < n; i++) {
            for (int j = i + max.size() + 1; j <= n && j <= i + n / 2; j++) {
                if (j == n || !list.get(j).equals(list.get(j - 1))) {
                    List<Byte> sub = list.subList(i, j);
                    atom = new AtomicInteger(sub.size());
                    if (seen.contains(sub)) {
                        if (sub.size() > max.size()) {
                            max = sub;
                        }
                    } else {
                        seen.add(sub);
                    }
                }
            }
        }
        return max;
    }
}
