package dbAPI;

import java.util.Iterator;

/**Represents a database row*/
public class Row implements IRow, Iterable<DatabaseCell> {
	protected DatabaseCell[] cells;

	/**Initialize a row with given cells
	 * @param cells The cells of the row
	 */
	public Row(final DatabaseCell[] cells) {
		this.cells = cells.clone();
	}

	// ---- IRow implimintation ----

	public IColumn getColumn(int index) {
		return cells[index].getColumn();
	}

	/** {@inheritDoc}
	 * @throws IllegalArgumentException If the column with the given name is not found in the row 
	 */
	public IColumn getColumn(String name) throws IllegalArgumentException {
		for (DatabaseCell cell : cells) {
			if (cell.getColumn().getName() == name) {
				return cell.getColumn();
			}
		}
		throw new IllegalArgumentException("'" + name + "' not found in the row");
	}

	public IColumn[] getColumns() {
		IColumn[] cols = new IColumn[cells.length];
		for (int i = 0; i < cols.length; i++) {
			cols[i] = cells[i].getColumn();
		}
		return cols;
	}

	/** {@inheritDoc}
	 * @throws IllegalArgumentException If the column is not found in the row
	 */
	public int getIndex(final IColumn column) throws IllegalArgumentException {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].getColumn() == column) {
				return i;
			}
		}
		throw new IllegalArgumentException("There is no such column in the row.");
	}

	/** {@inheritDoc}
	 * @throws IllegalArgumentException If the column with the given name is not found in the row
	 */
	public int getIndex(String column) throws IllegalArgumentException {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].getColumn().getName() == column) {
				return i;
			}
		}
		throw new IllegalArgumentException("There is no such column in the row.");
	}

	public DatabaseCell getCell(int index) {
		return cells[index];
	}

	public DatabaseCell getCell(IColumn column) {
		return cells[getIndex(column)];
	}

	public DatabaseCell getCell(String column) {
		return cells[getIndex(column)];
	}

	public DatabaseCell[] getCells(){
		return cells.clone();
	}

	public DatabaseValue[] getValues() {
		DatabaseValue values[] = new DatabaseValue[cells.length];
		int i = 0;
		for(DatabaseCell cell : cells){
			values[i++] = cell.Value;
		}
		return values;
	}

	public void setValue(int index, DatabaseValue value) {
		cells[index].Value = value;

	}

	public void setValue(IColumn column, DatabaseValue value) {
		cells[getIndex(column)].Value = value;

	}

	public void setValue(String column, DatabaseValue value) {
		cells[getIndex(column)].Value = value;

	}

	// ---- Iterable implementation ----

	public Iterator<DatabaseCell> iterator() {
		return new ArrayIterator<DatabaseCell>(cells);
	}
}
