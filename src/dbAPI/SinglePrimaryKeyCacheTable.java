package dbAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/**Represents a database table that saves the data in memory
 * @param <T> The type of rows in the table
 */
public class SinglePrimaryKeyCacheTable<T extends IRow> extends OnlineTable {
	/**A list of all the rows in the table*/
	protected ArrayList<T> rows;
	/**A map of rows indexed by their primary key*/
	protected HashMap<Object, T> rowsMap;
	/**A listener object for converting {@link IRow} rows to this table's rows*/
	protected IRowConverter<T> converter;
	
	/**Initialize this table with a name, primary key/s, indices, and columns
     * @param name The name of the table
     * @param pk The primary key columns of this table
     * @param indices The indices of this table
     * @param checkConstraints The table level check constraints
	 * @param columns An array of columns to put in this table, excluding the primary key/s
	 */
	protected SinglePrimaryKeyCacheTable(String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, CheckConstraint[] checkConstraints, IColumn... columns) {
		super(name, pk, indices, checkConstraints, columns);
		rows = new ArrayList<T>();
		rowsMap = new HashMap<Object, T>();
	}

	/**Initialize this table with a helper, a converting method, a name,<br>
	 * primary key columns, indices, and the rest of the columns
	 * @param helper The helper for the database
	 * @param converter A listener object for converting {@link IRow} rows to this table's rows
     * @param name The name of the table
     * @param pk The primary key columns of this table
     * @param indices The indices of this table
     * @param checkConstraints The table level check constraints
	 * @param columns An array of columns to put in this table, excluding the primary key/s
	 */
	public SinglePrimaryKeyCacheTable(IDatabaseHelper helper, IRowConverter<T> converter, String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, CheckConstraint[] checkConstraints, IColumn... columns) {
		this(name, pk, indices, checkConstraints, columns);
		this.helper = helper;
		this.converter = converter;
		
		IDatabaseReader reader = helper.selectAll(name);
		for (IRow row : reader) {
			addFromIRow(row);
		}
	}
	
	/**Add row/s to the table
	 * @param ts The row/s to add
	 */
	public void add(T... ts) {
		for (T t : ts) {
			insert(t);
		}
	}
	
	/**Add an iterable of rows to the table
	 * @param ts The iterable of rows
	 */
	public void add(Iterable<T> ts) {
		for (T t : ts) {
			insert(t);
		}
	}
	
	/**Update a row with a specified primary key to a new value
	 * @param key The key of the row to update
	 * @param newValue The value to update the row with
	 */
	public void update(IPrimaryKey key, T newValue) {
		String where = key.getColumn().getName() + " = " + helper.DatabaseValueToString(key.getValue());
		
		update(newValue, where);
	}
	
	/**Get a row from the HashMap
	 * @param row The row in {@link IRow} form
	 * @return The row in {@link T} form
	 */
	protected T getFromIRow(IRow row) {
		return rowsMap.get(row.getPrimaryKey().getValue().Value);
	}
	
	/**Remove a row just from rows and rowsMap, not from database
	 * @param t The row to remove in {@link T} form
	 */
	protected void remove(T t) {
		rows.remove(t);
		rowsMap.remove(t.getPrimaryKey().getValue().Value);
	}
	
	/**Create a new object of class {@link T} from {@link IRow} and add it to rows and rowsMap only
	 * @param row The row to add
	 */
	protected void addFromIRow(IRow row) {
		T t = converter.convertFromIRow(row);
		rows.add(t);
		rowsMap.put(t.getPrimaryKey().getValue().Value, t);
	}
	
	// ---- AbstractTable implementation ----

	@Override
	public T getRow(int index) {
		return rows.get(index);
	}
	
	@Override
	public T getRow(IPrimaryKey key) {
		return rowsMap.get(key.getValue().Value);
	}

	@Override
	public void insert(IRow newRow) {
		super.insert(newRow);
		addFromIRow(newRow);
	}

	@Override
	public void update(IRow columnValueList) {
		super.update(columnValueList);
		
		for(IRow row : rows) {
			for(DatabaseCell c : columnValueList) {
				row.setValue(c.getColumn(), c.Value);
			}
		}
	}

	@Override
	public void update(IRow columnValueList, String whereCondition) {
		super.update(columnValueList, whereCondition);
		
		IDatabaseReader reader = selectAll(whereCondition);
		while(reader.hasNext()) {
			IRow next = reader.next();
			T t = getFromIRow(next);
			for(DatabaseCell c : columnValueList) {
				t.setValue(c.getColumn(), c.Value);
			}
		}
	}

	@Override
	public void delete(String whereCondition) {
		super.delete(whereCondition);
		
		IDatabaseReader reader = selectAll(whereCondition);
		for (IRow row : reader) {
			remove(getFromIRow(row));
		}
	}
	
	// ---- Object implementation ----
	
	@Override
	public SinglePrimaryKeyCacheTable<T> clone() {
		SinglePrimaryKeyCacheTable<T> cloned = new SinglePrimaryKeyCacheTable<T>(name, pk, indices, columns);
		
		cloned.converter = this.converter;
		cloned.helper = this.helper.clone();
		for (T row : this.rows) {
			T clonedRow = (T)row.clone();
			cloned.rows.add(clonedRow);
			cloned.rowsMap.put(clonedRow.getPrimaryKey().getValue().Value, clonedRow);
		}
		
		return cloned;
	}
	
	@Override
	public int hashCode() {
		Object[] arr = new Object[columns.length + 2];//The +2 is for name and primary key
		int offset = 1;
		
		arr[0] = name;
		arr[1] = pk.keyColumns[0];
		for (int i = 0; i < pk.keyColumns.length; i++) {
			arr[i + offset++] = pk.keyColumns[i];
		}
		
		return Objects.hash(arr);
	}
	
	// ---- Iterator implementation ----

	@Override
	public Iterator<IRow> iterator() {
		return new RowIterator<T>(rows);
	}
}
