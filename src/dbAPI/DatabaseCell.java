package dbAPI;

import java.util.Objects;

/**Represents a cell in a row*/
public class DatabaseCell implements Cloneable{
	/**The column of the cell*/
    protected IColumn column;
    /**The value of the cell*/
    public DatabaseValue Value;

    /**Initialize a new cell with a column
     * @param col The column of this cell
     */
    public DatabaseCell(IColumn col) {
        this.column = col;
    }

    /**Initialize a new cell with a column, and a value
     * @param column The column of this cell
     * @param value The value of this cell
     */
    public DatabaseCell(IColumn column, DatabaseValue value){
        this.column = column;
        Value = value;
    }
    
    /**Initialize a new cell with a column, a value, and a data type
     * @param column The column of this cell
     * @param value The value of this cell
     * @param type The database type of the value
     */
    public DatabaseCell(IColumn column, Object value, DatabaseDataType type){
        this(column, new DatabaseValue(value, type));
    }

    /**Get this cell's column
     * @return The column of this cell
     */
    public IColumn getColumn(){
        return column;
    }
    
    @Override
    protected DatabaseCell clone() {
    	return new DatabaseCell(column, (DatabaseValue)Value.clone());
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(column, Value);
    }
    
    @Override
    public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null || getClass() != obj.getClass()) {return false;}
		DatabaseCell cell = (DatabaseCell)obj;
		return this.column.equals(cell.column) && this.Value.equals(cell.Value);
    }
}