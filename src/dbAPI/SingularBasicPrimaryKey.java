package dbAPI;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Represents only the value of a singular key*/
public class SingularBasicPrimaryKey extends DatabaseValue implements IBasicPrimaryKey {
	/**Initialize a new basic key with a value and a data type;
	 * @param value The value of the key
	 * @param type The type of the data of the value
	 */
	public SingularBasicPrimaryKey(Object value, DatabaseDataType type) {
		super(value, type);
	}

	@Override
	public DatabaseValue[] getValues() {
		return new DatabaseValue[] { this };
	}
	
	@Override
	public DatabaseValue getValue(int index) throws IndexOutOfBoundsException {
		if (index != 0) { throw new IndexOutOfBoundsException(index); }
		return this;
	}

	@Override
	public DatabaseValue getValue() {
		return this;
	}
	
	@Override
	public int getKeysCount() {
		return 1;
	}

	@Override
	public boolean isSingularKey() {
		return true;
	}

	@Override
	public SingularBasicPrimaryKey clone() {
		return new SingularBasicPrimaryKey(Value, type);
	}

	@Override
	public boolean equals(IBasicPrimaryKey key) {
		if (key == this) { return true; }
		if (key == null) { return false; }
		if (!key.isSingularKey()) { return false; }
		return key.getValue().equals(this.Value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) { return true; }
		if (!(obj instanceof IBasicPrimaryKey)) { return false; }
		IBasicPrimaryKey key = (IBasicPrimaryKey)obj;
		return equals(key);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	// ---- Iterator implementation ----

	@Override
	public Iterator<DatabaseValue> iterator() {
		return new Iterator<DatabaseValue>() {
			protected boolean first = true;
			
			@Override
			public DatabaseValue next() {
				if (!hasNext()) { throw new NoSuchElementException(); }
				first = false;
				return SingularBasicPrimaryKey.this;
			}
			
			@Override
			public boolean hasNext() {
				return first;
			}
		};
	}
}
