package dbAPI;

import java.util.Iterator;

/**An interface that represents a reader that reads rows from the database*/
public interface IDatabaseReader extends Iterable<IRow>, Iterator<IRow> {
	/**Get a row with a specified index
	 * @param index The index of the row
	 * @return The row at the given index, eg 4 would return the 4th row
	 */
	public IRow getRow(int index);
	
	/**Get all the rows
	 * @return An array of all the rows
	 */
	public IRow[] getAllRows();

	/**Check if there are rows left
	 * @return True if there are rows left, otherwise False
	 */
	public boolean hasNext();
	
	/**Get the next row
	 * @return The next row
	 */
	public IRow next();
	
	/**Skips to the next row without returning it*/
	public void skip();
}
