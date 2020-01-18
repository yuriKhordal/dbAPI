package dbAPI;

public interface IColumn{
	//Returns the index of the column
	public int getIndex();
	//Returns the name of the column
	public String getName();
	//Returns the type of data
	public DatabaseDataType getType();
	//Returns the constraints of the column
	public ConstraintsEnum getConstraints();
	//Checks whether the column has the specified constraint
	public boolean hasConstraint(ConstraintsEnum constraint);
}
