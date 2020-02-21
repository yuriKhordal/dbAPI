package dbAPI;

/**An abstract implementation of ITable*/
public abstract class AbstractTable implements ITable, Iterable<IRow> {
	/**An array of the columns in this table*/
    protected IColumn[] columns;

    /**Initialize this table with a list of columns
     * @param columns An array of columns to put in this table
     */
    public AbstractTable(IColumn[] columns) {
        this.columns = columns.clone();
    }

    // ---- ITable implementation ----


	public IColumn getColumn(int index){
        return columns[index];
    }

	/** {@inheritDoc}
	 * @throws IllegalArgumentException If no column with the given name is found in the table
	 */
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


	public IColumn[] getColumns(){
        return columns.clone();
    }


	public int getColumnCount(){
        return columns.length;
    }
}