package dbAPI;

public interface IDatabase extends Iterable<ITable>{
	//Returns all the tables
	public ITable[] getTables();
	//Returns table called 'name'
	public ITable getTable(String name);

	//Open the database
	public void open();
	//Checks if the database is open
	public boolean isOpen();
	//Creates a table
	public void create(final ITable table);
	//Inserts a row/s
	public void insert(final ITable table, final IRow row);
	public void insert(final ITable table, final IRow[] rows);
	//Updates the table
	public void update(final ITable table, final IRow row);
	public void update(final ITable table, final IRow row, String whereCondition);
	//Selects values from a single or multiple tables
	public IDatabaseReader select(final ITable table, final IColumn[] columns);
	public IDatabaseReader select(final ITable table, final IColumn[] columns, String whereCondition);
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns);
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns, String whereCondition);
	public IDatabaseReader selectAll(final ITable table);
	public IDatabaseReader selectAll(final ITable table, String whereCondition);
	public IDatabaseReader selectAll(final ITable[] tables);
	public IDatabaseReader selectAll(final ITable[] tables, String whereCondition);
	//Deletes from table
	public void delete(final ITable table, String whereCondition);
	//Drops the table
	public void drop(final ITable table);
	//Close the database
	public void close();
}
