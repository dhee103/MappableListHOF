package ic.doc;

import java.util.*;

public class MappableList<T> implements Iterable<T> {
    private final List<T> list = new ArrayList<>();

    public MappableList<T> map(Function<T> f) {
        MappableList<T> result = new MappableList<>();
        for (Iterator<T> i = list.iterator(); i.hasNext(); ) {
            result.insert(f.applyTo(i.next()));
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

