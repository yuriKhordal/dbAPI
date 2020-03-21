package dbAPI;

/**Represents a unique constraint*/
public class UniqueConstraint extends Constraint {
	/**Initialize a new unique constraint*/
	public UniqueConstraint() {
		super(ConstraintsEnum.UNIQUE);
	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"UNIQUE"</code>
	 */
	public String getSqlString() {
		return "UNIQUE";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if( obj == null || getClass() != obj.getClass()) { return false; }
		return this.name == ((UniqueConstraint)obj).name;
	}
}
