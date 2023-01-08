package com.yq.godbatis.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTranscation implements Transaction{


    private DataSource dataSource;

    private boolean autoCommit;

    private Connection connection;

    public JDBCTranscation(DataSource dataSource, boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection openConnection() {
        if(connection==null){
            try {
                connection=dataSource.getConnection();
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
