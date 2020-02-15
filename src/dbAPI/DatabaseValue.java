package dbAPI;

//Represents a value and a type in a database
public class DatabaseValue{
	public Object Value;
	private DatabaseDataType type;

	public DatabaseValue(Object value, DatabaseDataType type){
		Value = value;
		this.type = type;
	}

	public DatabaseDataType getType(){
		return type;
	}
	
	//Getters for different data types
	//if can't convert return null
	
	private Object convertTo(Class c){
		try{
			return c.cast(Value);
		} catch(ClassCastException e){
			return null;
		}
	}

	public Boolean getBoolean(){
		return (Boolean)convertTo(boolean.class);
	}

	public byte[] getByteArray(){
		return (byte[])convertTo(byte[].class);
	}

	//TODO: impliment for date-time type
	/*public DTTYPE getDTTYPE(){
	  	return convertTo(DTTYPE);
	}*/

	public Double getDouble(){
		return (Double)convertTo(double.class);
	}

	/*public Enum getEnum(){
		return convertTo(Enum);
	}*/

	public Integer getInteger(){
		return (Integer)convertTo(int.class);
	}

	public String getString(){
		return (String)convertTo(String.class);
	}

	@Override
	public String toString() {
		return Value.toString();
	}
}
