package com.colonsun.utils.thread.practice;

import java.util.ArrayList;
import java.util.List;

//多线程只能通过共享变量来实现交互？
public class ThreadTest {
    public static void main(String[] args){
        for(int i = 0; i < 200; i++){
            testCount();
        }
    }

    private static boolean allThreadTerminated(List<Thread> threads){
        for(Thread thread : threads){
            if(thread.isAlive()){
                return false;
            }
        }
        return true;
    }

    private static void testCount(){
        final Count count = new Count();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
                    count.increment();
                }
            }
        };
        List<Thread> threads = new ArrayList<Thread>(10);
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }
        //TODO 修改为定时器
        while(true){
            if(allThreadTerminated(threads)){
                if(count.get()<100000)
                    System.out.println(count.get());
                break;
            }
        }
    }
}

class Count {
    private int num;
    public void increment(){
        num++;
    }
    public int get(){
        return num;
    }
}