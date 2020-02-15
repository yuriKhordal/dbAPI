package dbAPI;

import java.util.Iterator;

public interface IDatabaseReader extends Iterable<IRow>, Iterator<IRow> {
	//Returns a row by 'index'
	public IRow getRow(int index);
	//Retruns all the rows
	public IRow[] getAllRows();

	//Checks if there are rows left
	public boolean hasNext();
	//Returns the next value
	public IRow next();
}
