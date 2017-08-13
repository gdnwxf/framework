package com.wch.json2beans;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wch.springframework.util.ObjectUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import sun.security.krb5.internal.PAData;

import javax.json.Json;
import java.util.*;
import java.util.regex.Pattern;

public class Json2Bean {



    static  String string = "{\n" +
            "\t\"code\": 1,\n" +
            "\t\"data\": [{\n" +
            "\t\t\"id\": null,\n" +
            "\t\t\"entityId\": null,\n" +
            "\t\t\"paymentScene\": \"wechat_member_card_recharge\",\n" +
            "\t\t\"sceneDesc\": null,\n" +
            "\t\t\"feeRate\": 0.02,\n" +
            "\t\t\"enable\": true,\n" +
            "\t\t\"operator\": null,\n" +
            "\t\t\"createTime\": null,\n" +
            "\t\t\"updateTime\": null,\n" +
            "\t\t\"settleChannel\": null\n" +
            "\t}, {\n" +
            "\t\t\"id\": null,\n" +
            "\t\t\"entityId\": null,\n" +
            "\t\t\"paymentScene\": \"alipay_member_card_recharge\",\n" +
            "\t\t\"sceneDesc\": null,\n" +
            "\t\t\"feeRate\": 0.03,\n" +
            "\t\t\"enable\": true,\n" +
            "\t\t\"operator\": null,\n" +
            "\t\t\"createTime\": null,\n" +
            "\t\t\"updateTime\": null,\n" +
            "\t\t\"settleChannel\": null\n" +
            "\t}]\n" +
            "}";



    static Pattern pattern1 = Pattern.compile("\\{.*\\}" ,Pattern.DOTALL);
    static Pattern pattern2 = Pattern.compile("\\[.*\\]" ,Pattern.DOTALL);


    static class ClassInfo{
        String pkgName;
        String className;
        Set<String>  fields ;
        Map<String , String> fieldType = new HashMap<>();
        Map<String , Object> fieldValue ;

        public String getPkgName() {
            return pkgName;
        }

        public void setPkgName(String pkgName) {
            this.pkgName = pkgName;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Set<String> getFields() {
            return fields;
        }

        public void setFields(Set<String> fields) {
            this.fields = fields;
        }

        public Map<String, String> getFieldType() {
            return fieldType;
        }

        public void setFieldType(Map<String, String> fieldType) {
            this.fieldType = fieldType;
        }

        public Map<String, Object> getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(Map<String, Object> fieldValue) {
            this.fieldValue = fieldValue;
        }
    }

    public static void main(String[] args) {
        Integer a  = 10 ;

        String className = "Data" ;
        String pkgName = "Data" ;

        ClassInfo mianClassInfo = new ClassInfo();
        mianClassInfo.setPkgName(pkgName);
        mianClassInfo.setClassName(className);
        mianClassInfo.setFields(Sets.newHashSet());
        mianClassInfo.setFieldType(Maps.newHashMap());
        mianClassInfo.setFieldValue(Maps.newHashMap());


        ArrayList<ClassInfo> classInfos = Lists.newArrayList();

        if (pattern2.matcher(string).matches()) {
            doAnalysisArray(JSON.parse(string));
        }else if(pattern1.matcher(string).matches()){
            doAnalysisObject(JSON.parse(string), classInfos, mianClassInfo);
        }else{
            System.out.printf("你输入的json格式有误");
            System.out.println(string);
        }


    }

    private static boolean baseType(Object parse) {

        if(parse == null) return true;
        //数字和字符

        if(parse instanceof Number) return true;
        if(parse instanceof Character) return true;
        if(parse instanceof CharSequence) return true;


        Class<?> aClass = parse.getClass();

        if(aClass == int.class) return  true;
        if(aClass == short.class) return  true;
        if(aClass == byte.class) return  true;
        if(aClass == long.class) return  true;
        if(aClass == double.class) return  true;
        if(aClass == float.class) return  true;
        if(aClass == int.class) return  true;

        return  false;

    }

    private String getFieldType(Object parse ) {

        //数字和字符

        if(parse instanceof Number)    return "Integer";
        if(parse instanceof Character) return "String";
        if(parse instanceof CharSequence) return "String";


        Class<?> aClass = parse.getClass();

        if(aClass == int.class) return  "int";
        if(aClass == short.class) return  "short";
        if(aClass == byte.class) return  "short";
        if(aClass == long.class) return  "long";
        if(aClass == double.class) return  "double";
        if(aClass == float.class) return  "float";


        return  null;
    }


    private static void doAnalysisObject(Object parse, List<ClassInfo> classInfos, ClassInfo classInfo) {


        if(baseType(parse)) {
            System.out.println(
                    parse  +" +++ "
            );


            return;
        }

        if(parse instanceof JSONObject ) {
            Set<String> strings = ((JSONObject) parse).keySet();
            classInfo.setFields(strings);
            strings.forEach((e)->{
                Object o = ((JSONObject) parse).get(e);
                doAnalysisObject(o ,classInfos, classInfo);

            });
        }else if(parse instanceof JSONArray) {
            Object o = ((JSONArray) parse).get(0);
            doAnalysisObject(o , classInfos ,classInfo);
        }
    }

    private static void doAnalysisArray(Object string) {
        System.out.println("数组");

    }

}
