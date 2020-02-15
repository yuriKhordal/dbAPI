package dbAPI;

//Represents a table in a Database
public interface ITable extends Iterable<IRow>{
	//Returns the name of the table
	public String getName();
	//Returns column at 'index'
	public IColumn getColumn(int index);
	//Returns column named 'name
	public IColumn getColumn(String name);
	//Returns all the columns
	public IColumn[] getColumns();
	//Returns the number of columns
	public int getColumnCount();
	//Returns row at 'index'
	public IRow getRow(int index);
	//Returns row with specified key
	public IRow getRow(final IColumn keyCol, final DatabaseValue keyValue);
	
	//Runs the insert sql command
	public void insert(final IRow newRow);
	public void insert(final IRow[] rows);
	//Runs the select sql command
	public IDatabaseReader select(final IColumn[] columns);
	public IDatabaseReader select(final IColumn[] columns, String whereCondition);
	public IDatabaseReader selectAll();
	public IDatabaseReader selectAll(String whereCondition);
	//Runs the update sql command
	public void update(final IRow columnValueList);
	public void update(final IRow columnValueList, String whereCondition);
	//Runs the delete sql command
	public void delete(String whereCondition);
}
