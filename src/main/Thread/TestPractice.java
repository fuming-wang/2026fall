package src.main.Thread;

import java.util.Random;

public class TestPractice {
    private static final int LENGTH=10000000;

    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
    }

    public static void method2() {
        Thread t1 = new Thread("a"){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName());
            }
        };
        Thread t2=new Thread("b"){
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName());
            }
        };
        Thread t3=new Thread("c"){
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }
    public static void method1() throws InterruptedException {
        Random random = new Random();
        int[] arr = new int[LENGTH];
        for(int i = 0; i < LENGTH; i++){
            arr[i]= random.nextInt(99) + 1;
        }
        final long[] ans = new long[2];
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < LENGTH; i+=2){
                    ans[0] += arr[i];
                }
            }
        });
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(int i = 1; i < LENGTH; i+=2){
                    ans[1] += arr[i];
                }
            }
        };
        long beginTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        System.out.println(ans[0] + ans[1]);
        System.out.println(endTime - beginTime);
    }
}
