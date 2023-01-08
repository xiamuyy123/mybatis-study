package com.yq.godbatis.core;

import java.sql.Connection;

public interface Transaction {
    void commit();
    void rollback();
    void close();
    Connection openConnection();
    Connection getConnection();

}
