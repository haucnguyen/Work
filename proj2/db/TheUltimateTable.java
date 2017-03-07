package db;

/**
 * Created by Hau on 3/6/17.
 */
public interface TheUltimateTable {
    public String load(String query);

    public String create(String query);

    public String store(String query);

    public String drop(String query);

    public String insert(String query);

    public String print(String query);

    public String select(String query);

}