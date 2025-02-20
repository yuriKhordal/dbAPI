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
    /**The table level check constraints*/
    protected CheckConstraint[] checks;

    /**Initialize this table with a name, primary key/s, indices, and columns
     * @param name The name of the table
     * @param pk The primary key/s of this table
     * @param indices The indices of this table
     * @param checkConstraints The table level check constraints
     * @param columns An array of columns to put in this table, excluding the primary key/s
     */
    public AbstractTable(String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, CheckConstraint[] checkConstraints, IColumn... columns) {
    	this.name = name;
    	this.pk = pk;
    	this.indices = indices;
    	this.checks = checkConstraints;
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
    	this(name, pk, null, null, columns);
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
            if (column.getName().equals(name)){
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

	@Override
	public CheckConstraint[] getTableChecks() {
		return checks;
	}
	
    public abstract AbstractTable clone();
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Table: ").append(name).append('\n');
        str.append(pk);
        for(int i = 0; i < columns.length; i++){
            str.append('\n').append(columns[i]);
            if (i < columns.length - 1){
                str.append(',');
            }
        }

        if (indices != null){
            str.append(',');
            for (int i = 0; i < indices.length; i++){
                str.append('\n').append(indices[i]);
                if (i < indices.length - 1){
                    str.append(',');
                }
            }
        }

        if (checks != null){
            str.append(',');
            for (int i = 0; i < checks.length; i++){
                str.append('\n').append(checks[i]);
                if (i < checks.length - 1){
                    str.append(',');
                }
            }
        }

        return str.append(';').toString();
    }
}