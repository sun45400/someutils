package com.colonsun.utils.tricksandtests;

import com.colonsun.utils.io.excel.Header;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class AnnotationTest {

    @Header(value = "field1",index = 1)
    private String field1;

    @Header(value = "field2",index = 2)
    private String field2;

    private String field3;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException {
        Class c = AnnotationTest.class;
        Method method = c.getMethod("main",String[].class);
        Hello hello = method.getAnnotation(Hello.class);
        System.out.println(hello.value());

        Field[] fields = c.getDeclaredFields();
        List<String> titles = new LinkedList<String>();
        for(Field field : fields){
            Header header = field.getAnnotation(Header.class);
            titles.add(header.value());
        }
    }
}
