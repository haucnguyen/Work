package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/7/17.
 */
public class Selector {
    //ArrayList<Columns> columnsToUse = new ArrayList<>();
    ArrayList<Columns> columnsToUse;
    ArrayList<String> columnsInCommon;
    Table cartesianTable;
    ArrayList<Columns> cartColumns = new ArrayList<>();
    ArrayList<String> cartTypes = new ArrayList<>();
    Table table;
    ArrayList<Table> tablesToUse = new ArrayList<>();
    ArrayList<String> columnTypez = new ArrayList<>();
    ArrayList<String> columnBoth = new ArrayList<>();
    ArrayList<String> columnNamez;
    String tablename = "tempTable";
    Boolean inCommon = false;

    Boolean exists = true;

    ArrayList<String> columnList;

    Table tableToPrint;


    int tableDepth = 0;
    int tableWidth;
    int numTables;
    ArrayList<String> columnTypesA;
    ArrayList<String> columnTypesB;
    ArrayList<String> justNames;


    public Selector(ArrayList<String> columnNamesToUse,
                    ArrayList<Table> fromWhatTables, ArrayList<String> whereClauses) {
        columnsToUse = new ArrayList<>();
        columnBoth = new ArrayList<>();
        numTables = fromWhatTables.size();
        tableWidth = columnNamesToUse.size();
        columnNamez = columnNamesToUse;
        tablesToUse = fromWhatTables;
        cartTypes = new ArrayList<>();

        columnList = new ArrayList<>();
        columnTypesA = new ArrayList<>();
        columnTypesB = new ArrayList<>();
        justNames = new ArrayList<>();

        //check if columns requested exist in tables

        for (int i = 0; i < columnNamez.size(); i++) {
            ArrayList<Boolean> doesColumnExist = new ArrayList<>();
            String checkColumn = columnNamez.get(i);
            for (int x = 0; x < tablesToUse.size(); x++) {
                Table tempTable = tablesToUse.get(x);
                columnList = tempTable.justNames;
                if (columnList.contains(checkColumn)) {
                    doesColumnExist.add(true);
                } else {
                    doesColumnExist.add(false);
                }
            }
            if (!doesColumnExist.contains(true)) {
                exists = false;
                break;
            }
        }
        //gets column names
        for (int i = 0; i < columnNamez.size(); i++) {
            justNames.add((columnNamez.get(i)).replaceAll(" .*", ""));
        }
        //check for columns in common to set variable inCommon
        //also store columns in common to use for future
        if (tablesToUse.size() > 1) {
            Table a = tablesToUse.get(0);
            Table b = tablesToUse.get(1);
            for (int i = 0; i < columnNamez.size(); i++) {
                for (int x = 0; x < columnNamez.size(); x++) {
                    String tempName = columnNamez.get(x);
                    if (a.justNames.contains(tempName) && b.justNames.contains(tempName)) {
                        columnsInCommon.add(tempName);
                        inCommon = true;
                    }
                }
            }
            if (!inCommon) {
                for (int z = 0; z < columnNamez.size(); z++) {
                    String tempName = columnNamez.get(z);
                    if (a.justNames.contains(tempName)) {
                        columnTypesA.add(tempName);
                    } else if (b.justNames.contains(tempName)) {
                        columnTypesB.add(tempName);
                    }
                }
            }

        } else if (tablesToUse.size() == 1) {
            Table tablezz = tablesToUse.get(0);
            tableDepth = tablezz.depth;
            for (int i = 0; i < columnNamez.size(); i++) {
                columnsToUse.add(tablezz.newTable.get(columnNamez.get(i)));
                columnTypez.add(tablezz.getType.get(columnNamez.get(i)));
                columnBoth.add(tablezz.getBoth.get(columnNamez.get(i)));
            }
        }
    }

    //simple select that does not need join
    public Table simpleSelect() {
//        ArrayList<String> typesAvailable = table.columnTypes;
//        ArrayList<Columns> columnsAvailable = table.columns;
//        ArrayList<String> columnToMake = new ArrayList<>();
        //creates a table object for the data just loaded from .tbl file
        tableToPrint = new Table(tablename, columnBoth, columnTypez, columnsToUse);
        return tableToPrint;
    }


    public String join() {
        return "fuck";
    }

    public Table cartesian() {
        Table a = tablesToUse.get(0);
        Table b = tablesToUse.get(1);
        ArrayList<String> cartRow;
        int newDepth = a.depth * b.depth;
        //gets column types
        for (int d = 0; d < columnNamez.size(); d++) {
            String both = columnNamez.get(d);
            String justType = both.substring(both.lastIndexOf(" ") + 1);
            //check to make sure type is only the 3 accepted
            if (!justType.equals("int") && !justType.equals("string")
                    && !justType.equals("float")) {
                System.err.printf("ERROR: yo that's an incorrect type ya got there");
            }
            cartTypes.add(justType);
        }
        for (int i = 0; i < a.counter; i++) {
            cartRow = new ArrayList<>();
            String tempType = cartTypes.get(i);
            for (int x = 0; x < a.depth; x++) {
                int depthCounter = 0;
                if (tempType.equals("int")) {
                    while (depthCounter < b.depth) {
                        ArrayList<Integer> intColumn = a.newTable.get
                                (justNames.get(i)).integerColumn;
                        String newInt = Integer.toString(intColumn.get(x));
                        cartRow.add(newInt);
                        depthCounter++;
                    }
                } else if (tempType.equals("float")) {
                    while (depthCounter < b.depth) {
                        ArrayList<Float> floatColumn = a.newTable.get(justNames.get(i)).floatColumn;
                        String newFloat = Float.toString(floatColumn.get(x));
                        cartRow.add(newFloat);
                        depthCounter++;
                    }
                } else if (tempType.equals("string")) {
                    while (depthCounter < b.depth) {
                        ArrayList<String> stringColumn = a.newTable.get
                                (justNames.get(i)).stringColumn;
                        String newString = stringColumn.get(x);
                        cartRow.add(newString);
                        depthCounter++;
                    }
                }
            }
            Columns tempColumn = new Columns(tempType, cartRow);
            cartColumns.add(tempColumn);
            //now and cartseian product rows
        }
        for (int i = 0; i < b.counter; i++) {
            cartRow = new ArrayList<>();
            String tempType = cartTypes.get(i + a.counter);
            //System.out.println(tempType);
            for (int x = 0; x < b.counter; x++) {
                //System.out.println("where is this fucking up");
                if (tempType.equals("int")) {
                    ArrayList<Integer> integerColumn = b.newTable.get(justNames.get
                            (i + a.counter)).integerColumn;
                    //System.out.println(integerColumn + "oh boy");
                    while (cartRow.size() < newDepth) {
                        for (int m = 0; m < b.depth; m++) {
                            String newInt = Integer.toString(integerColumn.get(m));
                            cartRow.add(newInt);
                        }
                    }
                }
            }
            //System.out.println(cartRow);
//            System.out.println(cartRow);
            Columns tempColumn = new Columns(tempType, cartRow);
            cartColumns.add(tempColumn);
            //make new table
            //System.out.println(columnNamez);
            //System.out.println(cartColumns + " huh");
            //return table to be printed
        }
        cartesianTable = new Table(tablename, columnNamez, cartTypes, cartColumns);
        return cartesianTable;
    }
}
