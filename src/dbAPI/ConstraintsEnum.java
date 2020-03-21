package dbAPI;

/**An enum for different column constraints*/
public enum ConstraintsEnum{
	/**Auto assign incrementing values to the column, typically starting at 0*/
	AUTO_INCREMENT,
	/**Check for specified condition before allowing to insert a value*/
	CHECK,
	/**If no value given, assigns a default value*/
	DEFAULT,
	/**A key from a different table, has to match an existing value in the other table*/
	FOREIGN_KEY,
	/**Used to create and retrieve data from the database very quickly*/
	INDEX,
	/**Don't allow null values*/
	NOT_NULL,
	/**UNIQUE and NOT_NULL, used to uniquely identify a row in a table*/
	PRIMARY_KEY,
	/**Ensures that the value doesn't exist already*/
	UNIQUE
}
