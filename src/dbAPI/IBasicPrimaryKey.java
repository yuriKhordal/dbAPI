package dbAPI;

/**An interface that represents a primary key that only contains values*/
public interface IBasicPrimaryKey extends Cloneable, Iterable<DatabaseValue>{
	/**Get the values that this key consists of
	 * @return This key's values
	 */
	public DatabaseValue[] getValues();
	
	/**Get the value at a specified index
	 * @param index The index of the value
	 * @return The value at the given index
	 * @throws IndexOutOfBoundsException If the index isn't between 1 and the number of values
	 */
	public DatabaseValue getValue(int index) throws IndexOutOfBoundsException;
	
	/**Get the value of the key. if the primary key has
	 * more then one value it will return the first value
	 * on the list
	 * @return The value of the key
	 */
	public DatabaseValue getValue();
	
	/**Get the number of values that make up this primary key
	 * @return The number of keys in the primary key
	 */
	public int getKeysCount();
	
	/**Check whether the key is made up of only one value or multiple values
	 * @return True if the key is only one value
	 */
	public boolean isSingularKey();
	
	public IBasicPrimaryKey clone();
	
	/**Equals for {@link IBasicPrimaryKey}
	 * @see Object#equals(Object)
	 */
	public boolean equals(IBasicPrimaryKey key);
}
