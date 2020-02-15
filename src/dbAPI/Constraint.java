package dbAPI;

//Represents a single column constraint in a table
public class Constraint{
	//The constraint's name
	private String name;
	//The constraint's type
	private ConstraintsEnum type;
	//Text for the CHECK, DEFAULT, FOREIGN_KEY constraints
	private String info;
	//The constraint's target column
	private IColumn target;

	//Initializes a new instance of the Constraint class with the specified type and target
	public Constraint(ConstraintsEnum type, IColumn target){
		this.type = type;
		this.target = target;
	}

	//Sets the constraint's name
	public Constraint setName(String name){
		Constraint c = new Constraint(this.type, this.target);
		c.name = name;
		return c;
	}
	
	//Set a text for CHECK, DEFAULT, FOREIGN_KEY constraints
	public Constraint setInfo(String info){
		Constraint c = new Constraint(this.type, this.target);
		c.info = info;
		return c;
	}

	//Retruns the constraint's name
	public String getName(){
		return this.name;
	}

	//Returns the constraint's type
	public ConstraintsEnum getType(){
		return this.type;
	}

	//Returns the constraint's info
	public String getInfo(){
		return this.info;
	}
}
