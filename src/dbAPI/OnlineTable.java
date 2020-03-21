package dbAPI;

import java.util.Iterator;
import java.util.Objects;

/**Represents a table that requires a connection*/
public class OnlineTable extends AbstractTable {
	/**The helper for the database*/
	protected IDatabaseHelper helper;

	/**Initialize this table with a name, primary key/s, indices, and columns
     * @param name The name of the table
     * @param pk The primary key columns of this table
     * @param indices The indices of this table
	 * @param columns An array of columns to put in this table, excluding the primary key/s
	 */
	protected OnlineTable(String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, IColumn[] columns) {
		super(name, pk, indices, columns);
	}

	/**Initialize this table with a helper, a converting method, a name,<br>
	 * primary key columns, indices, and the rest of the columns
	 * @param helper The helper for the database
     * @param name The name of the table
     * @param pk The primary key columns of this table
     * @param indices The indices of this table
	 * @param columns An array of columns to put in this table, excluding the primary key/s
	 */
	public OnlineTable(IDatabaseHelper helper, String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, IColumn... columns) {
		this(name, pk, indices, columns);
		this.helper = helper;
	}
	
	// ---- AbstractTable implementation ----

	@Override
	public IRow getRow(int index) {
		return selectAll().getRow(index);
	}

	@Override
	public IRow getRow(IPrimaryKey key) {
		StringBuilder where = new StringBuilder();
		
		for (int i = 0; i < key.getKeysCount(); i++) {
			where.append(key.getColumn(i)).append(" = ").append(helper.DatabaseValueToString(key.getValue(i)));
			if (i < key.getKeysCount() - 1) {
				where.append(", \n");
			}
		}
		
		return selectAll(where.toString()).next();
	}

	@Override
	public void insert(IRow newRow) {
		String[] columns = new String[newRow.getCells().length];
		String[] values = new String[newRow.getCells().length];
		
		for (int i = 0; i < newRow.getCellsCount(); i++) {
			columns[i] = newRow.getColumn(i).getName();
			values[i] = helper.DatabaseValueToString(newRow.getValue(i));
		}
		
		helper.insert(name, columns, values);
	}

	@Override
	public void insert(IRow[] rows) {
		for (IRow row : rows) {
			insert(row);
		}
	}

	@Override
	public IDatabaseReader select(IColumn[] columns) {
		String[] columnstring = new String[columns.length];
		for(IColumn c : columns) {
			columnstring[c.getIndex()] = c.getName();
		}
		return helper.select(name, columnstring);
	}

	@Override
	public IDatabaseReader select(IColumn[] columns, String whereCondition) {
		String[] columnstring = new String[columns.length];
		for(IColumn c : columns) {
			columnstring[c.getIndex()] = c.getName();
		}
		return helper.select(name, columnstring, whereCondition);
	}

	@Override
	public IDatabaseReader selectAll() {
		return helper.selectAll(name);
	}

	@Override
	public IDatabaseReader selectAll(String whereCondition) {
		return helper.selectAll(name, whereCondition);
	}

	@Override
	public void update(IRow columnValueList) {
		helper.update(name, columnValueList);
	}

	@Override
	public void update(IRow columnValueList, String whereCondition) {
		helper.update(name, columnValueList, whereCondition);
	}

	@Override
	public void delete(String whereCondition) {
		helper.delete(name, whereCondition);
	}
	
	// ---- Object overrides ----

	@Override
	public OnlineTable clone() {
		OnlineTable cloned = new OnlineTable(name, pk, indices, columns);
		
		cloned.helper = helper;
		
		return cloned;
	}
	
	@Override
	public boolean equals(ITable table) {
		if (table == this) { return true; }
		if (table == null) { return false; }
		if (table.getName() != this.name || table.getColumnCount() != this.getColumnCount()
				|| !table.getPrimaryKey().equals(pk)) { return false; }
		
		for(int i = 0; i < columns.length; i++) {
			IColumn thisColumn = this.columns[i];
			IColumn tableColumn = table.getColumn(i);
			if (!thisColumn.equals(tableColumn)) { return false; }
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) { return true; }
		if (!(obj instanceof ITable)) { return false; }
		ITable table = (ITable)obj;
		return equals(table);
	}
	
	@Override
	public int hashCode() {
		Object[] arr = new Object[pk.keyColumns.length + columns.length + 1];//The +1 is for name
		int offset = 1;
		
		arr[0] = name;
		for (int i = 0; i < pk.keyColumns.length; i++) {
			arr[i + offset++] = pk.keyColumns[i];
		}
		for (int i = 0; i < columns.length; i++) {
			arr[i + offset++] = columns[i];
		}
		
		return Objects.hash(arr);
	}
	
	// ---- Iterable implementation ----

	@Override
	public Iterator<IRow> iterator() {
		return selectAll();
	}
}
