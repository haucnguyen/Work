package db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hau on 3/3/17.
 */
public class TBLUser<T> {
    String tablename;
    String filename;
    int tableDepth;
    int tableWidth;
    ArrayList<String> columnHeaders;
    List<List<T>> actualColumns;
    ArrayList<String> easyToPrintRows;


    public TBLUser(ActualTable existingTable) throws IOException {
        columnHeaders = existingTable.columnToPrint;
        easyToPrintRows = existingTable.rowsToPrint;
        actualColumns = existingTable.values;
        tableDepth = existingTable.tableRows;
        tableWidth = existingTable.tableColumns;
        tablename = existingTable.name;
        filename = ("db/Tables/" + tablename + ".tbl");
    }

    //will be used by the store method
    public void writeTheTable() throws IOException {
        //File file = new File(filename);
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write(String.join(",", columnHeaders));
        bufferedWriter.newLine();

        for (int i = 0; i < tableDepth; i++) {
            String newRow = String.join(",", easyToPrintRows.get(i));
            bufferedWriter.write(newRow);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public void deleteTheTable() throws IOException {

    }

    public void printTheTable() throws IOException {
        System.out.println(String.join(",", columnHeaders));
        for (int i = 0; i < tableWidth; i++) {
            String newRow = String.join(",", easyToPrintRows.get(i));
            System.out.println(newRow);
        }
    }

    public static void main(String args[]) throws IOException {
        TBLReader t = new TBLReader("records");
        ActualTable aT = new ActualTable(t);
        TBLUser u = new TBLUser(aT);
        //u.writeTheTable();
        //System.out.println(u.columnHeaders);
        //System.out.println(u.actualColumns);
        u.printTheTable();
    }

}
