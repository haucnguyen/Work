package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Hau on 3/1/17.
 */
public class Table {
    String tablename;
    ArrayList<String> columnNames = new ArrayList<>();
    ArrayList<String> columnTypes = new ArrayList<>();
    ArrayList<Columns> columns = new ArrayList<>();
    HashMap<String, Columns> newTable = new HashMap<>();
    int counter = 0;
    int depth = 0;
    ArrayList<String> justNames = new ArrayList<>();
    HashMap<String, String> getType = new HashMap<>();
    HashMap<String, String> getBoth = new HashMap<>();


    public Table(String tablename, ArrayList<String> columnNames, ArrayList<String> types,
                 ArrayList<Columns> columns) {
        this.tablename = tablename;
        this.columnNames = columnNames;
        this.columns = columns;
        this.columnTypes = types;
        this.counter = this.columnNames.size();

        //split names from type again to create map that other functions can use
        for (int i = 0; i < counter; i++) {
            this.justNames.add((columnNames.get(i)).replaceAll(" .*", ""));

            //maps names to types so we can access correct type
            getType.put(justNames.get(i), columnTypes.get(i));

            //can get both (column headers) using name
            getBoth.put(justNames.get(i), columnNames.get(i));
        }


        //gets depth of column
        String temprColumnType = columnTypes.get(0);
        Columns temprColumn = columns.get(0);
        if (temprColumnType.equals("int")) {
            this.depth = temprColumn.integerColumn.size();
        } else if (temprColumnType.equals("float")) {
            this.depth = temprColumn.floatColumn.size();
        } else if (temprColumnType.equals("string")) {
            this.depth = temprColumn.stringColumn.size();
        }

        for (int i = 0; i < this.counter; i++) {
            this.newTable.put(this.justNames.get(i), this.columns.get(i));
        }
    }
}
