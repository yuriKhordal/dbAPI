package dbAPI;

/**An interface that represents a column in a database table*/
public interface IColumn extends Cloneable{
	/**Get the index of this column
	 * @return The index of this column
	 */
	public int getIndex();
	
	/**Get the name of this column
	 * @return The name of this column
	 */
	public String getName();
	
	/**Get the type of data in this column
	 * @return The database type of data in this column
	 */
	public DatabaseDataType getType();
	
	/**Get all of the constraints of this column
	 * @return An array of constraints for this column
	 */
	public Constraint[] getConstraints();
	
	/**Check whether this column has a specified constraint
	 * @param constraint The constraint to check this column for
	 * @return True if this column has the given constraint, otherwise False
	 */
	public boolean hasConstraint(ConstraintsEnum constraint);
	
	public IColumn clone();
}
