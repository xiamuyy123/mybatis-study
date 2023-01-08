package com.yq.godbatis.core;

import java.util.Map;

//对应于一个数据库
public class SqlSessionFactory {
    //事务管理器属性
    private Transaction transactionManager;
    //数据源属性 放置于事务管理器中

    //所有的sql标签对应属性 key:ns.id value:mappedStatement(sql,resultType)
    private Map<String,MapperStatement> map;

    public SqlSession openSqlSession(){
        transactionManager.openConnection();
        return new SqlSession(this);
    }
    public SqlSessionFactory() {
    }


    public SqlSessionFactory(Transaction transactionManager, Map<String, MapperStatement> map) {
        this.transactionManager = transactionManager;
        this.map = map;
    }

    public Transaction getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(Transaction transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Map<String, MapperStatement> getMap() {
        return map;
    }

    public void setMap(Map<String, MapperStatement> map) {
        this.map = map;
    }
}
