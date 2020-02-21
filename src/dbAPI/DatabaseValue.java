package dbAPI;

/**Represents a value and a type in a database*/
public class DatabaseValue{
	/**The value in {@link Object} form*/
	public Object Value;
	/**The data type of the value*/
	protected DatabaseDataType type;

	/**Initialize this database value with a generic value, and a type
	 * @param value The value in {@link Object} form
	 * @param type The type of the value
	 */
	public DatabaseValue(Object value, DatabaseDataType type){
		Value = value;
		this.type = type;
	}

	/**Get the type of the value
	 * @return A database data type of the value
	 */
	public DatabaseDataType getType(){
		return type;
	}
	
	//Getters for different data types
	//if can't convert return null
	
	/**Convert this value to a specified class
	 * @param c The class to which to convert
	 * @return The value converted to the given class
	 */
	protected Object convertTo(Class c){
		try{
			return c.cast(Value);
		} catch(ClassCastException e){
			return null;
		}
	}

	/**Get the value in {@link Boolean} form
	 * @return The value in {@link Boolean} form. if can't convert, null
	 */
	public Boolean getBoolean(){
		return (Boolean)convertTo(boolean.class);
	}

	/**Get the value in byte[] form
	 * @return The value in byte[] form. if can't convert, null
	 */
	public byte[] getByteArray(){
		return (byte[])convertTo(byte[].class);
	}

	//TODO: implement for date-time type
	/*public DTTYPE getDTTYPE(){
	  	return convertTo(DTTYPE);
	}*/

	/**Get the value in {@link Double} form
	 * @return The value in {@link Double} form. if can't convert, null
	 */
	public Double getDouble(){
		return (Double)convertTo(double.class);
	}

	/*public Enum getEnum(){
		return convertTo(Enum);
	}*/

	/**Get the value in {@link Integer} form
	 * @return The value in {@link Integer} form. if can't convert, null
	 */
	public Integer getInteger(){
		return (Integer)convertTo(int.class);
	}

	/**Get the value in {@link String} form
	 * @return The value in {@link String} form. if can't convert, null
	 */
	public String getString(){
		return (String)convertTo(String.class);
	}

	@Override
	public String toString() {
		return Value.toString();
	}
}
