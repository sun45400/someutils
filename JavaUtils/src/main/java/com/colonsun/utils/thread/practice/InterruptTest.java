package com.colonsun.utils.thread.practice;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread("thread");
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

class MyThread extends Thread{
    int i = 0;
    public MyThread(String name){
        super(name);
    }
    public void run(){
        while(!this.isInterrupted()){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                interrupt();
            }
            System.out.println("线程 " + getName() + " 第" + ++i + "次执行");
        }
    }
}