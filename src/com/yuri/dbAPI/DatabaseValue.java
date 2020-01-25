package dbAPI;

//Represents a value and a type in a database
public class DatabaseValue{
	public Object Value;
	private DatabaseDataType type;
	private String dbType;//The type in sql language

	public DatabaseValue(Object value, DatabaseDataType type, String dbType){
		Value = value;
		this.type = type;
		this.dbType = dbType;
	}

	public DatabaseDataType getType(){
		return type;
	}

	//Returns the type as defined in the database, therefore can be used directly in sql commands
	public String getDatabaseType(){
		return dbType;
	}
	
	//Getters for different data types
	//if can't convert return null
	
	private @Nullable Object convertTo(Class c){
		try{
			return c.cast(Value);
		} catch(ClassCastException e){
			return null;
		}
	}

	public @Nullable Boolean getBoolean(){
		return (Boolean)convertTo(boolean.class);
	}

	public @Nullable byte[] getByteArray(){
		return (byte[])convertTo(byte[].class);
	}

	//TODO: impliment for date-time type
	/*public @Nullable DTTYPE getDTTYPE(){
	  	return convertTo(DTTYPE);
	}*/

	public @Nullabe Double getDouble(){
		return (Double)convertTo(double.class);
	}

	/*public @Nullable Enum getEnum(){
		return convertTo(Enum);
	}*/

	public @Nullable Integer getInteger(){
		return (Integer)convertTo(int.class);
	}

	public @Nullable String getString(){
		return (String)convertTo(String.class);
	}
}
