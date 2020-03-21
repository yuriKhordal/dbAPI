package dbAPI;

import java.util.Iterator;
import java.util.Objects;

/**Represents a primary key*/
public class PrimaryKey implements IPrimaryKey{
	/**The cells of the primary key*/
	protected DatabaseCell[] cells;
	/**The basic version of the key*/
	protected BasicPrimaryKey basic;
	
	/**Initialize a new primary key with cells
	 * @param cells The cells of the key
	 */
	public PrimaryKey(DatabaseCell...cells) {
		this.cells = cells;
		basic = new BasicPrimaryKey(getValues());
	}

	@Override
	public DatabaseCell[] getCells() {
		return cells;
	}
	
	@Override
	public DatabaseCell getCell(int index) throws IndexOutOfBoundsException {
		return cells[index];
	}

	@Override
	public DatabaseCell getCell() {
		return cells[0];
	}

	@Override
	public IColumn[] getColumns() {
		IColumn[] cols = new IColumn[cells.length];
		for (int i = 0; i < cols.length; i++) {
			cols[i] = cells[i].column;
		}
		return cols;
	}
	
	@Override
	public IColumn getColumn(int index) throws IndexOutOfBoundsException {
		return cells[index].column;
	}

	@Override
	public IColumn getColumn() {
		return cells[0].column;
	}

	@Override
	public DatabaseValue[] getValues() {
		DatabaseValue[] vals = new DatabaseValue[cells.length];
		for(int i = 0; i < cells.length; i++) {
			vals[i] = cells[i].Value;
		}
		return vals;
	}
	
	@Override
	public DatabaseValue getValue(int index) throws IndexOutOfBoundsException {
		return cells[index].Value;
	}

	@Override
	public DatabaseValue getValue() {
		return cells[0].Value;
	}

	@Override
	public BasicPrimaryKey getBasicKey() {
		return basic;
	}
	
	@Override
	public int getKeysCount() {
		return cells.length;
	}

	@Override
	public boolean isSingularKey() {
		return getKeysCount() == 1;
	}
	
	@Override
	public PrimaryKey clone() {
		DatabaseCell[] cells = new DatabaseCell[this.cells.length];
		for (int i = 0; i < cells.length; i++) {
			cells[i] = new DatabaseCell(this.cells[i].column, (DatabaseValue)this.cells[i].Value.clone());
		}
		return new PrimaryKey(cells);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (!(obj instanceof IPrimaryKey)) { return false; }
		IPrimaryKey key = (IPrimaryKey)obj;
		return equals(key);
	}

	@Override
	public boolean equals(IPrimaryKey key) {
		if (this == key) { return true; }
		if (key == null) { return false; }
		DatabaseCell[] cells = key.getCells();
		if (cells.length != this.cells.length) { return false; }
		for (int i = 0; i < cells.length; i++) {
			if (!this.cells[i].equals(cells[i])) { return false; }
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash((Object[])cells);
	}

	@Override
	public Iterator<DatabaseCell> iterator() {
		return new ArrayIterator<DatabaseCell>(cells);
	}
}
