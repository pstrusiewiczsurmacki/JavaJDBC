import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {

    public static BasicDataSource dSource = null;

    public static BasicDataSource getDataSource(){

        if (dSource == null) {
            BasicDataSource ds = new BasicDataSource();

            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUsername("root");
            ds.setPassword("xokooniemoh2ao1chahlei9ufae9Dahg");
            ds.setUrl("jdbc:mysql://localhost:3306/java");

            ds.setMaxTotal(10);
            ds.setAutoCommitOnReturn(false);
            ds.setDefaultAutoCommit(false);

            dSource = ds;
        }
        return dSource;
    }

}
