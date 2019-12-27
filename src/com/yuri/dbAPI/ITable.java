package dbAPI;

//Represents a table in a Database
class ITable{
	//Returns the name of the table
	public String getName();
	//Returns column at 'index'
	public IColumn getColumn(int index);
	//Returns column named 'name
	public IColumn getColumn(String name);
	//Returns the number of columns
	public int getColumnCount();
	//Returns row at 'index'
	public IRow getRow(int index);
	//Returns row with specified key
	public IRow getRow(IColumn keyCol, DatabaseValue keyValue);
	
	//Runs the insert sql command
	public void insert(IRow newRow);
	//Runs the select sql command
	public IDatabaseReader select(IColumn[] columns, String WhereCondition);
	//Runs the update sql command
	public void update(IRow columnValueList, String whereCondition);
	//Runs the delete sql command
	public void delete(String whereCondition);
}
