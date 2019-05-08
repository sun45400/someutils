package com.colonsun.utils.tricksandtests;

import com.colonsun.utils.io.excel.OutputTestObj;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SomeTest {
    public static void main(String[] args){
        testSort();
    }

    private static void testSort(){
        OutputTestObj obj1 = new OutputTestObj();
        obj1.setOrder(9);
        OutputTestObj obj2 = new OutputTestObj();
        obj2.setOrder(1);
        OutputTestObj obj3 = new OutputTestObj();
        obj3.setOrder(4);
        List<OutputTestObj> list = new LinkedList<OutputTestObj>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        for(OutputTestObj obj : list){
            System.out.println(obj.getOrder());
        }
        System.out.println("--------");
        Collections.sort(list);
        for(OutputTestObj obj : list){
            System.out.println(obj.getOrder());
        }
    }

}
