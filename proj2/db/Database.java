package db;

import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Database {
    HashMap<String, Table> databaseOfTables;
    public Database() {
        databaseOfTables = new HashMap<>();
        //hashmap all the tables to their corresponding strings

    }


    public Table getTable(String tablename) {
        return databaseOfTables.get(tablename);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Database database = (Database) o;
        return databaseOfTables != null
                ? databaseOfTables.equals(database.databaseOfTables)
                : database.databaseOfTables == null;
    }

    @Override
    public int hashCode() {
        return databaseOfTables != null ? databaseOfTables.hashCode() : 0;
    }

    public String dropTable(String tablename) {
        boolean allLetters = tablename.matches("[a-zA-Z0-9]*");
        if (!allLetters) {
            return "ERROR: malformed table name";
        }
        /*if (!databaseOfTables.containsKey(tablename)) {
            System.err.printf("ERROR: yo man you can't drop a table that don't exist");
        }*/
        if (getTable(tablename) == null) {
            return "ERROR: yo man u can't drop a table that doesn't exist";
        }
        databaseOfTables.remove(tablename);
        return "";
    }

    public String insertInto(String tablename, ArrayList<String> values) {
        //System.out.println("tablename " + tablename + "Capatain America");
        //System.out.println("table exists " + tablename.equals("records"));
        Table table = getTable(tablename);
        if (table == null) {
            return "ERROR: yo man u can't insert into a table that don't exist";
        }
        ArrayList<String> columnType = table.columnTypes;
        //System.out.println(table.columnTypes);
        // ArrayList<String> columnTypes = table.columnTypes;
        ArrayList<Columns> theColumns = new ArrayList<>();
        int tableWidth = 0;
        int tableDepth = 0;

        //table.depth = table.depth + 1;
        //System.out.println(tableWidth);

        //columnTypes = table.columnTypes;

        theColumns = table.columns;

        tableWidth = table.counter;

        tableDepth = table.depth + 1;
        //System.out.println(tableWidth);

        //checks to make sure row is same width as column
        if (values.size() != tableWidth) {
            throw new Error("ERROR: yo man that row you tryin to insert ain't the right size");
        }

        for (int i = 0; i < tableWidth; i++) {
            String tempType = columnType.get(i);
            String tempValue = values.get(i);
            //System.out.println(tempValue);
            //System.out.println(tempType);
            if (tempType.equals("int")) {
                try {
                    Integer.parseInt(tempValue);
                } catch (NumberFormatException e) {
                    return "ERROR: yo this shit ain't an integer";
                }
                theColumns.get(i).integerColumn.add(Integer.parseInt(tempValue));
                //System.out.println(theColumns.get(i).integerColumn);
            } else if (tempType.equals("float")) {
                try {
                    Float.parseFloat(tempValue);
                } catch (NumberFormatException e) {
                    return "ERROR: yo this shit ain't an float";
                }
                theColumns.get(i).floatColumn.add(Float.parseFloat(tempValue));
                //System.out.println(theColumns.get(i).floatColumn);

            } else if (tempType.equals("string")) {
                if (!(tempValue instanceof String)) {
                    return "ERROR: yo this shit ain't an string";
                }
                theColumns.get(i).stringColumn.add(tempValue);
                //System.out.println(theColumns.get(i).stringColumn);
            } else {
                return "ERROR: why is this not fucking working";
            }
        }
        //print(tablename);
        Table newTable = new Table(tablename, table.columnNames, columnType, theColumns);
        //print(tablename);
        databaseOfTables.put(tablename, newTable);
        System.out.println("WHY AREN'T YOU WORKING");
        return "";
    }

    public String store(String tablename) {
        try {
            Table table = getTable(tablename);
            ArrayList<String> columnHeaders = table.columnNames;
            ArrayList<String> columnType = table.columnTypes;
            ArrayList<Columns> columns = table.columns;
            int tableDepth = table.depth;
            int tableWidth = table.counter;
            String filename = "db/Tables/" + tablename + ".tbl";

            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(String.join(",", columnHeaders));
            bufferedWriter.newLine();

            for (int i = 0; i < tableDepth; i++) {
                ArrayList<String> rowToAdd = new ArrayList<>();
                for (int x = 0; x < tableWidth; x++) {
                    String tempColumnType = columnType.get(x);
                    Columns tempColumn = columns.get(x);
                    if (tempColumnType.equals("int")) {
                        String input = Integer.toString(tempColumn.integerColumn.get(i));
                        rowToAdd.add(input);
                    } else if (tempColumnType.equals("float")) {
                        String input = Float.toString(tempColumn.floatColumn.get(i));
                        rowToAdd.add(input);
                    } else if (tempColumnType.equals("string")) {
                        String input = tempColumn.stringColumn.get(i);
                        rowToAdd.add(input);
                    } else {
                        return "ERROR: wrong type";
                    }
                }
                bufferedWriter.write(String.join(",", rowToAdd));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("God fucking damn it");
        }
        return "";
    }

    public String print(String tablename) {
        //System.out.println("potato");
        Table table = getTable(tablename);
        if (table == null) {
            return "ERROR: yo man u can't print a table that don't exist";
        }
        ArrayList<String> columnType;
        columnType = table.columnTypes;
        String columnCategoryString = String.join(",", table.columnNames);
        //System.out.println(columnCategoryString);
        //ArrayList<String> stringsToPrint = new ArrayList<>();
        String rowToPrint = "";
        rowToPrint += columnCategoryString + "\n";
        //get depth of the table
        //int tableDepth = 0;
        int tableDepth = table.depth;
        int tableWidth = table.counter;

        for (int i = 0; i < tableDepth; i++) {
            for (int x = 0; x < table.counter; x++) {
                String tempColumnType = columnType.get(x);
                Columns tempColumn = table.columns.get(x);
                if (x == tableWidth - 1) {
                    if (tempColumnType.equals("int")) {
                        rowToPrint += Integer.toString(tempColumn.integerColumn.get(i)) + "\n";
                        //System.out.print(tempColumn.integerColumn.get(i));
                    } else if (tempColumnType.equals("float")) {
                        rowToPrint += Float.toString(tempColumn.floatColumn.get(i)) + "\n";
                        //System.out.print(tempColumn.floatColumn.get(i));
                    } else if (tempColumnType.equals("string")) {
                        rowToPrint += tempColumn.stringColumn.get(i) + "\n";
                        //System.out.print(tempColumn.stringColumn.get(i));
                    }
                } else {
                    if (tempColumnType.equals("int")) {
                        rowToPrint += Integer.toString(tempColumn.integerColumn.get(i)) + ",";
                        //System.out.print(tempColumn.integerColumn.get(i) + ",");
                    } else if (tempColumnType.equals("float")) {
                        rowToPrint += Float.toString(tempColumn.floatColumn.get(i)) + ",";
                        //System.out.print(tempColumn.floatColumn.get(i) + ",");
                    } else if (tempColumnType.equals("string")) {
                        rowToPrint += tempColumn.stringColumn.get(i) + ",";
                        //System.out.print(tempColumn.stringColumn.get(i) + ",");
                    }
                }
            }
            //stringsToPrint.add(rowToPrint);
            //System.out.println();
        }
        //System.out.println(rowToPrint);
        return rowToPrint;
    }

    public String load(String tablename) {
        boolean allLetters = tablename.matches("[a-zA-Z0-9]*");
        if (!allLetters) {
            return "ERROR: malformed table name";
        }
        ArrayList<String> columnBoth;
        ArrayList<String> columnNames = new ArrayList<>();
        ArrayList<String> columnTypes = new ArrayList<>();
        ArrayList<String> theRows = new ArrayList<>();
        ArrayList<Columns> columnDataLists = new ArrayList<>();
        int numColumns = 0;
        int numRows = 0;
        Table newTable;
        String filename = ("examples/" + tablename + ".tbl");
        HashMap<String, String> columnMap = new HashMap<>();

        //figure out the column situation
//        if (databaseOfTables.containsKey(tablename)) {
//            return "ERROR: yo man can't adfasdf"
//        }

        try {
            BufferedReader columnReader = new BufferedReader(new FileReader(filename));
            ArrayList<String> initialColumns = new ArrayList<>();

            initialColumns.add(columnReader.readLine());

            String columnString = initialColumns.get(0);
            columnBoth = new ArrayList<>(Arrays.asList(columnString.trim().split("\\s*,\\s*")));
            numColumns = columnBoth.size();

            //figure out the row situation
            String line;
            while ((line = columnReader.readLine()) != null) {
                theRows.add(line);
                numRows++;
            }
        } catch (IOException e) {
            return "ERROR: that file don't exist man";
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
        databaseOfTables.put(tablename, newTable);
        //System.out.println("bitch did you put it in");
        System.out.println(databaseOfTables.containsKey(tablename)
                + " " + tablename + " " + newTable);
        //System.out.println(databaseOfTables.get("records"));
        return "";
    }

    //does not handle "where" clauses yet, simple select where there's only 1 table
    /*public String select(ArrayList<String> columnNamesToUse,
                         ArrayList<String> fromWhatTables, ArrayList<String> whereClauses) {
        ArrayList<Table> fromTables = new ArrayList<>();
        for (int i = 0; i < fromWhatTables.size(); i++) {
            fromTables.add(getTable(fromWhatTables.get(i)));
        }
        Selector pleaseSelect = new Selector(columnNamesToUse, fromTables, whereClauses);
        return print(pleaseSelect.select().tablename);
    }*/

    public static final String REST = "\\s*(.*)\\s*",
            COMMA = "\\s*,\\s*",
            AND = "\\s+and\\s+";

    // Stage 1 syntax, contains the command name.
    public static final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
            LOAD_CMD = Pattern.compile("load " + REST),
            STORE_CMD = Pattern.compile("store " + REST),
            DROP_CMD = Pattern.compile("drop table " + REST),
            INSERT_CMD = Pattern.compile("insert into " + REST),
            PRINT_CMD = Pattern.compile("print " + REST),
            SELECT_CMD = Pattern.compile("select " + REST);

    // Stage 2 syntax, contains the clauses of commands.
    public static final Pattern CREATE_NEW = Pattern.compile("(\\S+)\\s+\\((\\S+\\s+\\S+\\s*"
            + "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
            SELECT_CLS = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+"
                    + "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+"
                    + "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+"
                    + "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
            CREATE_SEL = Pattern.compile("(\\S+)\\s+as select\\s+"
                    + SELECT_CLS.pattern()),
            INSERT_CLS = Pattern.compile("(\\S+)\\s+values\\s+(.+?"
                    + "\\s*(?:,\\s*.+?\\s*)*)");


    public String[] clauseSelect(String line) {
        Pattern p = Pattern.compile("([A-Za-z]\\w*)\\s*(<=|>=|>|<|==|!=)\\s*(\'*\\w+\'*)");
        Matcher m;
        if ((m = p.matcher(line)).matches()) {
            String[] s = {m.group(1), m.group(2), m.group(3)};
            return s;
        } else {
            return null;
        }
    }

    public String[] asSelect(String line) {
        Pattern p = Pattern.compile
                ("([A-Za-z]\\w*)\\s*(\\*|\\/|\\+|\\-)\\s*(\\w+)\\s*[\"as\"]\\w+\\s*(\\w+)");
        Matcher m;
        if ((m = p.matcher(line)).matches()) {
            String[] s = {m.group(1), m.group(2), m.group(3), m.group(4)};
            return s;
        } else {
            return null;
        }
    }

    public String transact(String query) {
        Matcher m;
        int index = 0;
        int i = 0;
        while (i < query.length()) {
            if (query.charAt(i) == ' ') {
                index++;
            } else if (query.charAt(i) != ' ') {
                break;
            }
            i++;
        }
        query = query.substring(index, query.length());
        if ((m = CREATE_CMD.matcher(query)).matches()) {
            //createTable(m.group(1));
            return "not yet man";
        } else if ((m = LOAD_CMD.matcher(query)).matches()) {
            return load(m.group(1));
        } else if ((m = STORE_CMD.matcher(query)).matches()) {
            return store(m.group(1));
        } else if ((m = DROP_CMD.matcher(query)).matches()) {
            return dropTable(m.group(1));
        } else if ((m = INSERT_CMD.matcher(query)).matches()) {
            Matcher l = INSERT_CLS.matcher(m.group(1));
            if (!l.matches()) {
                System.err.printf("Malformed insert: %s\n", m.group(1));
            }

            ArrayList<String> values = new ArrayList<>();
            String[] splitThemUp = m.group().split("insert into |values ");
            String tableName = splitThemUp[0];
            String unsplitValues = splitThemUp[1];
            String[] value = unsplitValues.split(",");
            //String[] value = unsplitValues.split("\\s+(.+?\\s*(?:,\\s*.+?\\s*)*)");
            for (int a = 0; a < value.length; a++) {
                values.add(value[a].trim());
            }
            for (String s : values) {
                System.out.println(s);
            }
            return insertInto(tableName.trim(), values);

        } else if ((m = PRINT_CMD.matcher(query)).matches()) {
            return print(m.group(1).trim());
        } else if ((m = SELECT_CMD.matcher(query)).matches()) {
            return "not yet man";
            //select(m.group(1));
        } else {
            System.err.printf("Malformed query: %s\n", query);
        }
        //readThatShit.eval(query);
        return "fuck";
    }


    /*private void select(String expr) {
        Matcher m = SELECT_CLS.matcher(expr);
        if (!m.matches()) {
            System.err.printf("Malformed select: %s\n", expr);
            return;
        }

        select(m.group(1), m.group(2), m.group(3));
    }

    private void select(String exprs, String tables, String conds) {
        ArrayList<String> columnNamesToUse = new ArrayList<>();
        ArrayList<String> fromWhatTables = new ArrayList<>();
        ArrayList<String> whereClauses = new ArrayList<>();
        String[] firstSplit = exprs.trim().split("\\s*,\\s*");
        for (String s : firstSplit) {
            if (s.contains(" as ")) {
                String[] a = asSelect(s);
                for (String b : a) {
                    columnNamesToUse.add(b.trim());
                }
            } else {
                columnNamesToUse.add(s.trim());
            }
        }
        for (String sadas : columnNamesToUse) {
            System.out.println(sadas);
        }
        String[] secondSplit = tables.split("\\s*,\\s*");
        for (String c : secondSplit) {
            fromWhatTables.add(c.trim());
        }
        for (String sadasd : fromWhatTables) {
            System.out.println(sadasd);
        }
        if (conds != null) {
            String[] thirdSplit = conds.split("\\s*and\\s*");
            for (String s : thirdSplit) {
                String[] a = clauseSelect(s);
                for (String b : a) {
                    whereClauses.add(b);
                }
            }
            for (String as : whereClauses) {
                System.out.println(as);
            }
        }
      select(columnNamesToUse, fromWhatTables, whereClauses);
    }

    private void createTable(String expr) {
        Matcher m;
        if ((m = CREATE_NEW.matcher(expr)).matches()) {
            createNewTable(m.group(1), m.group(2).split(COMMA));
        } else if ((m = CREATE_SEL.matcher(expr)).matches()) {
            createSelectedTable(m.group(1), m.group(2), m.group(3), m.group(4));
        } else {
            System.err.printf("Malformed create: %s\n", expr);
        }
    }

    private void createNewTable(String name, String[] cols) {
        ArrayList<String> columnHeaders = new ArrayList<String>();
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < cols.length - 1; i++) {
            joiner.add(cols[i]);
        }
        for (String s : cols) {
            columnHeaders.add(s);
        }
        newTable(name, columnHeaders);
        String colSentence = joiner.toString() + " and " + cols[cols.length - 1];
        System.out.printf("You are trying to create a table named
        %s with the columns %s\n", name, colSentence);
    }*/

    private void createSelectedTable(String name, String exprs, String tables, String conds) {
        System.out.printf("You are trying to create a table named "
                + "%s by selecting these expressions:"
                + " '%s' from the join of these tables: '%s', filtered "
                + "by these conditions: '%s'\n", name, exprs, tables, conds);
    }

    private void insertRow(String expr) {
        Matcher m = INSERT_CLS.matcher(expr);
        if (!m.matches()) {
            System.err.printf("Malformed insert: %s\n", expr);
            return;
        }

        ArrayList<String> values = new ArrayList<>();
        String[] splitThemUp = expr.split("insert into |values ");
        String tableName = splitThemUp[0];
        String unsplitValues = splitThemUp[1];
        String[] value = unsplitValues.split(",");
        //String[] value = unsplitValues.split("\\s+(.+?\\s*(?:,\\s*.+?\\s*)*)");
        for (int i = 0; i < value.length; i++) {
            values.add(value[i].trim());
        }
        for (String s : values) {
            System.out.println(s);
        }
        insertInto(tableName.trim(), values);
        //System.out.printf("You are trying to insert the
        // row \"%s\" into the table %s\n", m.group(2), m.group(1));
    }
}
