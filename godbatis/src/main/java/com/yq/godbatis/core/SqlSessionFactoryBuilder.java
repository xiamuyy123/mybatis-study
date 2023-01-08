package com.yq.godbatis.core;

import com.yq.godbatis.utils.Resources;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactoryBuilder(){};

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element envs = (Element) document.selectSingleNode("/configuration/environments");
        String defaultEnv = envs.attributeValue("default");
        Element env = (Element) document.selectSingleNode("/configuration/environments/environment[@id='"+defaultEnv+"']");
        //datasource
        Element dataSourceElt = env.element("dataSource");
        DataSource dataSource = getDataSource(dataSourceElt);
        //transaction
        Element transactionManagerElt = env.element("transactionManager");
        Transaction transactionManager = getTransactionManager(dataSource, transactionManagerElt);
        //sqlMap
        List<String>mapperList = new ArrayList<>();
         document.selectNodes("//mapper").forEach(
                 node -> {
                     mapperList.add(((Element) node).attributeValue("resource"));
                 }
         );
        Map<String,MapperStatement> map = getSqlMap(mapperList);
        return new SqlSessionFactory(transactionManager,map);
    }

    private Map<String, MapperStatement> getSqlMap(List<String> mapperList) {
        Map<String, MapperStatement> map = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        mapperList.forEach(
                mapper -> {
                    InputStream mapperStream = Resources.getResourceAsStream(mapper);
                    try {
                        Document document = saxReader.read(mapperStream);
                        Element mapperElt = (Element) document.selectSingleNode("/mapper");
                        String namespace = mapperElt.attributeValue("namespace");
                        mapperElt.elements().forEach(
                                element -> {
                                    String id = element.attributeValue("id");
                                    String resultType = element.attributeValue("resultType");
                                    String sql = element.getTextTrim().trim();
                                    map.put(namespace+"."+id,new MapperStatement(sql,resultType));
                                }
                        );
                    } catch (DocumentException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return map;
    }

    public Transaction getTransactionManager(DataSource dataSource,Element txElt){
        Transaction transaction = null;
        String type = txElt.attributeValue("type").trim().toUpperCase();
        if(Const.JDBC_TRANSACTION.equals(type)){
            transaction = new JDBCTranscation(dataSource,false);
        }
        if(Const.MANAGED_TRANSACTION.equals(type)){

        }
        return transaction;

    }
    public DataSource getDataSource(Element dataSouceEle){
        Map<String,String>map = new HashMap<>();
        DataSource dataSource =null;
        dataSouceEle.elements("property").forEach(
                element -> {
                    map.put(element.attributeValue("name"),element.attributeValue("value"));
                }
        );
        String type = dataSouceEle.attributeValue("type").trim().toUpperCase();
        if(Const.UN_POOLLED_DATASOURCE.equals(type)){
            dataSource = new UnpolldeDataSource(map.get("driver"),map.get("url"),map.get("username"),map.get("password"));

        }
        if( Const.POOLED_DATASOURCE.equals(type)){

        }
        if(Const.JNDI_DATASOURCE.equals(type)){

        }
        return dataSource;
    }
}
