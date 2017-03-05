package db;

import java.util.regex.Pattern;

/**
 * Created by Hau on 3/3/17.
 */
public class CommandLineReader extends TheTable {
    private static final String REST = "\\s*(.*)\\s*",
            COMMA = "\\s*,\\s*",
            AND = "\\s+and\\s+";

    private static final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
            load = Pattern.compile("load " + REST),
            store = Pattern.compile("store " + REST),
            DROP_CMD = Pattern.compile("drop table " + REST),
            INSERT_CMD = Pattern.compile("insert into " + REST),
            PRINT_CMD = Pattern.compile("print " + REST),
            SELECT_CMD = Pattern.compile("select " + REST);

    private static final Pattern CREATE_NEW = Pattern.compile("(\\S+)\\s+\\(\\s*(\\S+\\s+\\S+\\s*" +
            "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
            SELECT_CLS = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
                    "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
                    "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+" +
                    "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
            CREATE_SEL = Pattern.compile("(\\S+)\\s+as select\\s+" +
                    SELECT_CLS.pattern()),
            INSERT_CLS = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
                    "\\s*(?:,\\s*.+?\\s*)*)");

    //select column names from table where filter
    public static void parse1(String query) {
        String[] theQuery = query.split(" ");
        String command = theQuery[0];

        //create table seasonRatios as select City,Season,Wins/Losses as Ratio from teams,records
        if (command.equals("create")) {
            String tablename = theQuery[2];
            if (theQuery[3] != null) {
                String as = theQuery[3];
                String select = theQuery[4];
                String column = theQuery[5];
                String[] columnnames = column.split(","); //split column names by ,
                String as2 = theQuery[6];
                String newtablename = theQuery[7];
                String from = theQuery[8];
                String tablethatalreadyexist = theQuery[9]; //somehow check to see if it is selecting from 1 table or multiple
                String[] columnthatalreadyexist = tablethatalreadyexist.split(",");
            }
        }

        //select Firstname,Lastname,TeamName from fans where Lastname >= 'Lee'
        if (command.equals("select")) {
            String column = theQuery[1]; //somehow check to see if it is selecting from 1 table or multiple
            String[] columnnames = column.split(","); //split column names by ,
            String from = theQuery[2]; //from statement
            String tablethatalreadyexist = theQuery[3]; //could be from 2 tables but needs join so that is more
            if (theQuery[4] != null) {
                String where = theQuery[4];
                String clause = theQuery[5]; //some clause that will filter through the table
                String equality = theQuery[6]; // > <
                String filter = theQuery[7];
            }
        }
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            parse1("select heloo,Lastnamdase,TeamdsName from fadsans where Lasdtname >= 'Lee'");
            return;
        }

        eval(args[0]);
    }

    public static void eval(String query) {
        System.out.println(query);
    }
}
