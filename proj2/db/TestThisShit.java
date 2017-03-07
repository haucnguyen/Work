package db;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hau on 3/7/17.
 */
public class TestThisShit {
    public static void main(String args[]) throws IOException {
        Database fuck = new Database();
        //Load x = new Load("records", fuck);
        //fuck.tableInteractor.print(x.newTable);
        //Print p = new Print(x.newTable);
        fuck.load("records");
        System.out.println("potato");
        //Store s = new Store(x.newTable);

        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("'Spongebob'");
        tempList.add("1996");
        tempList.add("420");
        tempList.add("69");
        tempList.add("666");
        //fuck.print("records");


        ArrayList<String> lmao = new ArrayList<>();
        lmao.add("'why the fuck'");
        lmao.add("1996");
        lmao.add("420");
        lmao.add("69");
        lmao.add("666");


        //Table idfk = fuck.getTable("records");
        //fuck.insertInto("records",tempList);
        //fuck.insertInto("records",lmao);
        //fuck.Print("records");

        fuck.insertInto("records", tempList);
        //Table hopefullyCorrect = fuck.getTable("records");
        //fuck.tableInteractor.print(hopefullyCorrect);
        System.out.println(fuck.print("records"));
        //fuck.store("records");
        System.out.println("yeah boi2");
        //fuck.load("records");
        fuck.store("records");

        /*ArrayList<String> columnsToSelect = new ArrayList<>();
        columnsToSelect.add("TeamName");
        columnsToSelect.add("Season");
        columnsToSelect.add("Ties");
        ArrayList<Table> tableToSelect = new ArrayList<>();
        tableToSelect.add(fuck.getTable("records"));
        ArrayList<String> idk = new ArrayList<>();
        Selector ugh = new Selector(columnsToSelect, tableToSelect, idk);
        System.out.println(fuck.print(ugh.select()));
        System.out.println("is this working");*/
        //System.out.println(fuck.select(columnsToSelect, tableToSelect));
        //System.out.println("potoato");
        //System.out.println("yeah boi");

        //fuck.Print("records");
        //fuck.dropTable("records");
        //System.out.println("fuck");

        //fuck.Print("records");

        //fuck.store("records");
        //Store hehe = new Store(temp); //ask Harley why it's printing
        //updated table but storing the old one
        //System.out.println("is this working");

        //System.out.println(x.columnBoth);
        //System.out.println(x.columnTypes);
        //System.out.println(x.columnNames);
        //System.out.println(x.theRows);
    }
}
