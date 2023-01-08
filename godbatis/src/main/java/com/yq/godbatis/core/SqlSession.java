package com.yq.godbatis.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class SqlSession {

    private SqlSessionFactory factory;

    public Object selectOne(String sqlId,Object param) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Connection connection = factory.getTransactionManager().openConnection();
        MapperStatement mapperStatement = factory.getMap().get(sqlId);
        String godbatisSql = mapperStatement.getSql();
        String resultType = mapperStatement.getResultType();
        String sql = godbatisSql.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, (String) param);
        ResultSet rs = ps.executeQuery();

        Class<?> clazz = Class.forName(resultType);
        Object o = clazz.newInstance();
        while (rs.next()){
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            for (int i = 0; i < count; i++) {
                String columnName = metaData.getColumnName(i+1);
                String methodName = "set" + columnName.toUpperCase().charAt(0) + columnName.substring(1);
                Method method = clazz.getDeclaredMethod(methodName, String.class);
                method.invoke(o,rs.getString(columnName));
            }
        }
        return o;


    }
    public int insert(String sqlId,Object pojo) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int count=0;

        //JDBC执行sql

        Connection connection = factory.getTransactionManager().openConnection();

        MapperStatement mapperStatement = factory.getMap().get(sqlId);
        String godbatisSql = mapperStatement.getSql();
        String sql = godbatisSql.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给？传值：1.不知道有多少个 2.第几个问号传什么值
        int fromIndex = 0;
        int index = 1;
        while (true){
            int jingIndex = godbatisSql.indexOf("#",fromIndex);
            if(jingIndex<0){
                break;
            }
            int youkuohaoIndex  = godbatisSql.indexOf("}",fromIndex);
            String propertyName = godbatisSql.substring(jingIndex + 2, youkuohaoIndex).trim();

            fromIndex = youkuohaoIndex+1;
            String methodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
            Method method = pojo.getClass().getDeclaredMethod(methodName);
            String invoke = (String) method.invoke(pojo, null);
            preparedStatement.setString(index,invoke);
            index++;
        }
        count=preparedStatement.executeUpdate();
        return count;
    }

    public void commit(){
        factory.getTransactionManager().commit();
    }
    public void rollback(){
        factory.getTransactionManager().rollback();
    }
    public void close(){
        factory.getTransactionManager().close();
    }

    public SqlSession(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public SqlSessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SqlSessionFactory factory) {
        this.factory = factory;
    }
}
