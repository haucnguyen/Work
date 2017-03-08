package db;

import java.util.ArrayList;

/**
 * Created by Hau on 3/7/17.
 */
public class Selector {
    //ArrayList<Columns> columnsToUse = new ArrayList<>();
    ArrayList<Columns> columnsToUse;
    ArrayList<String> columnNames;
    ArrayList<String> columnsInCommon;
    Table table;
    ArrayList<String> typesToUse = new ArrayList<>();
    ArrayList<Table> tablesToUse = new ArrayList<>();
    ArrayList<String> columnTypes;
    ArrayList<String> columnBoth;
    ArrayList<String> columnNamez;
    String tablename = "tempTable";
    Boolean inCommon = false;

    Boolean exists = true;

    Table tableToPrint;


    int tableDepth = 0;
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
        columnNamez = columnNamesToUse;
        tablesToUse = fromWhatTables;

        ArrayList<String> columnList;

        //check if columns requested exist in tables
        for (int i = 0; i < columnNamesToUse.size(); i++) {
            ArrayList<Boolean> doesColumnExist = new ArrayList<>();
            String checkColumn = columnNamez.get(i);
            for (int x = 0; x < tablesToUse.size(); x++) {
                table = tablesToUse.get(x);
                columnList = table.justNames;
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


        //check for columns in common to set variable inCommon
        //also store columns in common to use for future
        if (tablesToUse.size() > 1) {
            for (int i = 0; i < columnNamez.size(); i++) {
                Table a = tablesToUse.get(0);
                Table b = tablesToUse.get(1);
                for (int x = 0; x < columnNamez.size(); x++) {
                    String tempName = columnNamez.get(x);
                    if (a.justNames.contains(tempName) && b.justNames.contains(tempName)) {
                        columnsInCommon.add(tempName);
                        inCommon = true;
                    }
                }
            }
        }
    }

    //simple select that does not need join
    public Table simpleSelect() {
        Table tablezz = tablesToUse.get(0);
        tableDepth = tablezz.depth;
//        ArrayList<String> typesAvailable = table.columnTypes;
//        ArrayList<Columns> columnsAvailable = table.columns;
//        ArrayList<String> columnToMake = new ArrayList<>();

        for (int i = 0; i < columnNamez.size(); i++) {
            columnsToUse.add(tablezz.newTable.get(columnNamez.get(i)));
            columnTypes.add(tablezz.getType.get(columnNamez.get(i)));
            columnBoth.add(tablezz.getBoth.get(columnNamez.get(i)));
        }
        if (columnNamez.contains("+")) {
            if (columnTypes.equals("int")) {

            }
        }
        else if (columnNamez.contains("-")) {

        }
        else if (columnNamez.contains("*")) {

        }
        else if (columnNamez.contains("/")) {

        }
        //creates a table object for the data just loaded from .tbl file
        tableToPrint = new Table(tablename, columnBoth, columnTypes, columnsToUse);
        return tableToPrint;
    }


    public String join() {
        return "fuck";
    }

    public String cartesian() {
        Table a = tablesToUse.get(0);
        Table b = tablesToUse.get(1);
        for (int i = 0; i < a.depth; i++) {
            System.out.println("eh");
        }
        return "fuck";
    }
}