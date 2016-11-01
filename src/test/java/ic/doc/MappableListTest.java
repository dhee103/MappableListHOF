package ic.doc;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MappableListTest {
    @Test
    public void sizeTest() throws Exception {
        MappableList<Integer> ml = new MappableList<>();
        assertThat(ml.size(), is(0));
    }

    @Test
    public void insertTest() throws Exception {
        MappableList<Integer> ml = new MappableList<>();
        ml.insert(1);
        assertThat(ml.size(), is(1));
    }

    @Test
    public void mapTest() throws Exception {
        MappableList<Integer> ml = new MappableList<>();
        ml.insert(0);
        ml.insert(1);
        ml.insert(2);
        ml.insert(3);
        ml.insert(4);

        Iterable<Integer> mlAfterMap = ml.map(new Function<Integer>() {
            @Override
            public Integer applyTo(Integer next) {
                return next + 1;
            }
        });

        assertThat(mlAfterMap, contains(1, 2, 3, 4, 5));

    }

    @Test
    public void genericMapTest() throws Exception {
        MappableList<String> ml = new MappableList<>();
        ml.insert("a");
        ml.insert("b");
        ml.insert("c");

        Iterable<String> mlAfterMap = ml.map(new Function<String>() {
            @Override
            public String applyTo(String next) {
                return next + "1";
            }
        });

        assertThat(mlAfterMap, contains("a1", "b1", "c1"));
    }

    @Test
    public void multipleMapsTest() throws Exception {
        MappableList<Integer> ml = new MappableList<>();
        ml.insert(0);
        ml.insert(1);
        ml.insert(2);

        MappableList<Integer> mlAfterMap = ml.map(new Function<Integer>() {
            @Override
            public Integer applyTo(Integer next) {
                return next + 1;
            }
        }).map(new Function<Integer>() {
            @Override
            public Integer applyTo(Integer next) {
                return next + 1;
            }
        });

        assertThat(mlAfterMap, contains(2, 3, 4));
    }

}