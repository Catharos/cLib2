package net.catharos.lib.test.util;

import net.catharos.lib.util.ArrayIterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

/**
 * Tests the functionality of ArrayIterator
 */
@RunWith(JUnit4.class)
public class ArrayIteratorTest {

    private ArrayIterator<String> iterator;

    @Before
    public void init() {
        String[] array = new String[]{"a", "b", "c", "d"};
        iterator = new ArrayIterator<String>(array);
    }

    @Test
    public void remove() {
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFail() {
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
		iterator.next();
    }

    @Test
    public void iteration() {
        for (; iterator.hasNext(); ) {
            iterator.next();
        }
    }
}
