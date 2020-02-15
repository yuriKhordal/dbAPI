package dbAPI;

import java.util.Iterator;

//An iterator for arrays
public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int i;

    //Initiallize the iterator with an array
    public ArrayIterator(T[] array){
        this.array = array.clone();
        i = 0;
    }

    //Returns whethere there are values left
    public boolean hasNext() {
        return i < array.length;
    }

    //Returns the next value
    public T next() {
        return array[i++];
    }
}