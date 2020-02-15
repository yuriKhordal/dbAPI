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
	public void update(String table, final IRow row);
	public void update(String table, final IRow row, String whereCondition);
	//Selects values from a single or multiple tables
	public IDatabaseReader select(String table, String[] columns);
	public IDatabaseReader select(String table, String[] columns, String whereCondition);
	public IDatabaseReader select(String[] tables, String[] columns);
	public IDatabaseReader select(String[] tables, String[] columns, String whereCondition);
	public IDatabaseReader selectAll(String table);
	public IDatabaseReader selectAll(String table, String whereCondition);
	public IDatabaseReader selectAll(String[] tables);
	public IDatabaseReader selectAll(String[] tables, String whereCondition);
	//Deletes from table
	public void delete(String table, String whereCondition);
	//Drops the table
	public void drop(String table);
	//Close the database
	public void close(); 
}
