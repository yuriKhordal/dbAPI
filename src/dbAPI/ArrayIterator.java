package dbAPI;

import java.util.Iterator;

/**An iterator for arrays*/
public final class ArrayIterator<T> implements Iterator<T> {
	/**The array for iteration*/
	private T[] array;
    /**An index of the current item in the database*/
	private int i;

    /**Initialize the iterator with an array
     * @param array The array to iterate over
     */
    public ArrayIterator(T[] array){
        this.array = array.clone();
        i = 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasNext() {
        return i < array.length;
    }
    
    /**
     * {@inheritDoc}
     */
    public T next() {
        return array[i++];
    }
}