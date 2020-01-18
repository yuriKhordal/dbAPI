package dbAPI;

public interface IDatabase extends Iterable<ITable>{
	//Returns all the tables
	public ITable[] getTables();

	//Open the database
	public void open();
	//Checks if the database is open
	public boolean isOpen();
	//Creates a table
	public void create(ITable table);
	//Inserts a row/s
	public void insert(ITable table, IRow row);
	public void insert(ITable table, IRow[] rows);
	//Updates the table
	public void update(ITable table, IRow row);
	public void update(ITable table, IRow row, String whereCondition);
	public void update(ITable table, IRow[] rows);
	public void update(ITable table, IRow[] rows, String whereCondition);
	//Selects values from a single or multiple tables
	public IDatabaseReader select(ITable table, IColumn[] columns);
	public IDatabaseReader select(ITable table, IColumn[] columns, String whereCondition);
	public IDatabaseReader select(ITable[] tables, IColumn[] columns);
	public IDatabaseReader select(ITable[] tables, IColumn[] columns, String whereCondition);
	public IDatabaseReader selectAll(ITable table, IColumn[] columns);
	public IDatabaseReader selectAll(ITable table, IColumn[] columns, String whereCondition);
	public IDatabaseReader selectAll(ITable[] tables, IColumn[] columns);
	public IDatabaseReader selectAll(ITable[] tables, IColumn[] columns, String whereCondition);
	//Deletes from table
	public void delete(ITable table, String whereCondition);
	//Drops the table
	public void drop(ITable table);
	//Close the database
	public void close();
}
