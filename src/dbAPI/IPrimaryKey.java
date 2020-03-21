package dbAPI;

/**An interface that represents a primary key*/
public interface IPrimaryKey extends Cloneable, Iterable<DatabaseCell> {
	
	/**Get the cells that the key contains
	 * @return The cells in the key
	 */
	public DatabaseCell[] getCells();
	
	/**Get the cell at a specified index
	 * @param index The index of the cell
	 * @return The cell at the given index
	 * @throws IndexOutOfBoundsException If the index isn't between 1 and the number of cells
	 */
	public DatabaseCell getCell(int index) throws IndexOutOfBoundsException;
	
	/**Get the cell of the key. If the key has multiple cells,
	 * it will return the first of the cells
	 * @return The first cell of the key
	 */
	public DatabaseCell getCell();
	
	/**Get the columns that make up the key
	 * @return The columns of the key
	 */
	public IColumn[] getColumns();
	
	/**Get the column at a specified index
	 * @param index The index of the column
	 * @return The column at the given index
	 * @throws IndexOutOfBoundsException If the index isn't between 1 and the number of columns
	 */
	public IColumn getColumn(int index) throws IndexOutOfBoundsException;
	
	/**Get the column that is the primary key. If the key
	 * is made up of multiple columns, it will return the
	 * first column in the list
	 * @return The column of the key
	 */
	public IColumn getColumn();
	
	/**Get the values of the primary key
	 * @return The values of the primary key
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
	
	/**Get the basic version of the primary key, with only values
	 * @return The basic version of this key
	 */
	public IBasicPrimaryKey getBasicKey();
	
	/**Get the number of columns that make up this primary key
	 * @return The number of keys in the primary key
	 */
	public int getKeysCount();
	
	/**Check whether the key is made up of only one column or multiple columns
	 * @return True if the key is only one column
	 */
	public boolean isSingularKey();
	
	public IPrimaryKey clone();
	
	/**Equals for {@link IPrimaryKey}
	 * @see Object#equals(Object)
	 */
	public boolean equals(IPrimaryKey key);
}