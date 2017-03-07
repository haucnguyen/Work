//package db;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.StringJoiner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by Hau on 3/3/17.
// */
//public class CommandLineReader {
//        // Various common constructs, simplifies parsing.
//        public static final String REST  = "\\s*(.*)\\s*",
//                COMMA = "\\s*,\\s*",
//                AND   = "\\s+and\\s+";
//
//        // Stage 1 syntax, contains the command name.
//        public static final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
//                LOAD_CMD   = Pattern.compile("load " + REST),
//                STORE_CMD  = Pattern.compile("store " + REST),
//                DROP_CMD   = Pattern.compile("drop table " + REST),
//                INSERT_CMD = Pattern.compile("insert into " + REST),
//                PRINT_CMD  = Pattern.compile("print " + REST),
//                SELECT_CMD = Pattern.compile("select " + REST);
//
//        // Stage 2 syntax, contains the clauses of commands.
//        public static final Pattern CREATE_NEW  = Pattern.compile("(\\S+)\\s+\\((\\S+\\s+\\S+\\s*" +
//                "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
//                SELECT_CLS  = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
//                        "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
//                        "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+" +
//                        "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
//                CREATE_SEL  = Pattern.compile("(\\S+)\\s+as select\\s+" +
//                        SELECT_CLS.pattern()),
//                INSERT_CLS  = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
//                        "\\s*(?:,\\s*.+?\\s*)*)");
//
//        public static void main(String[] args) {
//            if (args.length != 1) {
//                System.err.println("Expected a single query argument");
//                return;
//            }
//
//            eval(args[0]);
//        }
//
//        public static void eval(String query) {
//            Matcher m;
//            if ((m = CREATE_CMD.matcher(query)).matches()) {
//                createTable(m.group(1));
//            } else if ((m = LOAD_CMD.matcher(query)).matches()) {
//                loadTable(m.group(1));
//            } else if ((m = STORE_CMD.matcher(query)).matches()) {
//                storeTable(m.group(1));
//            } else if ((m = DROP_CMD.matcher(query)).matches()) {
//                dropTable(m.group(1));
//            } else if ((m = INSERT_CMD.matcher(query)).matches()) {
//                insertRow(m.group(1));
//            } else if ((m = PRINT_CMD.matcher(query)).matches()) {
//                printTable(m.group(1));
//            } else if ((m = SELECT_CMD.matcher(query)).matches()) {
//                select(m.group(1));
//            } else {
//                System.err.printf("Malformed query: %s\n", query);
//            }
//        }
//
//        private static void createTable(String expr) {
//            Matcher m;
//            if ((m = CREATE_NEW.matcher(expr)).matches()) {
//                createNewTable(m.group(1), m.group(2).split(COMMA));
//            } else if ((m = CREATE_SEL.matcher(expr)).matches()) {
//                createSelectedTable(m.group(1), m.group(2), m.group(3), m.group(4));
//            } else {
//                System.err.printf("Malformed create: %s\n", expr);
//            }
//        }
//
//        private static void createNewTable(String name, String[] cols) {
//            StringJoiner joiner = new StringJoiner(", ");
//            for (int i = 0; i < cols.length-1; i++) {
//                joiner.add(cols[i]);
//            }
//
//            String colSentence = joiner.toString() + " and " + cols[cols.length-1];
//            System.out.printf("You are trying to create a table named %s with the columns %s\n", name, colSentence);
//        }
//
//        private static void createSelectedTable(String name, String exprs, String tables, String conds) {
//            System.out.printf("You are trying to create a table named %s by selecting these expressions:" +
//                    " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", name, exprs, tables, conds);
//        }
//
//        private static void loadTable(String name) {
//            System.out.printf("You are trying to load the table named %s\n", name);
//        }
//
//        private static void storeTable(String name) {
//            System.out.printf("You are trying to store the table named %s\n", name);
//        }
//
//        private static void dropTable(String name) {
//            System.out.printf("You are trying to drop the table named %s\n", name);
//        }
//
//        private static void insertRow(String expr) {
//            Matcher m = INSERT_CLS.matcher(expr);
//            if (!m.matches()) {
//                System.err.printf("Malformed insert: %s\n", expr);
//                return;
//            }
//
//            System.out.printf("You are trying to insert the row \"%s\" into the table %s\n", m.group(2), m.group(1));
//        }
//
//        private static void printTable(String name) {
//            System.out.printf("You are trying to print the table named %s\n", name);
//        }
//
//        private static void select(String expr) {
//            Matcher m = SELECT_CLS.matcher(expr);
//            if (!m.matches()) {
//                System.err.printf("Malformed select: %s\n", expr);
//                return;
//            }
//
//
//            select(m.group(1), m.group(2), m.group(3));
//
//        }
//
//        private static void select(String exprs, String tables, String conds) {
//            System.out.printf("You are trying to select these expressions:" +
//                    " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", exprs, tables, conds);
//        }
//    }
//
//
////    private static final String REST = "\\s*(.*)\\s*",
////            COMMA = "\\s*,\\s*",
////            AND = "\\s+and\\s+";
////
////    private static final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
////            load = Pattern.compile("load " + REST),
////            store = Pattern.compile("store " + REST),
////            DROP_CMD = Pattern.compile("drop table " + REST),
////            INSERT_CMD = Pattern.compile("insert into " + REST),
////            PRINT_CMD = Pattern.compile("print " + REST),
////            SELECT_CMD = Pattern.compile("select " + REST);
////
////    private static final Pattern CREATE_NEW = Pattern.compile("(\\S+)\\s+\\(\\s*(\\S+\\s+\\S+\\s*" +
////            "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
////            SELECT_CLS = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
////                    "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
////                    "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+" +
////                    "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
////            CREATE_SEL = Pattern.compile("(\\S+)\\s+as select\\s+" +
////                    SELECT_CLS.pattern()),
////            INSERT_CLS = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
////                    "\\s*(?:,\\s*.+?\\s*)*)");
////
////    public CommandLineReader(String query) {
////        parse1(query);
////    }
////
////    //select column names from table where filter
////    public static void parse1(String query) {
////        String[] theQuery = query.split(" ");
////        String command = theQuery[0];
////        int equality = 8;
////        ArrayList<String> columnnames = new ArrayList<>(); //all the column names
////        ArrayList<String> fromwhattable = new ArrayList<>(); //all the tables that it is taking from
////        ArrayList<String> whereclause = new ArrayList<>(); // the where clauses split up
////
////        //create table seasonRatios as select City,Season,Wins/Losses as Ratio from teams,records
////        if (command.equals("create")) {
////            String tablename = theQuery[2]; //table names
////            if (theQuery[3] != null) { //checks to see if it is complex, i.e. as blah blah blah
////                String as = theQuery[3];
////                String select = theQuery[4];
////                String column = theQuery[5];
////                if (theQuery[1].contains(",")) { //checks to see if it is selecting from more than one column
////                    String[] columnname = column.split(","); //split column names by ,
////                    for (int i = 0; i < columnname.length; i++) { //adds all the column names into the columnnames array
////                        columnnames.add(columnname[i]);
////                    }
////                    String as2 = theQuery[6];
////                    String newtablename = theQuery[7]; //the new table name
////                    String from = theQuery[8];
////                    if (theQuery[9].contains(",")) {  //checks to see if taking from different tables
////                        String existingtables = theQuery[9];
////                        String[] columnthatalreadyexist = existingtables.split(","); //split column names by ,
////                        for (int i = 0; i < columnthatalreadyexist.length; i++) {
////                            fromwhattable.add(columnthatalreadyexist[i]);
////                        }
////                    }
////                    else {
////                        fromwhattable.add(theQuery[9]);
////                    }
////                }
////                else {
////                    columnnames.add(theQuery[1]);
////                }
////            }
////        }
////
////        //select Firstname,Lastname,TeamName from fans,boob where Lastname >= 'Lee'
////        if (command.equals("select")) {
////            String column = theQuery[1]; //somehow check to see if it is selecting from 1 table or multiple
////            if (theQuery[1].contains(",")) {
////                String[] columnname = column.split(","); //split column names by ,
////                String from = theQuery[2]; //from statement
////                String existingtables = theQuery[3]; //could be from 2 tables but needs join so that is more
////                if (theQuery[3].contains(",")) { //checks to see if it is selecting from more than one table
////                    String[] listofexistingtables = existingtables.split(",");
////                    for (int i = 0; i < listofexistingtables.length; i++) {
////                        fromwhattable.add(listofexistingtables[i]);
////                    }
////                    if (theQuery[4] != null) { //checks to see if there is a filter or nah
////                        String where = theQuery[4];
////                        String clause1 = theQuery[5]; //some clause that will filter through the table
////                        String equality1 = theQuery[6]; // equalities like dis > <
////                        String filter1 = theQuery[7];
////                    }
////                }
////                else {
////                    fromwhattable.add(theQuery[3]); //selecting from one table only
////                    if (theQuery[4] != null) {
////                        String where = theQuery[4];
////                        String columns = theQuery[5]; //columns
////                        String equality1 = theQuery[6]; // > <
////                        whereclause.add(columns);
////                        whereclause.add(equality1);
////                        String filter1 = theQuery[7];
////                        for (int i = 7; i < theQuery.length; i +=2) { //runs through and splits the where clause
////                            if (theQuery[i].contains(",")) {
////                                String filter = theQuery[i]; // it goes by columns, equality, and then filter
////                                String filters[] = filter.split(",");
////                                String filter2 = filters[0];
////                                String columns2 = filters[1];
////                                whereclause.add((filter2));
////                                whereclause.add(columns2);
////                                whereclause.add(theQuery[equality]); // adds in the equality before it runs the for loop again
////                                equality +=2;
////                            }
////                        }
////                    }
////                }
////            }
////            else {
////                columnnames.add(theQuery[1]);
////            }
////        }
////    }
////
////
////    public static String parse2(String query) {
////        String[] theQuery = query.split(" ");
////        String command = theQuery[0];
////
////        //load tablename
////        if (command.equals("load")) {
////            if(theQuery[1].contains(",")) {
////                throw new Error("too many tables u loser");
////            }
////            String tablename = theQuery[1];
////            return tablename;
////        }
////
////        //store tablename
////        if (command.equals("store")) {
////            if(theQuery[1].contains(",")) {
////                throw new Error("too many tables u loser");
////            }
////            String tablename = theQuery[1];
////            return tablename;
////        }
////
////        //drop table tablename
////        if (command.equals("drop")) {
////            if(theQuery[2].contains(",")) {
////                throw new Error("too many tables u loser");
////            }
////            String tablename = theQuery[2];
////            return tablename;
////        }
////
////        //print tablename
////        if (command.equals("print")) {
////            if(theQuery[1].contains(",")) {
////                throw new Error("too many tables u loser");
////            }
////            String tablename = theQuery[1];
////            return tablename;
////        }
////        return null;
////    }
////
////
////    public static void main(String[] args) {
////        Database fuck = new Database();
////        parse1("select hello,Lastname,TeamName from fans,lol,sick where Lastname >= 'Lee',Firstname > 'lol',dsda < 'dsads'");
////        parse2("load t1");
////        return;
////
////    }
////
////
//////    public String eval(String query) throws IOException {
//////    Matcher m;
//////        TheTable currentTable = new TheTable(query);
//////
//////        if ((m = CREATE_CMD.matcher(query)).matches()) {
//////            return currentTable.create();
//////        } else if ((m = LOAD_CMD.matcher(query)).matches()) {
//////            return currentTable.load(m.group(1));
//////        } else if ((m = STORE_CMD.matcher(query)).matches()) {
//////            return currentTable.store(m.group(1));
//////        } else if ((m = DROP_CMD.matcher(query)).matches()) {
//////            return currentTable.drop(m.group(1));
//////        } else if ((m = INSERT_CMD.matcher(query)).matches()) {
//////            return currentTable.insert(m.group(1));
//////        } else if ((m = PRINT_CMD.matcher(query)).matches()) {
//////            return currentTable.print(m.group(1));
//////        } else if ((m = SELECT_CMD.matcher(query)).matches()) {
//////            return currentTable.select(m.group(1));
//////        } else {
//////            return "you fucked up man";
//////        }
//////    }
////
////}
////
////
