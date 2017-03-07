package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/7/17.
 */
public class Insert {
    ArrayList<String> columnTypes;
    ArrayList<Columns> theColumns;
    int tableWidth = 0;

    private Insert(Table table, ArrayList<String> values) {
        columnTypes = table.columnTypes;
        theColumns = table.columns;
        tableWidth = table.counter;

        for (int i = 0; i < tableWidth; i++) {
            String tempType = columnTypes.get(i);
            String tempValue = values.get(i);
            if (tempType.equals("int")) {
                try {
                    Integer.parseInt(tempValue);
                } catch (NumberFormatException e) {
                    throw new Error("yo this shit ain't an integer");
                }
                theColumns.get(i).integerColumn.add(Integer.parseInt(tempValue));
            } else if (tempType.equals("float")) {
                try {
                    Float.parseFloat(tempValue);
                } catch (NumberFormatException e) {
                    throw new Error("yo this shit ain't an float");
                }
                theColumns.get(i).floatColumn.add(Float.parseFloat(tempValue));
            } else if (tempType.equals("string")) {
                if (!(tempValue instanceof String)) {
                    throw new Error("yo this shit ain't an string");
                }
                theColumns.get(i).stringColumn.add(tempValue);
            }
        }
    }
}
