package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/5/17.
 */
public class Print {
    ArrayList<String> columnType;

    public Print(Table table) {
        this.columnType = table.columnTypes;

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
    }
}
