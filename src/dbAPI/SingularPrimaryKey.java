package dbAPI;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Represents a primary key that consists of only one column*/
public class SingularPrimaryKey extends DatabaseCell implements IPrimaryKey {
	/**The basic value of the key*/
	protected SingularBasicPrimaryKey basic;
	
	/**Initialize a new primary key with a column and a value
	 * @param column The key column
	 * @param value The value of the key
	 */
	public SingularPrimaryKey(IColumn column, DatabaseValue value) {
		super(column, value);
		basic = new SingularBasicPrimaryKey(value.Value, value.type);
	}

	/**Initialize a new primary key with a column, value, and data type
	 * @param column The key column
	 * @param value The value of the key
	 * @param type The type of the value
	 */
	public SingularPrimaryKey(IColumn column, Object value, DatabaseDataType type) {
		super(column, value, type);
	}
	
	/**Initialize a new primary key with a cell
	 * @param cell the key cell
	 */
	public SingularPrimaryKey(DatabaseCell cell) {
		super(cell.column, cell.Value);
	}
	
	public PrimaryKey toPrimaryKey() {
		return new PrimaryKey(this);
	}

	@Override
	public DatabaseCell[] getCells() {
		return new DatabaseCell[] { this };
	}
	
	@Override
	public DatabaseCell getCell(int index) throws IndexOutOfBoundsException {
		if (index != 0) { throw new IndexOutOfBoundsException(index); }
		return this;
	}

	@Override
	public DatabaseCell getCell() {
		return this;
	}

	@Override
	public IColumn[] getColumns() {
		return new IColumn[] { column };
	}
	
	@Override
	public IColumn getColumn(int index) throws IndexOutOfBoundsException {
		if (index != 0) { throw new IndexOutOfBoundsException(index); }
		return column;
	}
	
	@Override
	public IColumn getColumn() {
		return column;
	}

	@Override
	public DatabaseValue[] getValues() {
		return new DatabaseValue[] { Value };
	}
	
	@Override
	public DatabaseValue getValue(int index) throws IndexOutOfBoundsException {
		if (index != 0) { throw new IndexOutOfBoundsException(index); }
		return Value;
	}

	@Override
	public DatabaseValue getValue() {
		return Value;
	}
	
	@Override
	public int getKeysCount() {
		return 1;
	}

	@Override
	public SingularBasicPrimaryKey getBasicKey() {
		return basic;
	}

	@Override
	public boolean isSingularKey() {
		return true;
	}
	
	@Override
	public SingularPrimaryKey clone() {
		return new SingularPrimaryKey((DatabaseCell)super.clone());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof IPrimaryKey)) { return false; }
		return equals((IPrimaryKey)obj);
	}

	@Override
	public boolean equals(IPrimaryKey key) {
		if (this == key) { return true; }
		if (key == null) { return false; }
		if (!key.isSingularKey()) { return false; }
		return column.equals(key.getColumn()) && Value.equals(key.getValue());
	}

	@Override
	public Iterator<DatabaseCell> iterator() {
		return new Iterator<DatabaseCell>() {
			boolean first = true;
			
			@Override
			public boolean hasNext() {
				return first;
			}

			@Override
			public DatabaseCell next() {
				if (!hasNext()) { throw new NoSuchElementException(); }
				first = false;
				return SingularPrimaryKey.this;
			}
		};
	}
}
