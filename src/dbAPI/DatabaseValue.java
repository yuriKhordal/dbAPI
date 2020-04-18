package dbAPI;

import java.time.LocalDateTime;
import java.util.Objects;

/**Represents a value and a type in a database*/
public class DatabaseValue implements Cloneable{
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

	/**Get the value in boolean form
	 * @return The value in boolean form. if can't convert, null
	 */
	public boolean getBoolean(){
		return (Boolean)convertTo(Boolean.class);
	}

	/**Get the value in byte[] form
	 * @return The value in byte[] form. if can't convert, null
	 */
	public byte[] getByteArray(){
		return (byte[])convertTo(byte[].class);
	}

	/**Get the value in {@link LocalDateTime} form
	 * @return The value in {@link LocalDateTime} form. if can't convert, null
	 */
	public LocalDateTime getDateTime(){
	  	return (LocalDateTime)convertTo(LocalDateTime.class);
	}

	/**Get the value in double form
	 * @return The value in Double form. if can't convert, null
	 */
	public double getDouble(){
		return (Double)convertTo(Double.class);
	}

	/*public Enum getEnum(){
		return convertTo(Enum);
	}*/

	/**Get the value in int form
	 * @return The value in int form. if can't convert, null
	 */
	public int getInt(){
		return (Integer)convertTo(Integer.class);
	}

	/**Get the value in {@link String} form
	 * @return The value in {@link String} form. if can't convert, null
	 */
	public String getString(){
		return (String)convertTo(String.class);
	}
	
	@Override
	protected DatabaseValue clone() {
		return new DatabaseValue(Value, type);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null || getClass() != obj.getClass()) {return false;}
		DatabaseValue value = (DatabaseValue)obj;
		return this.type == value.type && this.Value.equals(value.Value);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(Value, type);
	}

	@Override
	public String toString() {
		return "Database Value: type: " + type + "\nValue: " + Value.toString();
	}
}
