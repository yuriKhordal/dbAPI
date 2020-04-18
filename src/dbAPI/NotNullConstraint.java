package dbAPI;

/**Represents a not null constraint*/
public class NotNullConstraint extends Constraint {
	/**Initialize a new not null constraint*/
	public NotNullConstraint() {
		super(ConstraintsEnum.NOT_NULL);
	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"NOT NULL"</code>
	 */
	public String getSqlString() {
		return "NOT NULL";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if( obj == null || getClass() != obj.getClass()) { return false; }
		return this.name == ((NotNullConstraint)obj).name;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
