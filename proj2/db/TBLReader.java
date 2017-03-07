package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hau on 3/3/17.
 */
public class TBLReader<T> {
    protected ArrayList<String> wholeTable = new ArrayList<>(); //string form of wholetable
    ArrayList<String> columnNames = new ArrayList<>(); //just column names
    ArrayList<String> columnTypes = new ArrayList<>(); //just column types
    ArrayList<String> columnBoth; //column names and types
    ArrayList<String> theRows = new ArrayList<>(); //string forms of the rows
    List<List<T>> theColumns; //list of column lists
    int numColumns;
    int numRows = 0;
    String filename;
    String tablename;

    public TBLReader(String tablename) throws IOException {
        this.tablename = tablename;
        filename = ("examples/" + tablename + ".tbl");
        //figure out the column situation
        BufferedReader columnReader = new BufferedReader(new FileReader(filename));
        ArrayList<String> initialColumns = new ArrayList<>();

        initialColumns.add(columnReader.readLine());

        String columnString = initialColumns.get(0);
        columnBoth = new ArrayList<>(Arrays.asList(columnString.trim().split("\\s*,\\s*")));
        numColumns = columnBoth.size();

        //figure out the row situation
        BufferedReader rowReader = new BufferedReader(new FileReader(filename));
        rowReader.readLine(); //ignore this line, it's column names
        String line;
        while ((line = rowReader.readLine()) != null) {
            theRows.add(line);
            numRows++;
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

        theColumns = new ArrayList<>();
        for (int i = 0; i < numColumns; i++) {
            theColumns.add(new ArrayList<T>());
        }
        //puts shit in le columns
        for (int i = 0; i < numRows; i++) {
            String stringRow = theRows.get(i);
            T[] theRow = (T[]) stringRow.split(","); //if weird things happen check here
            for (int x = 0; x < numColumns; x++) {
                (theColumns.get(x)).add(theRow[x]);
            }
        }

        //makes whole table
        String columnCategoryString = String.join(",", columnBoth);
        wholeTable.add(columnCategoryString);
        for (int i = 1; i < numRows; i++) {
            wholeTable.add(theRows.get(i));
        }

        System.out.println("yikes");
    }

    private void printTable() {
        for (int i = 0; i < numRows; i++) {
            System.out.println(wholeTable.get(i));
        }
    }

    private List<T> getColumn(String columnName) {
        int index = 0;
        int counter = 0;

        while (!columnNames.get(counter).equals(columnName)) {
            index++;
            counter++;
        }
        return theColumns.get(index);
    }

    public static void main(String args[]) throws IOException {
        TBLReader T = new TBLReader("records");
        //ActualTable HELP = new ActualTable(T);
        //List aColumn = (List) T.theColumns.get(0);
        //System.out.println(T.theColumns);
        //System.out.println(T.columnNames);
        //System.out.println(T.theRows);
        //T.getColumnNames();
        //T.getColumnTypes();
        //T.setUpColumns();
        //T.makeColumns();
        //T.setUpColumns();
        //T.makeColumns();
        for (int i = 0; i < T.numColumns; i++) {
            System.out.println(T.columnNames.get(i));
        }
        for (int i = 0; i < T.numColumns; i++) {
            System.out.println(T.columnTypes.get(i));
        }
        for (int i = 0; i < T.numRows; i++) {
            System.out.println(T.theRows.get(i));
            //String something = T.columnName.get(0);
            //System.out.println(something.equals("Lastname string"));
        }
        //System.out.println(T.theColumns.get(4)); //prints Row0
        System.out.println(T.theRows);
        System.out.println(T.theColumns);
        System.out.println(T.columnBoth);


        System.out.println(T.wholeTable);
        System.out.println(T.columnBoth);
        System.out.println(T.columnBoth.get(1));
        //T.setWholeTable();
        T.printTable();
        System.out.println(T.theColumns);
        System.out.println(T.columnBoth);
        System.out.println("potato");

    }
}