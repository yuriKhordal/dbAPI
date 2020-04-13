package dbAPI;

/**A listener interface for converting IRow to T*/
public interface IRowConverter<T extends IRow>{
	/**Convert an {@link IRow} row to a specific table's row type
	 * @param row - The row to convert
	 * @return A converted row
	 */
	T convertFromIRow(IRow row);
}