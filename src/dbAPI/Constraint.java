package dbAPI;

import java.util.Objects;

/**Represents a single column constraint in a table, immutable*/
public abstract class Constraint{
	/**The name of the constraint(optional)*/
	protected String name;
	/**This constraint's type*/
	protected ConstraintsEnum type;

	/**Initializes this constraint with a type
	 * @param type The type of the constraint
	 */
	public Constraint(ConstraintsEnum type){
		this.type = type;
		this.name = null;
	}
	
	public Constraint setName(String name) {
		this.name = name;
		return this;
	}

	/**Get this constraint's type
	 * @return The type of the constraint
	 */
	public ConstraintsEnum getType(){
		return this.type;
	}
	
	/**Get the name of the constraint
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**Get the SQL representation of the constraint
	 * @return The string representation of the constraint
	 */
	public abstract String getSqlString();

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		Constraint con = (Constraint)obj;
		return name == con.name && type == con.type;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, type);
	}
}
