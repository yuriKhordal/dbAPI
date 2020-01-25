package dbAPI;

//Represents a generic database interface for specific database APIs
public interface IDatabaseHelper{
	//Database types
	public static final int MY_SQL = 0;
	public static final int SQLITE = 1;
	//etc
	
	//Returns the type of the database
	public int getType();

	//Open the database
	public void open();
	//Checks if the database is open
	public boolean isOpen();
	//Creates a table
	public void create(String table);
	//Insert values into respective calumns
	public void insert(String table, final String[] columns, final String[] values);
	//Updates the table
	public void update(final ITable table, final IRow row);
	public void update(final ITable table, final IRow row, String whereCondition);
	public void update(final ITable table, final IRow[] rows);
	public void update(final ITable table, final IRow[] rows, String whereCondition);
	//Selects values from a single or multiple tables
	public IDatabaseReader select(final ITable table, final IColumn[] columns);
	public IDatabaseReader select(final ITable table, final IColumn[] columns, String whereCondition);
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns);
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns, String whereCondition);
	public IDatabaseReader selectAll(final ITable table, final IColumn[] columns);
	public IDatabaseReader selectAll(final ITable table, final IColumn[] columns, String whereCondition);
	public IDatabaseReader selectAll(final ITable[] tables, final IColumn[] columns);
	public IDatabaseReader selectAll(final ITable[] tables, final IColumn[] columns, String whereCondition);
	//Deletes from table
	public void delete(final ITable table, String whereCondition);
	//Drops the table
	public void drop(final ITable table);
	//Close the database
	public void close(); 
}
