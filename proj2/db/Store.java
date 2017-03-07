package db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hau on 3/7/17.
 */
public class Store {
    ArrayList<String> columnHeaders;
    ArrayList<Columns> columns;
    ArrayList<String> columnType;
    String tablename;
    String filename;
    int tableDepth;
    int tableWidth;

    private Store(Table table) throws IOException {
        this.columnHeaders = table.columnNames;
        this.columnType = table.columnTypes;
        this.columns = table.columns;
        this.tablename = table.tablename;
        this.tableDepth = table.depth;
        this.tableWidth = table.counter;
        this.filename = "db/Tables/" + this.tablename + ".tbl";

        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write(String.join(",", columnHeaders));
        bufferedWriter.newLine();

        for (int i = 0; i < tableDepth; i++) {
            ArrayList<String> rowToPrint = new ArrayList<>();
            for (int x = 0; x < tableWidth; x++) {
                String tempColumnType = this.columnType.get(x);
                Columns tempColumn = this.columns.get(x);
                if (tempColumnType.equals("int")) {
                    String input = Integer.toString(tempColumn.integerColumn.get(i));
                    rowToPrint.add(input);
                } else if (tempColumnType.equals("float")) {
                    String input = Float.toString(tempColumn.floatColumn.get(i));
                    rowToPrint.add(input);
                } else if (tempColumnType.equals("string")) {
                    String input = tempColumn.stringColumn.get(i);
                    rowToPrint.add(input);
                }
            }
            bufferedWriter.write(String.join(",", rowToPrint));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
