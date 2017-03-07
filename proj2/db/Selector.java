package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/7/17.
 */
public class Selector {
    //ArrayList<Columns> columnsToUse = new ArrayList<>();
    ArrayList<Columns> columnsToUse;
    ArrayList<String> columnNames;
    ArrayList<String> inCommon;
    //Table table;
    ArrayList<String> typesToUse = new ArrayList<>();
    ArrayList<Table> tablesToUse = new ArrayList<>();
    ArrayList<String> columnTypes;
    ArrayList<String> columnBoth;
    String tablename = "tempTable";
    Boolean columnsInCommon;

    Boolean exists;


    int tableDepth;
    int tableWidth;
    int numTables;

    public Selector(ArrayList<String> columnNamesToUse,
                    ArrayList<Table> fromWhatTables, ArrayList<String> whereClauses) {
        columnsToUse = new ArrayList<>();
        columnTypes = new ArrayList<>();
        columnBoth = new ArrayList<>();
        //typesToUse = new ArrayList<>();
        numTables = fromWhatTables.size();
        tableWidth = columnNamesToUse.size();
        this.columnNames = columnNamesToUse;
        tablesToUse = fromWhatTables;

        inCommon = new ArrayList<>();

        //check if columns requested exist in tables
        ArrayList<Boolean> doesColumnExist = new ArrayList<>();
        for (int i = 0; i < columnNamesToUse.size(); i++) {
            String checkColumn = columnNames.get(i);
            for (int x = 0; x < tablesToUse.size(); x++) {
                Table table = tablesToUse.get(x);
                ArrayList<String> columnList = table.justNames;
                if (columnList.contains(checkColumn)) {
                    doesColumnExist.add(true);
                } else {
                    doesColumnExist.add(false);
                }
            } if (!doesColumnExist.contains(true)) {
                exists = false;
            }
        }

        //
    }

    //simple select that does not need join
    public Table simpleSelect() {
        Table table = tablesToUse.get(0);
        tableDepth = table.depth;
        ArrayList<String> typesAvailable = table.columnTypes;
        ArrayList<Columns> columnsAvailable = table.columns;
        ArrayList<String> columnToMake = new ArrayList<>();

        for (int i = 0; i < columnNames.size(); i ++) {
            columnsToUse.add(table.newTable.get(columnNames.get(i)));
            columnTypes.add(table.getType.get(columnNames.get(i)));
            columnBoth.add(table.getBoth.get(columnNames.get(i)));
        }
        //creates a table object for the data just loaded from .tbl file
        Table tableToPrint = new Table(tablename, columnBoth, columnTypes, columnsToUse);
        System.out.println("help");
        return tableToPrint;
    }


    public String join() {
        return "fuck";
    }

    public String cartesian() {

        return "fuck";
    }
}

 /*
        //adds column headers to row to print
        for (int i = 0; i < tableWidth; i++) {
            columnsToUse.add(tablez.newTable.get(columnNames.get(i)));
            typesToUse.add(tablez.getType.get(columnNames.get(i)));
            if (i == tableWidth - 1) {
                rowToPrint += tablez.columnNames.get(i);
                rowToPrint += "\n";
            } else {
                rowToPrint += tablez.columnNames.get(i) + ",";
            }
        }

        //adds everything else to row to print
        for (int i = 0; i < tableDepth; i++) {
            for (int x = 0; x < columnNames.size(); x++) {
                //if column to
                String tempColumnType = typesToUse.get(x);
                Columns tempColumn = tablez.columns.get(x);
                if (x == tableWidth - 1) {
                    if (tempColumnType.equals("int")) {
                        rowToPrint += Integer.toString(tempColumn.integerColumn.get(i)) + "\n";
                        //System.out.print(tempColumn.integerColumn.get(i));
                    } else if (tempColumnType.equals("float")) {
                        rowToPrint += Float.toString(tempColumn.floatColumn.get(i)) + "\n";
                        //System.out.print(tempColumn.floatColumn.get(i));
                    } else if (tempColumnType.equals("string")) {
                        rowToPrint += tempColumn.integerColumn.get(i) + "\n";
                        //System.out.print(tempColumn.stringColumn.get(i));
                    }
                } else {
                    if (tempColumnType.equals("int")) {
                        rowToPrint += Integer.toString(tempColumn.integerColumn.get(i)) + ",";
                        //System.out.print(tempColumn.integerColumn.get(i) + ",");
                    } else if (tempColumnType.equals("float")) {
                        rowToPrint += Float.toString(tempCoslumn.floatColumn.get(i)) + ",";
                        //System.out.print(tempColumn.floatColumn.get(i) + ",");
                    } else if (tempColumnType.equals("string")) {
                        rowToPrint += tempColumn.stringColumn.get(i) + ",";
                        //System.out.print(tempColumn.stringColumn.get(i) + ",");
                    }
                }
            }
        }
        return rowToPrint;*/

