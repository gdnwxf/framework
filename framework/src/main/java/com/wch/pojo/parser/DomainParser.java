package com.wch.pojo.parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wch.pojo.UserInfo;

/**
 * Created by wch on 2016/5/25.
 */
public class DomainParser {

    private String tableName;
    private List<KeyValue<String,String>> ids = new LinkedList<KeyValue<String, String>>();
    private List<KeyValue<String,String>> columns = new LinkedList<KeyValue<String, String>>();

    private Map<String , String> allColumn  = new HashMap<String, String>() ;

    public void parser(Class<?> clazz){
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        tableName =  tableAnnotation.name();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            Column column = declaredField.getAnnotation(Column.class);
            Id idAnnotation = declaredField.getAnnotation(Id.class);

            KeyValue<String, String> map = getMap();
            if(idAnnotation != null ){
                map.put(fieldName, column == null ? fieldName : column.name());
                allColumn.put(fieldName, column == null ? fieldName : column.name());
                ids.add(map);
            } else if(column != null)
            {
                map.put(fieldName,column.name());
                allColumn.put(fieldName,column.name());
                ids.add(map);
            } else{
                map.put(fieldName,fieldName);
                allColumn.put(fieldName,fieldName);
                columns.add(map);
            }
        }

    }


    public Map<String, String> getAllColumn() {
        return allColumn;
    }

    public void setAllColumn(Map<String, String> allColumn) {
        this.allColumn = allColumn;
    }

    private KeyValue<String , String> getMap() {
        return new KeyValue<String, String>();
    }


    public static void main(String[] args) {
        DomainParser domainParser = new DomainParser();
        domainParser.parser(UserInfo.class);
        System.out.println(domainParser);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public List<KeyValue<String, String>> getIds() {
        return ids;
    }

    public void setIds(List<KeyValue<String, String>> ids) {
        this.ids = ids;
    }

    public List<KeyValue<String, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<KeyValue<String, String>> columns) {
        this.columns = columns;
    }

    /**
     * 单个keyvalue的键值对
     * @param <T>
     * @param <E>
     */
    class KeyValue<T ,E >{
        private T  key;
        private E  value;

        public KeyValue() {
        }

        public KeyValue(T key, E value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void put(T key , E value) {
            this.key  = key ;
            this.value  = value ;
        }
    }
}
