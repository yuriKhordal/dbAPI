package dbAPI;

import java.util.Iterator;
import java.util.Objects;

/**Represents only the values of a primary key*/
public class BasicPrimaryKey implements IBasicPrimaryKey {
	/**The values of this key*/
	protected DatabaseValue[] values;

	public BasicPrimaryKey(DatabaseValue...values) {
		if (values == null) {
			throw new NullPointerException("values can't be null");
		}
		this.values = values;
	}
	
	// ---- IBasicPrimaryKey implementation ----

	@Override
	public DatabaseValue[] getValues() {
		DatabaseValue[] values = new DatabaseValue[this.values.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = (DatabaseValue)this.values[i].clone();
		}
		return values;
	}
	
	@Override
	public DatabaseValue getValue(int index) throws IndexOutOfBoundsException {
		return values[index].clone();
	}

	@Override
	public DatabaseValue getValue() {
		return (DatabaseValue)values[0].clone();
	}
	
	@Override
	public int getKeysCount() {
		return values.length;
	}

	@Override
	public boolean isSingularKey() {
		return values.length == 1;
	}

	@Override
	public boolean equals(IBasicPrimaryKey key) {
		if (this == key) { return true; }
		if (key == null) { return false; }
		DatabaseValue[] values = key.getValues();
		if (values.length != this.values.length) { return false; }
		for (int i = 0; i < values.length; i++) {
			if (!this.values[i].equals(values[i])) { return false; }
		}
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof IBasicPrimaryKey)) { return false; }
		IBasicPrimaryKey key = (IBasicPrimaryKey)obj;
		return equals(key);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash((Object[])values);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Basic Primary Key: ");
		for (int i = 0; i < values.length;i ++){
			str.append('\n').append(values[i]);
			if (i < values.length - 1){
				str.append(',');
			}
		}

		return str.append(';').toString();
	}
	
	// ---- Cloneable implementation
	
	@Override
	public BasicPrimaryKey clone() {
		return new BasicPrimaryKey(getValues());
	}
	
	// ---- Iterator implementation ----

	@Override
	public Iterator<DatabaseValue> iterator() {
		return new ArrayIterator<DatabaseValue>(values);
	}
}
