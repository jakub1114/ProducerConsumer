package org.example.setting;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnector {

    private DbConnector(){
        throw new AssertionError();
    }

    public static Connection getConnection() {

        try {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl("jdbc:h2:/data/test;INIT=RUNSCRIPT FROM 'classpath:users.sql'");
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
