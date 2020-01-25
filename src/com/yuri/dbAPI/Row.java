package dbAPI;

import java.util.Iterator;

//A database row
public class Row implements IRow, Iterable<DatabaseCell> {
	private DatabaseCell[] cells;

	// Initialize the row with 'cells'
	public Row(final DatabaseCell[] cells) {
		this.cells = cells.clone();
	}

	//----IRow implimintation----

	// Returns collumn at index
	public IColumn getColumn(int index) {
		return cells[index].Column;
	}

	// Returns collumn called 'name'
	public IColumn getColumn(String name) throws IllegalArgumentException {
		for (DatabaseCell cell : cells) {
			if (cell.Column.getName() == name) {
				return cell.Column;
			}
		}
		throw new IllegalArgumentException("'" + name + "' not found in the row");
	}

	// Returns all columns
	public IColumn[] getColumns() {
		IColumn[] cols = new IColumn[cells.length];
		for (int i = 0; i < cols.length; i++) {
			cols[i] = cells[i].Column;
		}
		return cols;
	}

	//Returns the index of 'column'
	public int getIndex(final IColumn column) throws IllegalArgumentException {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].getColumn() == column) {
				return i;
			}
		}
		throw new IllegalArgumentException("There is no such column in the row.");
	}

	//Returns the index of 'column'
	public int getIndex(String column) throws IllegalArgumentException {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].getColumn().getName() == column) {
				return i;
			}
		}
		throw new IllegalArgumentException("There is no such column in the row.");
	}

	//Retruns the cell at index
	public DatabaseCell getCell(int index) {
		return cells[index];
	}

	//Returns the cell at 'column'
	public DatabaseCell getCell(IColumn column) {
		return cells[getIndex(column)];
	}

	//Return the cell called 'column'
	public DatabaseCell getCell(String column) {
		return cells[getIndex(column)];
	}

	//Returns all the values sorted by index
	public DatabaseValue[] getValues() {
		DatabaseValue values[] = new DatabaseValue[cells.length];
		int i = 0;
		for(DatabaseCell cell : cells){
			values[i++] = cell.Value;
		}
		return values;
	}

	//Sets the value of the cell at 'index'
	public void setValue(int index, DatabaseValue value) {
		cells[index].Value = value;

	}

	//Sets the value of the cell at 'column'
	public void setValue(IColumn column, DatabaseValue value) {
		cells[getIndex(column)].Value = value;

	}

	//Sets the value of the cell at 'column'
	public void setValue(String column, DatabaseValue value) {
		cells[getIndex(column)].Value = value;

	}

	// ----Iterable implimintation----

	public Iterator<DatabaseCell> iterator() {
		return new ArrayIterator<DatabaseCell>(cells);
	}
}
