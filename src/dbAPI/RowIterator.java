package dbAPI;

import java.util.Collection;
import java.util.Iterator;

/**An iterator for rows in {@link T} form*/
public class RowIterator<T extends IRow> implements Iterator<IRow>{
	/**An iterator of type {@link T}*/
	protected Iterator<T> iterator;
	
	/**Initialize the iterator with a type {@link T} row collection
	 * @param rows The rows in {@link T} form
	 */
	protected RowIterator(Collection<T> rows) {
		this.iterator = rows.iterator();
	}
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public IRow next() {
		return iterator.next();
	}
}
