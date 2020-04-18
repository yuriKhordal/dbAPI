package dbAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**Represents a database table that saves the data in memory
 * @param <T> The type of rows in the table
 */
public class CacheTable<T extends IRow> extends OnlineTable {
	/**A list of all the rows in the table*/
	protected ArrayList<T> rows;
	/**A map of rows indexed by their primary key*/
	protected HashMap<IBasicPrimaryKey, T> rowsMap;
	/**A listener object for converting {@link IRow} rows to this table's rows*/
	protected IRowConverter<T> converter;
	
	/**Initialize this table with a name, primary key/s, indices, and columns
     * @param name The name of the table
     * @param pk The primary key columns of this table
     * @param indices The indices of this table
     * @param checkConstraints The table level check constraints
	 * @param columns An array of columns to put in this table, excluding the primary key/s
	 */
	protected CacheTable(String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, CheckConstraint[] checkConstraints, IColumn... columns) {
		super(name, pk, indices, checkConstraints, columns);
		rows = new ArrayList<T>();
		rowsMap = new HashMap<IBasicPrimaryKey, T>();
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
	public CacheTable(IDatabaseHelper helper, IRowConverter<T> converter, String name, PrimaryKeyConstraint pk, IndexConstraint[] indices, CheckConstraint[] checkConstraints, IColumn... columns) {
		this(name, pk, indices, checkConstraints, columns);
		this.helper = helper;
		this.converter = converter;
	}
	
	/**Load all the rows into this object*/
	public void load() {
		IDatabaseReader reader = helper.selectAll(this);
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
		StringBuilder whereCondition = new StringBuilder();
		
		for (int i = 0; i < key.getKeysCount(); i++) {
			String value = helper.DatabaseValueToString(key.getValue(i));
			whereCondition.append(key.getColumn(i)).append(" = ").append(value);
			if (i < key.getKeysCount() - 1) {
				whereCondition.append(", \n");
			}
		}
		
		update(newValue, whereCondition.toString());
	}
	
	/**Get a row from the HashMap
	 * @param row The row in {@link IRow} form
	 * @return The row in {@link T} form
	 */
	protected T getFromIRow(IRow row) {
		IBasicPrimaryKey key = row.getPrimaryKey().getBasicKey();
		return rowsMap.get(key);
	}
	
	/**Remove a row just from rows and rowsMap, not from database
	 * @param t The row to remove in {@link T} form
	 */
	protected void remove(T t) {
		rows.remove(t);
		IBasicPrimaryKey key = t.getPrimaryKey().getBasicKey();
		rowsMap.remove(key);
	}
	
	/**Create a new object of class {@link T} from {@link IRow} and add it to rows and rowsMap only
	 * @param row The row to add
	 */
	protected void addFromIRow(IRow row) {
		T t = converter.convertFromIRow(row);
		rows.add(t);
		rowsMap.put(t.getPrimaryKey().getBasicKey(), t);
	}
	
	// ---- AbstractTable implementation ----
	
	

	@Override
	public T getRow(int index) {
		return rows.get(index);
	}
	
	@Override
	public T getRow(IPrimaryKey key) {
		return rowsMap.get(key.getBasicKey());
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
	
	// --- Object Overrides
	
	@Override
	public CacheTable<T> clone() {
		CacheTable<T> cloned = new CacheTable<T>(name, pk, indices, checks, columns);
		
		cloned.converter = this.converter;
		cloned.helper = this.helper.clone();
		for (T row : this.rows) {
			T clonedRow = (T)row.clone();
			cloned.rows.add(clonedRow);
			cloned.rowsMap.put(clonedRow.getPrimaryKey().getBasicKey(), clonedRow);
		}
		
		return cloned;
	}
	
	// ---- Iterator implementation ----

	@Override
	public Iterator<IRow> iterator() {
		return new RowIterator<T>(rows);
	}
}