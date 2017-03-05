package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Database{

    public Database() {

    }

     public String transact(String query) {
        String[] theQuery = query.split(" ");
        String command = theQuery[0];

        if (command.equals("load")) {
            return load(query);
        } else if (command.equals("print")) {
            return print(query);
        } else if (command.equals("store")) {
            return store(query);
        } else if (command.equals("drop")) {
            return drop(query);
        } else if (command.equals("insert")) {
            return insert(query);
        } else if (command.equals("select")) {
            return select(query);
        }
         return "just give up already!!! u aren't going to finish";
     }

    public String createTable(String name, String[] columns){

        return "call createTable here";
    }

    public String store(String name) {
        return "call store method here";
    }

    public String load (String name) {
        return "call load method";
    }

    public String print(String name) {
        return "call print method here";
    }

    public String join(String name) {
        return "call join method here";
    }

    public String drop(String name) {
        return "call drop method here";
    }

    public String insert(String name) {
        return "call insert method here";
    }

    public String select(String name) {
        return "call select method here";
    }

    /* private void nameType(ArrayList name) {
        HashMap somename = new HashMap<String, String>();
        for (int i = 0; i < name.size(); i++) {
            Objects[] anothername = name.get(i).split(" ");
            somename.put(anothername[0], anothername[1]);
        }
    } */

}
