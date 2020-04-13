package dbAPI;

import java.util.ArrayList;
import java.util.Iterator;

/**An abstract implementation of IDatabase*/
public abstract class AbstractDatabase implements IDatabase, Iterable<ITable> {
	/**A list of tables in the database*/
    protected ArrayList<ITable> tables;
    /**The helper used by the API*/
    protected IDatabaseHelper helper;

    /** Initialize the database with a helper and a list of tables
     * @param helper The helper to be used by the API
     * @param tables An iterable of tables
     */
    public AbstractDatabase(IDatabaseHelper helper, Iterable<ITable> tables) {
        this.tables = new ArrayList<ITable>();
        for(ITable table : tables){
            this.tables.add(table);
        }
        this.helper = helper;
    }
    /** Initialize the database with a helper and an array of tables
     * @param helper The helper to be used by the API
     * @param tables An array of tables
     */
    public AbstractDatabase(IDatabaseHelper helper, ITable...tables) {
        this.tables = new ArrayList<ITable>();
        for(ITable table : tables){
            this.tables.add(table);
        }
        this.helper = helper;
    }

    // ---- IDatabase implementation ----

    public ITable[] getTables(){ 
        //TODO: Check if (T[])toArray();
        return (ITable[])tables.toArray();
    }

	public void open(){
        if (!helper.isOpen()){
            helper.open();
        }
    }

	public boolean isOpen(){
        return helper.isOpen();
    }

	public void create(final ITable table){
        tables.add(table);
        helper.create(table);
    }

	public void insert(final ITable table, final IRow row){
        /*DatabaseCell[] cells = row.getCells();
        String[][] cells_string = new String[2][cells.length];
        for(int i = 0; i < cells.length; i++){
            cells_string[0][i] = cells[i].getColumn().getName();
            cells_string[1][i] = cells[i].Value.toString();
        }
        helper.insert(table.getName(), cells_string[0], cells_string[1]);*/
		
		IColumn[] columns = new IColumn[row.getCellsCount()];
		DatabaseValue[] values = new DatabaseValue[columns.length];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = row.getColumn(i);
			values[i] = row.getValue(i);
		}
		helper.insert(table, columns, values);
    }

	public void insert(final ITable table, final IRow[] rows){
        for (IRow row : rows){
            insert(table, row);
        }
    }

	public void update(final ITable table, final IRow row){
        helper.update(table, row);
    }
	
	public void update(final ITable table, final IRow row, String whereCondition){
        helper.update(table, row, whereCondition);
    }

	public IDatabaseReader select(final ITable table, final IColumn[] columns){
        /*String[] col = new String[columns.length];
        int i = 0;
        for(IColumn c : columns){
            col[i++] = c.getName();
        }
        return helper.select(table.getName(), col);*/
		
		return helper.select(table, columns);
    }
	
	public IDatabaseReader select(final ITable table, final IColumn[] columns, String whereCondition){
        /*String[] col = new String[columns.length];
        int i = 0;
        for(IColumn c : columns){
            col[i++] = c.getName();
        }
        return helper.select(table.getName(), col, whereCondition);*/
		
		return helper.select(table, columns, whereCondition);
    }
	
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns){
        /*String[] col = new String[columns.length];
        String[] tab = new String[tables.length];
        int i = 0;
        for(IColumn c : columns){
            col[i++] = c.getName();
        }
        i = 0;
        for(ITable t : tables){
            tab[i++] = t.getName();
        }
        return helper.select(tab, col);*/
		
		return helper.select(tables, columns);
    }
	
	public IDatabaseReader select(final ITable[] tables, final IColumn[] columns, String whereCondition){
        /*String[] col = new String[columns.length];
        String[] tab = new String[tables.length];
        int i = 0;
        for(IColumn c : columns){
            col[i++] = c.getName();
        }
        i = 0;
        for(ITable t : tables){
            tab[i++] = t.getName();
        }
        return helper.select(tab, col, whereCondition);*/
		
		return helper.select(tables, columns, whereCondition);
    }
	
	public IDatabaseReader selectAll(final ITable table){
        return helper.selectAll(table);
    }
	
	public IDatabaseReader selectAll(final ITable table, String whereCondition){
        return helper.selectAll(table, whereCondition);
    }
	
	public IDatabaseReader selectAll(final ITable[] tables){
        /*String[] tab = new String[tables.length];
        int i = 0;
        for(ITable t : tables){
            tab[i++] = t.getName();
        }
        return helper.selectAll(tab);*/
		
		return helper.selectAll(tables);
    }
	
	public IDatabaseReader selectAll(final ITable[] tables, String whereCondition){
        /*String[] tab = new String[tables.length];
        int i = 0;
        for(ITable t : tables){
            tab[i++] = t.getName();
        }
        return helper.selectAll(tab, whereCondition);*/
		
		return helper.selectAll(tables, whereCondition);
    }

	public void delete(final ITable table, String whereCondition){
        helper.delete(table, whereCondition);
    }

	public void drop(final ITable table){
        helper.drop(table);
        this.tables.remove(table);
    }

	public void close(){
        helper.close();
    }
	
	public abstract AbstractDatabase clone();

    // ---- Iterable implementation ----

    public Iterator<ITable> iterator() {
        return tables.iterator();
    }
}