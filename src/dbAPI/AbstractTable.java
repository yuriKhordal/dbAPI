package dbAPI;

//an abstract implementation of ITable
public abstract class AbstractTable implements ITable, Iterable<IRow> {
    protected IColumn[] columns;

    //Initialize name and columns
    public AbstractTable(IColumn[] columns) {
        this.columns = columns.clone();
    }

    // ---- ITable implementation ----

	//Returns column at 'index'
	public IColumn getColumn(int index){
        return columns[index];
    }

	//Returns column named 'name
    public IColumn getColumn(String name)
    throws IllegalArgumentException{
        for(IColumn column : columns){
            if (column.getName() == name){
                return column;
            }
        }
        throw new IllegalArgumentException(
            "No column called '" + name + "' in table '" + this.getName() + "'");
    }

	//Returns all the columns
	public IColumn[] getColumns(){
        return columns.clone();
    }

	//Returns the number of columns
	public int getColumnCount(){
        return columns.length;
    }
}