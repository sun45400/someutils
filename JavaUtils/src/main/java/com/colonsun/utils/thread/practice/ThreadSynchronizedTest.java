package com.colonsun.utils.thread.practice;

public class ThreadSynchronizedTest {
    public static void main(String[] args) {
        test2();
    }

    private static void test1(){
        final Outputter outputter = new Outputter();
        new Thread(){
            public void run(){
                try {
                    outputter.output("12345");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    outputter.output("67890");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void test2(){
        new Thread(){
            public void run(){
                for(int i = 0; i < 100; i++){
                    Outputter.increment();
                }
            }

        }.start();

        new Thread(){
            public void run(){
                for(int i = 0; i < 100; i++){
                    Outputter.print();
                }
            }
        }.start();
    }
}



class Outputter{

    static int i = 0, j = 0;
    static void increment(){
        i++;
        j++;
    }
    static void print(){
        System.out.println("i = " + i + ", j = " + j);
    }





    public void output(String str) throws InterruptedException {
        synchronized(this){
            for(int i = 0; i < str.length(); i++){
                System.out.print(str.charAt(i));
                Thread.sleep(10);
            }
        }
    }


}