package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/5/17.
 */
public class Columns {
    ArrayList<String> stringColumn;
    ArrayList<Integer> integerColumn;
    ArrayList<Float> floatColumn;

    public Columns(String columnType, ArrayList<String> columnData) {
        //this.theColumn = new ArrayList<>();
        if (columnType.equals("int")) {
            this.integerColumn = new ArrayList<>();
            for (int i = 0; i < columnData.size(); i++) {
                int input = Integer.parseInt(columnData.get(i));
                integerColumn.add(input);
            }
        } else if (columnType.equals("float")) {
            this.floatColumn = new ArrayList<>();
            for (int i = 0; i < columnData.size(); i++) {
                float input = Float.parseFloat(columnData.get(i));
                floatColumn.add(input);
            }
        } else if (columnType.equals("string")) {
            this.stringColumn = new ArrayList<>();
            for (int i = 0; i < columnData.size(); i++) {
                String input = columnData.get(i);
                stringColumn.add(input);
            }
        } else {
            throw new IllegalArgumentException("Damn you fucked up");
        }
    }

}
