package dbAPI;

public interface IRow extends Iterable<DatabaseValue>{
	//Returns the columns of the row
	public IColumn[] getColumns();
	//Returns the value at 'index'
	public DatabaseValue getValue(int index);
	//Sets a value at 'index'
	public void setValue(int index, DatabaseValue value);
	//Returns all the values, sorted by index
	public DatabaseValue[] getValues();
}
