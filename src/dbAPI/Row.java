package dbAPI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**Represents a database row*/
public class Row implements IRow, Iterable<DatabaseCell>, Cloneable {
	/**The cells in the row*/
	protected DatabaseCell[] cells;
	protected IPrimaryKey pkey;

	/**Initialize a row with given cells
	 * @param cells The cells of the row
	 */
	public Row(final DatabaseCell... cells) {
		this.cells = cells.clone();
		ArrayList<DatabaseCell> keys = new ArrayList<DatabaseCell>(cells.length);
		for (DatabaseCell cell : cells) {
			if (cell.column.hasConstraint(ConstraintsEnum.PRIMARY_KEY)) {
				keys.add(cell);
			}
		}
		
		if (keys.size() == 1) {
			pkey = new SingularPrimaryKey(keys.get(0));
		} else if (keys.size() > 1) {
			pkey = new PrimaryKey((DatabaseCell[])keys.toArray());
		} else {
			pkey = null;
		}
	}

	// ---- IRow implementation ----

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

	public DatabaseCell[] getCells(){
		return cells.clone();
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
	
	@Override
	public int getCellsCount() {
		return cells.length;
	}
	
	@Override
	public DatabaseValue getValue(int index) {
		return cells[index].Value;
	}
	
	@Override
	public DatabaseValue getValue(IColumn column) {
		return getCell(column).Value;
	}
	
	@Override
	public DatabaseValue getValue(String column) {
		return getCell(column).Value;
	}

	public DatabaseValue[] getValues() {
		DatabaseValue values[] = new DatabaseValue[cells.length];
		int i = 0;
		for(DatabaseCell cell : cells){
			values[i++] = cell.Value;
		}
		return values;
	}

	public IPrimaryKey getPrimaryKey() {
		return (IPrimaryKey)pkey.clone();
	}
	
	public boolean hasPrimaryKey() {
		return pkey != null;
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
	
	@Override
	public Row clone() {
		DatabaseCell[] cells = new DatabaseCell[this.cells.length];
		for(int i = 0; i < cells.length; i++) {
			cells[i] = (DatabaseCell)this.cells[i].clone();
		}
		return new Row(cells);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash((Object[])cells);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null || getClass() != obj.getClass()) { return false; }
		Row row = (Row)obj;
		if (this.cells.length != row.cells.length) { return false; }
		for (int i = 0; i < cells.length; i++) {
			if (!cells[i].equals(row.cells[i])) { return false; }
		}
		return true;
	}
}
