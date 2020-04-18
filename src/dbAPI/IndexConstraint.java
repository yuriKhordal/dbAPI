package dbAPI;

import java.util.Objects;

/**Represents an index constraint*/
public class IndexConstraint extends Constraint {
	/**The table and columns of the index*/
	protected String target;
	/**Whether the index rows are unique*/
	protected boolean unique;

	/**Initialize a new index constraint with a name target and whether it's unique
	 * @param name The name of the index
	 * @param target The target table and columns
	 * @param unique Whether the index rows are unique
	 */
	public IndexConstraint(String name, String target, boolean unique) {
		super(ConstraintsEnum.INDEX);
		this.name = name;
		this.target = target;
		this.unique = unique;
	}
	
	/**Initialize a new index constraint with a name target
	 * @param name The name of the index
	 * @param target The target table and columns
	 */
	public IndexConstraint(String name, String target) {
		this(name, target, false);
	}
	
	/**Get the target table and columns
	 * @return The target table and columns
	 */
	public String getTarget() {
		return target;
	}
	
	/**Check whether the index is unique
	 * @return True if the index is unique
	 */
	public boolean isUnique() {
		return unique;
	}

	/**{@inheritDoc}<br>
	 * <br>
	 * Return as <code>"<i>[UNIQUE]</i> INDEX <i>name</i> ON <i>target</i>"</code>
	 */
	public String getSqlString() {
		return (unique ? "UNIQUE " : "") + "INDEX " + name + " ON " + target;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		IndexConstraint index = (IndexConstraint)obj;
		return name == index.name && target == index.target && unique == index.unique;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, target, unique);
	}

	@Override
	public String toString() {
		return super.toString().replace(';', ',') + (unique ? " unique," : "") + " indices: " + target + ";";
	}
}
