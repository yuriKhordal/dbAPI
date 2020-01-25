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

    public IColumn getColumn(){
        return column;
    }
}