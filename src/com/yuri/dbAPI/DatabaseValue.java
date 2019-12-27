package dbAPI;

//Represents a value and a type in a database
public class DatabaseValue{
	public Object Value;
	private DatabaseDataType type;
	private String dbType;

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
			return (c)value;
		} catch(Exception e){
			return null;
		}
	}

	public @Nullable Boolean getBoolean(){
		return convertTo(boolean);
	}

	public @Nullable byte[] getByteArray(){
		return convertTo(byte[]);
	}

	//TODO: impliment for date-time type
	/*public @Nullable DTTYPE getDTTYPE(){
	  	return convertTo(DTTYPE);
	}*/

	public @Nullabe Double getDouble(){
		return convertTo(double);
	}

	/*public @Nullable Enum getEnum(){
		return convertTo(Enum);
	}*/

	public @Nullable Integer getInteger(){
		return convertTo(int);
	}

	public @Nullable String getString(){
		return convertTo(String);
	}
}
