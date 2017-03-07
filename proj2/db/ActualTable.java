package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hau on 3/6/17.
 */
public class ActualTable<T> {
    HashMap<String, List<T>> aTable;
    int tableColumns;
    int tableRows;
    String name;
    ArrayList<String> keys = new ArrayList<>();
    List<List<T>> values = new ArrayList<>();
    ArrayList<String> columnNames;
    ArrayList<String> columnToPrint;
    ArrayList<String> rowsToPrint = new ArrayList<>(); //will be useful in future

    //constructor for table that already exists as a .tbl file
    public ActualTable(TBLReader<T> rawTable) {
        name = rawTable.tablename;
        aTable = new HashMap<>();
        tableColumns = rawTable.numColumns;
        tableRows = rawTable.numRows;
        columnNames = rawTable.columnNames;
        columnToPrint = rawTable.columnBoth;
        rowsToPrint = rawTable.theRows;

        for (int i = 0; i < tableColumns; i++) {
            String tempKey = rawTable.columnNames.get(i);
            List<T> tempValue = rawTable.theColumns.get(i);

            keys.add(tempKey);
            values.add(tempValue);
            aTable.put(tempKey, tempValue);
        }
        //System.out.println(values);
        //System.out.println(keys);
        System.out.println("is this even working");
    }

    //constructor for table that was created using create table method (not a .tbl file)
    //columns is the list<list<T>> but post clause
    public ActualTable(String tablename, ArrayList<String> columnHeaders, ArrayList<ArrayList<T>> columns) {
        name = tablename;
        columnToPrint = columnHeaders;
        tableColumns = columnHeaders.size();
        tableRows = (columns.get(0).size());
        //rowsToPrint;
        aTable = new HashMap<>();
        for (int i = 0; i < tableColumns; i++) {
            aTable.put(columnHeaders.get(i), columns.get(i));
        }

        System.out.println(aTable.get(columnHeaders.get(2)));
        //create the rows
        for (int i = 0; i < tableColumns; i++) {
            List aColumn = columns.get(i);
            //System.out.println(aColumn);
            ArrayList<String> rowData = new ArrayList<>();
            for (int x = 0; x < tableRows; x++) {
                rowData.add((String) aColumn.get(x)); //casting comes with riskkkkk
            }
            String listString = String.join(",", rowData);

            rowsToPrint.add(listString);
        }
    }


    public void getKeys() {
        for (int i = 0; i < tableColumns; i++) {
            String tempKey = keys.get(i);
            System.out.println(tempKey);
            //System.out.print(aTable.get(tempKey));
        }
    }

    public void getValues() {
        for (int i = 0; i < tableColumns; i++) {
            List<T> tempValue = values.get(i);
            System.out.println(tempValue);
        }
    }

    public List<T> getThatShit(String column) {
        return aTable.get(column);
    }


    @Override
    public int hashCode() {
        int result = tableColumns;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

