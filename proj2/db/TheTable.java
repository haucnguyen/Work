package db;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hau on 3/3/17.
 */
public class TheTable<T> {

    private String create() {
        return "running create";
    }

    private String load(String tablename, Database database) throws IOException {
        Load loader = new Load(tablename, database);
        return "";
    }

    /*public String store(Table table) throws IOException {
        ArrayList<String> columnHeaders = table.columnNames;
        ArrayList<String> columnType = table.columnTypes;
        ArrayList<Columns> columns = table.columns;
        String tablename = table.tablename;
        int tableDepth = table.depth;
        int tableWidth = table.counter;
        String filename = "db/Tables/" + tablename + ".tbl";

        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write(String.join(",", columnHeaders));
        bufferedWriter.newLine();

        for (int i = 0; i < tableDepth; i++) {
            ArrayList<String> rowToPrint = new ArrayList<>();
            for (int x = 0; x < tableWidth; x++) {
                String tempColumnType = columnType.get(x);
                Columns tempColumn = columns.get(x);
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
        return "";
    }*/


    /*public String insert(Table table, ArrayList<String> values) {
        ArrayList<String> columnTypes;
        ArrayList<Columns> theColumns;
        int tableWidth = 0;

        columnTypes = table.columnTypes;
        theColumns = table.columns;
        tableWidth = table.counter;

        for (int i = 0; i < tableWidth; i++) {
            String tempType = columnTypes.get(i);
            String tempValue = values.get(i);
            if (tempType.equals("int")) {
                try {
                    Integer.parseInt(tempValue);
                } catch(NumberFormatException e) {
                    throw new Error("yo this shit ain't an integer");
                }
                theColumns.get(i).integerColumn.add(Integer.parseInt(tempValue));
            } else if (tempType.equals("float")) {
                try {
                    Float.parseFloat(tempValue);
                } catch(NumberFormatException e) {
                    throw new Error("yo this shit ain't an float");
                }
                theColumns.get(i).floatColumn.add(Float.parseFloat(tempValue));
            } else if (tempType.equals("string")) {
                if (!(tempValue instanceof String)) {
                    throw new Error("yo this shit ain't an string");
                }
                theColumns.get(i).stringColumn.add(tempValue);
            }
        } return "";
    }*/

    /*public String print(Table table) {
        ArrayList<String> columnType;
        columnType = table.columnTypes;
        String columnCategoryString = String.join(",", table.columnNames);
        System.out.println(columnCategoryString);

        //get depth of the table
        int tableDepth = 0;
        String temprColumnType = columnType.get(0);
        Columns temprColumn = table.columns.get(0);
        if (temprColumnType.equals("int")) {
            tableDepth = temprColumn.integerColumn.size();
        } else if (temprColumnType.equals("float")) {
            tableDepth = temprColumn.floatColumn.size();
        } else if (temprColumnType.equals("string")) {
            tableDepth = temprColumn.stringColumn.size();
        }

        for (int i = 0; i < tableDepth; i++) {
            for (int x = 0; x < table.counter; x++) {
                String tempColumnType = columnType.get(x);
                Columns tempColumn = table.columns.get(x);
                if (x == table.counter - 1) {
                    if (tempColumnType.equals("int")) {
                        System.out.print(tempColumn.integerColumn.get(i));
                    } else if (tempColumnType.equals("float")) {
                        System.out.print(tempColumn.floatColumn.get(i));
                    } else if (tempColumnType.equals("string")) {
                        System.out.print(tempColumn.stringColumn.get(i));
                    }
                } else {
                    if (tempColumnType.equals("int")) {
                        System.out.print(tempColumn.integerColumn.get(i) + ",");
                    } else if (tempColumnType.equals("float")) {
                        System.out.print(tempColumn.floatColumn.get(i) + ",");
                    } else if (tempColumnType.equals("string")) {
                        System.out.print(tempColumn.stringColumn.get(i) + ",");
                    }
                }
            }
            System.out.println();
        }
        return "";
    }*/

    private String select(String query) {
        return "running select";
    }
}
