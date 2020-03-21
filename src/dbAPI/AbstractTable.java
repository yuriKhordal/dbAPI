package dbAPI;

/**An abstract implementation of ITable*/
public abstract class AbstractTable implements ITable, Iterable<IRow> {
	/**The name of the table*/
	protected String name;
	/**An array of the columns in this table*/
    protected IColumn[] columns;
    /**The primary key/s in the table*/
    protected PrimaryKeyConstraint pk;
    /**The indices in the table*/
    protected IndexConstraint[] indices;

    /**Initialize this table with a name, primary key/s, indices, and columns
     * @param name The name of the table
     * @param pk The primary key/s of this table
     * @param indices The indices of this table
     * @param columns An array of columns to put in this table, excluding the primary key/s
     */
    public AbstractTable(String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, IColumn... columns) {
    	this.name = name;
    	this.pk = pk;
    	this.indices = indices;
    	this.columns = new IColumn[columns.length + pk.keyColumns.length];
    	for(IColumn col : pk.keyColumns) {
    		this.columns[col.getIndex()] = col;
    	}
    	for(IColumn col : columns) {
    		this.columns[col.getIndex()] = col;
    	}
    }
    
    /**Initialize this table with a name, primary key/s, and columns
     * @param name The name of the table
     * @param pk The primary key/s of this table
     * @param columns An array of columns to put in this table, excluding the primary key/s
     */
    public AbstractTable(String name, PrimaryKeyConstraint pk, IColumn... columns) {
    	this(name, pk, null, columns);
    }

    // ---- ITable implementation ----
    
    public String getName() {
    	return name;
    }
    
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
	
	public PrimaryKeyConstraint getPrimaryKey() {
		return pk;
	}
	
	public IndexConstraint[] getIndices() {
		return indices;
	}
	
	public abstract AbstractTable clone();
}