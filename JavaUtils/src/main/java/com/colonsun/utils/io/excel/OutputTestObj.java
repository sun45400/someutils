package com.colonsun.utils.io.excel;

public class OutputTestObj implements Comparable<OutputTestObj>{

    public OutputTestObj(){}

    @Header(value = "列1",index = 1)
    private String field1;

    private String field2;

    @Header(value = "field2",index = 2)
    private String field3;

    private int order;

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int compareTo(OutputTestObj anotherObj) {
        return Integer.compare(this.order,anotherObj.order);
    }
}
