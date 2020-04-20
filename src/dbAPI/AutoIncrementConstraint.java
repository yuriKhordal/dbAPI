package dbAPI;

/**Represents an auto increment constraint*/
public class AutoIncrementConstraint extends Constraint {
	/**The sql string of primary key, set up before using {@link #getSqlString()}*/
	public static String SqlString = null;

	/**Initialize a new auto increment constraint
	 */
	public AutoIncrementConstraint() {
		super(ConstraintsEnum.AUTO_INCREMENT);
	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Initialize {@link #SqlString} before calling <br>
	 * Return the value in {@link #SqlString}
	 * @throws NullPointerException If {@link #SqlString} is not initialized
	 */
	public String getSqlString() throws NullPointerException {
		if (SqlString == null) {
			throw new NullPointerException("SqlString not initialized beforehand and thus is null");
		}
		return SqlString;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		AutoIncrementConstraint auto = (AutoIncrementConstraint)obj;
		return name.equals(auto.name);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
