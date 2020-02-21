package dbAPI;

/**Represents a single column constraint in a table, immutable*/
public class Constraint{
	/**This constraint's name in the database, optional*/
	protected String name;
	/**This constraint's type*/
	protected ConstraintsEnum type;
	/**Text for the CHECK, DEFAULT, FOREIGN_KEY constraints*/
	protected String info;
	/**This constraint's column*/
	protected IColumn target;

	/**Initializes this constraint with a type and target column
	 * @param type The type of the constraint
	 * @param target The column to which the constraint is attached
	 */
	public Constraint(ConstraintsEnum type, IColumn target){
		this.type = type;
		this.target = target;
	}

	/**Set this constraint's name
	 * @param name The name of the constraint as in the database
	 * @return A new constraint
	 */
	public Constraint setName(String name){
		Constraint c = new Constraint(this.type, this.target);
		c.info = this.info;
		c.name = name;
		return c;
	}
	
	/**Set a text for CHECK, DEFAULT, FOREIGN_KEY constraints
	 * @param info The info text for this constraint
	 * @return A new constraint
	 */
	public Constraint setInfo(String info){
		Constraint c = new Constraint(this.type, this.target);
		c.name = this.name;
		c.info = info;
		return c;
	}

	/**Get this constraint's name
	 * @return The name of the constraint as in the database, null if no name
	 */
	public String getName(){
		return this.name;
	}

	/**Get this constraint's type
	 * @return The type of the constraint
	 */
	public ConstraintsEnum getType(){
		return this.type;
	}

	/**Get this constraint's info
	 * @return A string of text for the CHECK, DEFAULT, FOREIGN_KEY constraints, null if no info
	 */
	public String getInfo(){
		return this.info;
	}
}
