package dbAPI;

import java.util.Objects;

/**Represents a primary key constraint*/
public class PrimaryKeyConstraint extends Constraint {
	/**The columns that are primary keys*/
	protected IColumn[] keyColumns;

	/**Initialize a new primary key constraint with key columns
	 * @param keyColumns The columns of the key
	 */
	public PrimaryKeyConstraint(IColumn... keyColumns) {
		super(ConstraintsEnum.PRIMARY_KEY);
		this.keyColumns = keyColumns;
	}
	
	/**Get the columns that are primary keys
	 * @return The columns that are primary keys
	 */
	public IColumn[] getKeyColumns() {
		return keyColumns;
	}
	
	/**Get a column at a specified index
	 * @param index The index of the column
	 * @return The column at the given index
	 */
	public IColumn getColumn(int index) {
		return keyColumns[index];
	}
	
	/**Get a column with a specified name
	 * @param name The name of the column
	 * @return The column with the given name
	 * @throws IllegalArgumentException If there is no such column in the key columns
	 */
	public IColumn getColumn(String name) throws IllegalArgumentException {
		for (IColumn col : keyColumns) {
			if (col.getName() == name) { return col; }
		}
		throw new IllegalArgumentException("No column named '" + name + "' in the constraint");
	}
	
	/**Check whether the given column is present
	 * @param column The column
	 * @return True if the column is present, false otherwise
	 */
	public boolean hasColumn(IColumn column) {
		for (IColumn col : keyColumns) {
			if (col.equals(column)) { return true; }
		}
		return false;
	}
	
	/**Check whether the given column is present
	 * @param column The name of the column
	 * @return True if the column is present, false otherwise
	 */
	public boolean hasColumn(String column) {
		for (IColumn col : keyColumns) {
			if (col.getName() == column) { return true; }
		}
		return false;
	}
	
	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"CONSTRAINT <i>[name]</i> PRIMARY KEY (<i>columns</i>)"</code>
	 */
	public String getSqlString() {
		StringBuilder str = new StringBuilder();
		str.append("CONSTRAINT ");
		if (name != null) {
			str.append(name).append(" ");
		}
		str.append("PRIMARY KEY (");
		
		for(int i = 0; i < keyColumns.length; i++) {
			str.append(keyColumns[i].getName());
			if (i < keyColumns.length - 1) {
				str.append(", ");
			}
		}
		
		str.append(")");
		
		return str.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		PrimaryKeyConstraint pk = (PrimaryKeyConstraint)obj;
		if (this.keyColumns.length != pk.keyColumns.length) { return false; }
		for (int i = 0; i < keyColumns.length; i++) {
			if (!keyColumns[i].equals(pk.keyColumns[i])) { return false; }
		}
		if (this.name != pk.name) { return false; }
		return true;
	}
	
	@Override
	public int hashCode() {
		Object[] arr = new Object[keyColumns.length + 1];
		arr[0] = name;
		for (int i = 0; i < keyColumns.length; i++) {
			arr[i + 1] = keyColumns[i];
		}
		return Objects.hash(arr);
	}
}
