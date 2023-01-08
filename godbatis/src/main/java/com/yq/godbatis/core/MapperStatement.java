package com.yq.godbatis.core;

public class MapperStatement {
    String sql;
    String resultType;

    public MapperStatement(String sql, String resultType) {
        this.sql = sql;
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "MapperStatement{" +
                "sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
