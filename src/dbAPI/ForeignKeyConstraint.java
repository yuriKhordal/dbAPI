package dbAPI;

import java.util.Objects;

/**Represents a foreign key constraint*/
public class ForeignKeyConstraint extends Constraint {
	/**The table and column the key references*/
	protected String reference;
	/**The name of the column this constraint is in*/
	protected String column;
	
	/**Initialize a new foreign key constraint with a column and a referenced column
	 * @param column The name of the column this constraint is in
	 * @param reference The column this constraint references, including its table
	 */
	public ForeignKeyConstraint(String column, String reference) {
		super(ConstraintsEnum.FOREIGN_KEY);
		this.column = column;
		this.reference = reference;
	}
	
	/**Get the name of the foreign key column
	 * @return The name of the foreign key column
	 */
	public String getColumn() {
		return column;
	}
	
	/**Get the name of the column this constraint references
	 * @return The name of the column, including its table
	 */
	public String getReference() {
		return reference;
	}
	
//	/**Set the name of the column this constraint references
//	 * @param reference The name of the column, including its table
//	 */
//	public void setReference(String reference) {
//		this.reference = reference;
//	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"FOREIGN KEY (<i>column</i>) REFERENCES <i>table</i>(<i>column</i>)"</code>
	 */
	public String getSqlString() {
		return "FOREIGN KEY (" + column + ") REFERENCES " + reference;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		ForeignKeyConstraint fk = (ForeignKeyConstraint)obj;
		return name == fk.name && reference == fk.reference && column == fk.column;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, reference, column);
	}

	@Override
	public String toString() {
		return super.toString().replace(';', ',') + " referances: " + reference + ";";
	}
}
