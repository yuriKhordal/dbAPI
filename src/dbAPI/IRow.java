package dbAPI;

public interface IRow extends Iterable<DatabaseCell>{
	//Returns the columns of the row
	public IColumn[] getColumns();
	//Returns the column at 'index'
	public IColumn getColumn(int index);
	//Returns the column called 'name'
	public IColumn getColumn(String name);
	//Returns the index of 'column'
	public int getIndex(final IColumn column);
	public int getIndex(String column);
	//Returns the cell at 'index'
	public DatabaseCell getCell(int index);
	//Returns the cell at 'column'
	public DatabaseCell getCell(IColumn column);
	public DatabaseCell getCell(String column);
	//Returns all the cells
	public DatabaseCell[] getCells();
	//Sets a value at 'index'
	public void setValue(int index, final DatabaseValue value);
	//Sets a value of 'column'
	public void setValue(IColumn column, final DatabaseValue value);
	public void setValue(String column, final DatabaseValue value);
	//Returns all the values, sorted by index
	public DatabaseValue[] getValues();
}
