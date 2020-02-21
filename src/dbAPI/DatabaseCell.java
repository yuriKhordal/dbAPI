package dbAPI;

/**Represents a cell in a row*/
public class DatabaseCell{
	/**The column of the cell*/
    protected IColumn column;
    /**The value of the cell*/
    public DatabaseValue Value;

    /**Initialize this cell with a column
     * @param col The column of this cell
     */
    public DatabaseCell(IColumn col) {
        column = col;
    }

    /**Initialize this cell with a column, and a value
     * @param column The column of this cell
     * @param value The value of this cell
     */
    public DatabaseCell(IColumn column, DatabaseValue value){
        column = column;
        Value = value;
    }
    
    /**Initialize this cell with a column, a value, and a data type
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
}