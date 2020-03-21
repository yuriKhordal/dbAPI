package dbAPI;

/**An interface that represents a table in a database*/
public interface ITable extends Iterable<IRow>, Cloneable{
	/**Get this table's name in the database
	 * @return The name of the table as in the database
	 */
	public String getName();
	
	/**Get column at a specified index
	 * @param index The index of the column
	 * @return A column at the given index
	 */
	public IColumn getColumn(int index);
	
	/**Get a column with a specified name
	 * @param name The name of the column as in the database
	 * @return The column with the given name
	 */
	public IColumn getColumn(String name);
	
	/**Get all the columns in this table
	 * @return An array of all the columns
	 */
	public IColumn[] getColumns();
	
	/**Get number of columns in this table
	 * @return The number of columns
	 */
	public int getColumnCount();
	
	/**Get the primary key of this table
	 * @return The primary key of this table
	 */
	public PrimaryKeyConstraint getPrimaryKey();
	
	/**Get the indices of this table
	 * @return The indices of this table
	 */
	public IndexConstraint[] getIndices();
	
	/**Get a row at a specified index
	 * @param index The index of the row
	 * @return The row at the given index
	 */
	public IRow getRow(int index);
	
	/**Search for a row by a specified key
	 * @param key - The values of the key
	 * @return A row with the given key
	 */
	public IRow getRow(final IPrimaryKey key);
	
	// ---- database operations ----
	
	/**Insert a specified row in this table
	 * @param newRow The row to insert
	 */
	public void insert(final IRow newRow);
	
	/**Insert specified rows in this table
	 * @param rows The rows to insert
	 */
	public void insert(final IRow[] rows);
	
	/**Select all the rows from this table and read the specified columns
	 * @param columns The columns to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final IColumn[] columns);
	
	/**Select all the rows from this table and read the specified columns
	 * @param columns The columns to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final IColumn[] columns, String whereCondition);
	
	/**Select everything from this table
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll();
	
	/**Select everything from this table where a condition is met
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(String whereCondition);
	
	/**Update this table with values from a specified row
	 * @param columnValueList The row to take values from
	 */
	public void update(final IRow columnValueList);
	
	/**Update this table with values from a specified row where a condition is met
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @param columnValueList The row to take values from
	 */
	public void update(final IRow columnValueList, String whereCondition);
	
	/**Delete from this table where a condition is met
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 */
	public void delete(String whereCondition);
	
	public ITable clone();
	
	/**Equals for {@link ITable}
	 * @see Object#equals(Object)
	 */
	public boolean equals(ITable table);
}
