package dbAPI;

/**An interface that represents a row in a database*/
public interface IRow extends Iterable<DatabaseCell>{
	/**Get all the columns of the row
	 * @return An array of all the columns in the row
	 */
	public IColumn[] getColumns();
	
	/**Get a column at a specified index in the database
	 * @param index The index of the column in the database
	 * @return The column at the given index
	 */
	public IColumn getColumn(int index);
	
	/**Get a column with a specified name
	 * @param name The name of the column in the database
	 * @return The column with the given name
	 */
	public IColumn getColumn(String name);
	
	/**Get an index of a given column
	 * @param column The column
	 * @return The index of the column in the database
	 */
	public int getIndex(final IColumn column);
	
	/**Get an index of a column with a specified name
	 * @param column The name of the column in the database
	 * @return The index of the column in the database 
	 */
	public int getIndex(String column);
	
	/**Get a cell at a specified column index
	 * @param index The index of the column in the database
	 * @return The cell at the given index
	 */
	public DatabaseCell getCell(int index);
	
	/**Get a cell at a specified column
	 * @param column The column
	 * @return The cell at the given column
	 */
	public DatabaseCell getCell(IColumn column);
	
	/**Get a cell at a column with a specified name
	 * @param column The name of the column
	 * @return The cell at the column with the given name
	 */
	public DatabaseCell getCell(String column);
	
	/**Get all the cells
	 * @return An array of all the cells
	 */
	public DatabaseCell[] getCells();
	
	/**Set a value at a specified column index
	 * @param index The index in the database
	 * @param value The value to set the cell to
	 */
	public void setValue(int index, final DatabaseValue value);
	
	/**Set a value at a specified column
	 * @param column The column at which to set the value at
	 * @param value The value to set the cell to
	 */
	public void setValue(IColumn column, final DatabaseValue value);
	
	/**Set a value at a column with a specified name
	 * @param column The name of the column in the database
	 * @param value The value to set the cell to
	 */
	public void setValue(String column, final DatabaseValue value);
	
	/**Get all the values, sorted by index
	 * @return An array of all the values
	 */
	public DatabaseValue[] getValues();
}
