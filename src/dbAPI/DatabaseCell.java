package dbAPI;

public class DatabaseCell{
    private IColumn column;
    public DatabaseValue Value;

    public DatabaseCell(IColumn col) {
        column = col;
    }

    public DatabaseCell(IColumn col, DatabaseValue val){
        column = col;
        Value = val;
    }
    
    public DatabaseCell(IColumn col, Object value, DatabaseDataType type){
        this(col, new DatabaseValue(value, type));
    }

    public IColumn getColumn(){
        return column;
    }
}