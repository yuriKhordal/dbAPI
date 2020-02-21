package dbAPI;

import dbAPI.Constraint;
import dbAPI.ConstraintsEnum;
import dbAPI.DatabaseDataType;
import dbAPI.IColumn;

/**Represents a column in a database table*/
public class Column implements IColumn {
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
	public Column(String name, int index, DatabaseDataType type, Constraint[] constraints) {
		this(name, index, type);
		this.constraints = new Constraint[constraints.length];
		for (int i = 0; i < constraints.length; i++) {
			Constraint c = constraints[i];
			this.constraints[i] = new Constraint(c.getType(), this).setName(c.getName()).setInfo(c.getInfo());
		}
	}
	
	/**Initialize this column with a name, index, type of data, and an array of constraints
	 * @param name The name of the column as in the database
	 * @param index The index of the column as in the database
	 * @param type The database data type of the column
	 * @param constraints An array of constraints for this column
	 */
	public Column(String name, int index, DatabaseDataType type, ConstraintsEnum ... constraints) {
		this(name, index, type);
		this.constraints = new Constraint[constraints.length];
		int i = 0;
		for (ConstraintsEnum c : constraints) {
			this.constraints[i++] = new Constraint(c, this);
		}
	} 

	@Override
	public Constraint[] getConstraints() {
		return constraints.clone();
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
		for (Constraint c : constraints) {
			if (c.getType() == constraint) {
				return true;
			}
		}
		return false;
	}

}
