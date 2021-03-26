package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum ConnectionUtil {
    INSTANCE;

//    private static HikariConfig config = new HikariConfig("C:\\Users\\anaki\\Desktop\\gitrepos\\Project1\\src\\main\\resources\\datasource.properties");
    Properties prop = new Properties ();
    private static HikariConfig config = new HikariConfig(INSTANCE.prop);
    private static HikariDataSource ds = new HikariDataSource (config);

    final int MAX_THREAD = 3;
    ExecutorService threads = Executors.newFixedThreadPool (MAX_THREAD);

    public ExecutorService getThread(){
        return threads;
    }

    private ConnectionUtil() {
        prop.setProperty ("jdbcUrl","jdbc:postgresql://samplepsql.cx2v78knsmfh.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=anime_dealership");
        prop.setProperty ("dataSource.user","caradmin");
        prop.setProperty ("dataSource.password","password");
        prop.setProperty ("dataSource.cachePrepStmts","true");
        prop.setProperty ("dataSource.prepStmtCacheSize","250");
        prop.setProperty ("dataSource.prepStmtCacheSqlLimit","2048");

    }

    public static Connection getConnection() throws SQLException {
        config.setDriverClassName("org.postgresql.Driver");

        return ds.getConnection();
    }


}
