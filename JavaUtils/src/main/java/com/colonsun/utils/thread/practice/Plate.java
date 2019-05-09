package com.colonsun.utils.thread.practice;

import java.util.ArrayList;
import java.util.List;

public class Plate {
    public static void main(String[] args){
        Plate plate = new Plate();
        for(int i = 0; i < 10; i++){
            new Thread(new GetThread(plate)).start();
            new Thread(new AddThread(plate)).start();
        }
    }

    List<Object> eggs = new ArrayList<Object>();

    public synchronized Object getEgg(){
        while (eggs.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object egg = eggs.get(0);
        eggs.clear();
        notify();
        System.out.println("拿鸡蛋");
        return egg;
    }

    public synchronized void putEgg(Object egg){
        while(eggs.size() > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eggs.add(egg);
        notify();
        System.out.println("放鸡蛋");
    }

    static class AddThread implements Runnable{

        private Plate plate;
        private Object egg = new Object();

        public AddThread(Plate plate){
            this.plate = plate;
        }

        @Override
        public void run() {
            plate.putEgg(egg);
        }
    }

    static class GetThread implements Runnable{
        private Plate plate;

        public GetThread(Plate plate){
            this.plate = plate;
        }

        @Override
        public void run() {
            plate.getEgg();
        }
    }
}
