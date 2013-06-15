package net.catharos.lib.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A iterator for arrays of objects. This does not work with primitive types since they do not support generics.
 *
 * @see Iterator
 */
public class ArrayIterator<E> implements Iterator<E> {
    /**
     * The array to iterate over
     */
    protected E[] array;
    /**
     * The current index
     */
    protected int index = 0;

    public ArrayIterator(E... array) {
        this.array = array;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There is no further element in this array!");
        }

        return array[index++];
    }

    @Override
    public void remove() {
        if (!hasNext()) {
            throw new IllegalStateException("There is no further element!");
        }

        int move = array.length - index - 1;
        if (move > 0) {
            E[] newArray = Arrays.copyOf(array, array.length - 1);
            System.arraycopy(array, index + 1, newArray, index, move);
            this.array = newArray;
        }
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }
}
