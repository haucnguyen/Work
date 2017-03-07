package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/5/17.
 */
public class Columns {
    ArrayList<String> stringColumn = new ArrayList<>();
    ArrayList<Integer> integerColumn = new ArrayList<>();
    ArrayList<Float> floatColumn = new ArrayList<>();

    public Columns(String columnType, ArrayList<String> columnData) {
        //this.theColumn = new ArrayList<>();
        if (columnType.equals("int")) {
            //this.integerColumn = new ArrayList<>();
            if (columnData.isEmpty()) {
                this.integerColumn = new ArrayList<>();
            } else if (!columnData.isEmpty()) {
                for (int i = 0; i < columnData.size(); i++) {
                    int input;
                    input = Integer.parseInt(columnData.get(i));
                    integerColumn.add(input);
                }
            }
        } else if (columnType.equals("float")) {
            //this.floatColumn = new ArrayList<>();
            if (columnData.isEmpty()) {
                this.floatColumn = new ArrayList<>();
            } else if (!columnData.isEmpty()) {
                for (int i = 0; i < columnData.size(); i++) {
                    float input;
                    input = Float.parseFloat(columnData.get(i));
                    input = Float.parseFloat(String.format("%.2f", input));
                    floatColumn.add(input);
                }
            }
        } else if (columnType.equals("string")) {
            //this.stringColumn = new ArrayList<>();
            if (columnData.isEmpty()) {
                this.stringColumn = new ArrayList<>();
            } else if (!columnData.isEmpty()) {
                for (int i = 0; i < columnData.size(); i++) {
                    String input = columnData.get(i);
                    stringColumn.add(input);
                }
            }
        } else {
            System.err.printf("i dont even fucking know man you should not have made it here");
        }
    }

}
