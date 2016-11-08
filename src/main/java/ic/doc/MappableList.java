package ic.doc;

import java.util.*;
import java.util.concurrent.*;

public class MappableList<T> implements Iterable<T> {
    private final List<T> list = new ArrayList<>();

    public MappableList<T> map(final Function<T> f) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Set<Future<T>> futureSet = new LinkedHashSet<>();
        for (final T elem : list) {
            futureSet.add(executorService.submit(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return f.applyTo(elem);
                }
            }));
        }

        MappableList<T> result = fillMap(futureSet);

        executorService.shutdown();

        return result;
    }

    private MappableList<T> fillMap(Set<Future<T>> futureSet) {
        MappableList<T> result = new MappableList<>();

        for (Iterator<Future<T>> i = futureSet.iterator(); i.hasNext(); ) {
            try {
                result.insert(i.next().get());
            } catch (Exception e) {
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public void insert(T elem) {
        list.add(elem);
    }

}

