package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Hau on 3/6/17.
 */
public class Load {
    String tablename;
    String filename;
    ArrayList<String> columnBoth;
    ArrayList<String> columnNames = new ArrayList<>();
    ArrayList<String> columnTypes = new ArrayList<>();
    ArrayList<String> theRows = new ArrayList<>();
    ArrayList<Columns> columnDataLists = new ArrayList<>();
    int numColumns = 0;
    int numRows = 0;
    Table newTable;

    protected Load(String tablename, Database database) throws IOException {
        this.tablename = tablename;
        this.filename = ("examples/" + tablename + ".tbl");

        //figure out the column situation
        BufferedReader columnReader = new BufferedReader(new FileReader(filename));
        ArrayList<String> initialColumns = new ArrayList<>();

        initialColumns.add(columnReader.readLine());

        String columnString = initialColumns.get(0);
        columnBoth = new ArrayList<>(Arrays.asList(columnString.trim().split("\\s*,\\s*")));
        numColumns = columnBoth.size();

        //figure out the row situation
        BufferedReader rowReader = new BufferedReader(new FileReader(filename));
        rowReader.readLine(); //ignore this line, it's column names
        String line;
        while ((line = rowReader.readLine()) != null) {
            theRows.add(line);
            numRows++;
        }

        //gets column names
        for (int i = 0; i < numColumns; i++) {
            columnNames.add((columnBoth.get(i)).replaceAll(" .*", ""));
        }

        //gets column types
        for (int i = 0; i < numColumns; i++) {
            String both = columnBoth.get(i);
            columnTypes.add(both.substring(both.lastIndexOf(" ") + 1));
        }

        //puts shit in le columns
        for (int i = 0; i < numColumns; i++) {
            ArrayList<String> tempList = new ArrayList<>();
            String tempColumnType = columnTypes.get(i);

            for (int x = 0; x < numRows; x++) {
                String stringRow = theRows.get(x);
                String[] theRow = stringRow.split(",");
                tempList.add(theRow[i]);
            }

            Columns newColumn = new Columns(tempColumnType, tempList);
            columnDataLists.add(newColumn);
        }
        //creates a table object for the data just loaded from .tbl file
        newTable = new Table(tablename, columnBoth, columnTypes, columnDataLists);
        //database.newTable(tablename, newTable);
    }
}
