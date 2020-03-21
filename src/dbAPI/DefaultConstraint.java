package dbAPI;

import java.util.Objects;

/**Represents a default constraint*/
public class DefaultConstraint extends Constraint {
	/**The default value*/
	protected String defaultValue;

	/**Initialize a new default constraint with a default value
	 * @param defaultValue The default value for the constraint 
	 */
	public DefaultConstraint(String defaultValue) {
		super(ConstraintsEnum.DEFAULT);
		this.defaultValue = defaultValue;
	}
	
	/**Get the default value of the condition
	 * @return The default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	
//	/**Set the default value of the condition
//	 * @param defaultValue The default value
//	 */
//	public void setDefaultValue(String defaultValue) {
//		this.defaultValue = defaultValue;
//	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"DEFALUT <i>value</i>"</code>
	 */
	public String getSqlString() {
		return "DEFAULT " + defaultValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		DefaultConstraint def = (DefaultConstraint)obj;
		return name == def.name && defaultValue == def.defaultValue;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, defaultValue);
	}
}
