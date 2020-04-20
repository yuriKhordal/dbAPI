package dbAPI;

import java.util.Objects;

/**Represents a check constraint*/
public class CheckConstraint extends Constraint {
	/**The condition for the check*/
	protected String condition;

	/**Initialize a new check constraint with a condition
	 * @param condition The condition for the constraint, for example {@code "Age >= 21"}
	 */
	public CheckConstraint(String condition) {
		super(ConstraintsEnum.CHECK);
		this.condition = condition;
	}
	
	/**Get the condition for the constraint
	 * in the form it is in the sql string,
	 * for example {@code "Age >= 21"}
	 * @return The condition string
	 */
	public String getCondition() {
		return condition;
	}
	
//	/**Set the condition for the constraint
//	 * in the form it is in the sql string,
//	 * for example {@code "Age >= 21"}
//	 * @param condition The condition string
//	 */
//	public void setCondition(String condition) {
//		this.condition = condition;
//	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"CHECK (<i>condition</i>)"</code>
	 */
	public String getSqlString() {
		return "CHECK (" + condition + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		CheckConstraint check = (CheckConstraint)obj;
		return name.equals(check.name) && condition.equals(check.condition);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, condition);
	}

	@Override
	public String toString() {
		return super.toString().replace(';', ',') + " condition: " + condition + ";";
	}
}
