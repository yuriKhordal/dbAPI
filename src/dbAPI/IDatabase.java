package dbAPI;

/**An interface that represents a database*/
public interface IDatabase extends Iterable<ITable>{
	/**Return all the tables
     * @return an array of all the tables
     */
	public ITable[] getTables();
	
	/**Get a table of a specified
	 * @param name The name of the table in the database
	 * @return The table with the given name
	 */
	public ITable getTable(String name);

	/**Open the database*/
	public void open();
	
	/**Check if the database is open
	 * @return True if the database is open, otherwise: False
	 */
	public boolean isOpen();
	
	/**Create the specified table in the database
	 * @param table The table to create
	 */
	public void create(final ITable table);
	
	/**Insert a specified row in a specified table
	 * @param table The table in which to insert the row
	 * @param row The row to insert
	 */
	public void insert(final ITable table, final IRow row);
	
	/**Insert specified rows in a specified table
	 * @param table The table in which to insert the row
	 * @param rows The rows to insert
	 */
	public void insert(final ITable table, final IRow[] rows);
	
	/**Update a specified table with values from a specified row
	 * @param table The table which is updated
	 * @param row The row to take values from
	 */
	public void update(final ITable table, final IRow row);
	
	/**Update a specified table with values from a specified row with a condition
	 * @param table The table which is updated
	 * @param row The row to take values from
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 */
	public void update(final ITable table, final IRow row, String whereCondition);
	
	/**Select all the rows from a specified table and read the specified columns
	 * @param table The table from which to select
	 * @param columns The columns which to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable table, final IColumn[] columns);
	
	/**Select rows in a specified table and read the specified columns where the condition is met 
	 * @param table The table from which to select
	 * @param columns The columns which to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable table, final IColumn[] columns, String whereCondition);
	
	/**Select all the rows from specified tables and read the specified columns
	 * @param tables The tables from which to select
	 * @param columns The columns which to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns);
	
	/**Select rows in specified tables and read the specified columns where the condition is met 
	 * @param tables The tables from which to select
	 * @param columns The columns which to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns, String whereCondition);
	
	/**Select everything from the specified table
	 * @param table The table from which to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable table);
	
	/**Select everything from the specified table where the condition is met
	 * @param table The table from which to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable table, String whereCondition);
	
	/**Select everything from the specified tables
	 * @param tables The tables from which to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable[] tables);
	
	/**Select everything from the specified tables where the condition is met
	 * @param tables The tables from which to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable[] tables, String whereCondition);
	
	/**Delete from the specified table where the condition is met
	 * @param table The table from which to delete
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 */
	public void delete(final ITable table, String whereCondition);
	
	/**Drop the specified table
	 * @param table The table to drop
	 */
	public void drop(final ITable table);
	
	/**Close the database*/
	public void close();
}
