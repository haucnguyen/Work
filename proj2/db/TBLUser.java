package db;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Hau on 3/3/17.
 */
public class TBLUser<T> {
    TBLReader existingTable;
    String tablename;
    String filename;

    public TBLUser(String tablename) throws IOException {
        this.tablename = tablename;
        filename = ("db/Tables/" + tablename + ".tbl");
        existingTable = new TBLReader(tablename);
    }

    //will be used by the store method
    public void writeTheTable() throws IOException {
        //File file = new File(filename);
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (int i = 0; i < existingTable.numRows; i++) {
            bufferedWriter.write((String) existingTable.wholeTable.get(i));
        }
    }

    public void deleteTheTable() throws IOException {

    }

    public static void main(String args[]) throws IOException {

    }

}
