package dbAPI;

/**An interface that represents a generic database for specific database APIs <br>
 * It's supposed to link between a specific database type and it's API, and this generic API <br>
 * Should be implemented for each database type
 */
public interface IDatabaseHelper extends Cloneable{
	/**A value to be returned by {@link #getType()} if the type isn't one of the constants*/
	public static final int OTHER = 0;
	/**A value to be returned by {@link #getType()} when it's a MySQL database*/
	public static final int MY_SQL = 1;
	/**A value to be returned by {@link #getType()} when it's a SQLite database*/
	public static final int SQLITE = 2;
	
	/**Get this helper's implementation database type
	 * @return A constant value that represents a database type
	 */
	public int getType();

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
	
	/**Insert specified values in specified columns inside a specified table
	 * @param table The name of the table in the database
	 * @param columns The columns in which to insert the values
	 * @param values The values which to insert
	 */
	public void insert(final ITable table, final IColumn[] columns, final DatabaseValue[] values);
	
	/**Update a specified table with values from a specified row
	 * @param table The name of the table in the database
	 * @param row The row to take values from
	 */
	public void update(final ITable table, final IRow row);
	
	/**Update a specified table with values from a specified row with a condition
	 * @param table The name of the table in the database
	 * @param row The row to take values from
	 * @param whereCondition the condition in sql format(without 'WHERE')
	 */
	public void update(final ITable table, final IRow row, String whereCondition);
	
	/**Execute an sql statement
	 * @param sql The sql statement
	 */
	public void executeSql(String sql);
	
	/**Select all the rows from a specified table and read the specified columns
	 * @param table The name of the table in the database
	 * @param columns The columns which to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable table, final IColumn[] columns);
	
	/**Select rows in a specified table and read the specified columns where the condition is met
	 * @param table The name of the table in the database
	 * @param columns The columns which to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable table, final IColumn[] columns, String whereCondition);
	
	/**Select all the rows from specified tables and read the specified columns
	 * @param tables The name of the tables in the database
	 * @param columns The columns which to select
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns);
	
	/**Select rows in specified tables and read the specified columns where the condition is met
	 * @param tables The name of the tables in the database
	 * @param columns The columns which to select
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns, String whereCondition);
	
	/**Select everything from a specified table
	 * @param table The name of the table in the database
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable table);
	
	/**Select everything from the specified table where the condition is met
	 * @param table The name of the table in the database
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable table, String whereCondition);
	
	/**Select everything from the specified tables
	 * @param tables The name of the tables in the database
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable[] tables);
	
	/**Select everything from the specified tables where the condition is met
	 * @param tables The name of the tables in the database
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 * @return A reader with the selects results
	 */
	public IDatabaseReader selectAll(final ITable[] tables, String whereCondition);
	
	/**Execute an sql statement and return results in a {@link IDatabaseReader}
	 * @param sql The sql statement to execute
	 * @return An {@link IDatabaseReader} with the results
	 */
	public IDatabaseReader readSql(String sql);
	
	/**Convert DatabaseValue to it's string form in sql statements
	 * @param value The database value to convert to sql statment string form
	 * @return A string format of the value
	 */
	public String DatabaseValueToString(DatabaseValue value);
	
	/**Delete from the specified table where the condition is met
	 * @param table The table from which to delete
	 * @param whereCondition The condition in sql format(without 'WHERE')
	 */
	public void delete(ITable table, String whereCondition);
	
	/**Drop the specified table
	 * @param table The table to drop
	 */
	public void drop(ITable table);
	
	/**Close the database*/
	public void close(); 
	
	public IDatabaseHelper clone();
}
