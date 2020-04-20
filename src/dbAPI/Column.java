package dbAPI;

import java.util.Objects;

/**Represents a column in a database table*/
public class Column implements IColumn, Cloneable {
	/**This column's name as in the database*/
	protected String name;
	/**This column's index as in the database*/
	protected int index;
	/**This column's database data type*/
	protected DatabaseDataType type;
	/**The constraints of this column*/
	protected Constraint[] constraints;
	
	/**Initialize this column with a name, index, and a type of data
	 * @param name The name of the column as in the database
	 * @param index The index of the column as in the database
	 * @param type The database data type of the column
	 */
	public Column(String name, int index, DatabaseDataType type) {
		this.name = name;
		this.index = index;
		this.type = type;
	}
	
	/**Initialize this column with a name, index, type of data, and an array of constraints
	 * @param name The name of the column as in the database
	 * @param index The index of the column as in the database
	 * @param type The database data type of the column
	 * @param constraints An array of constraints for this column
	 */
	public Column(String name, int index, DatabaseDataType type, Constraint ... constraints) {
		this(name, index, type);
		this.constraints = new Constraint[constraints.length];
		for (int i = 0; i < constraints.length; i++) {
			Constraint c = constraints[i];
			this.constraints[i] = c;
		}
	}
	
//	/**Initialize this column with a name, index, type of data, and an array of constraints
//	 * @param name The name of the column as in the database
//	 * @param index The index of the column as in the database
//	 * @param type The database data type of the column
//	 * @param constraints An array of constraints for this column
//	 */
//	public Column(String name, int index, DatabaseDataType type, ConstraintsEnum ... constraints) {
//		this(name, index, type);
//		this.constraints = new Constraint[constraints.length];
//		int i = 0;
//		for (ConstraintsEnum c : constraints) {
//			this.constraints[i++] = new Constraint(c);
//		}
//	} 

	@Override
	public Constraint[] getConstraints() {
		return constraints;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DatabaseDataType getType() {
		return type;
	}

	@Override
	public boolean hasConstraint(ConstraintsEnum constraint) {
		if (constraints == null) { return false; }
		for (Constraint c : constraints) {
			if (c.getType().equals(constraint)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Column clone() {
		Constraint[] c = constraints.clone();
		return new Column(name, index, type, c);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		Column col = (Column)obj;
		return name.equals(col.name) && index == col.index && type.equals(col.type);
	}
	
	@Override
	public int hashCode() {
		Object[] arr = new Object[(constraints != null ? constraints.length : 0) + 3];
		arr[0] = name; arr[1] = index; arr[2] = type;
		if (constraints != null){
			for(int i = 0; i < constraints.length; i++) {
				arr[i + 3] = constraints[i];
			}
		}
		return Objects.hash(arr);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Column '" + name + "', index: " + index + ", type: " + type);
		if (constraints != null && constraints.length > 0){
			for (Constraint constraint : constraints){
				str.append(',').append('\n').append(constraint);
			}
		}

		return str.append(';').toString();
	}
}
