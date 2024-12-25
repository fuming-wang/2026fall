package src.main.Thread;

import java.util.Scanner;


/**
 * 线程不安全的示例
 * **/
public class ThreadDemo6 {
    public static void main(String[] args) throws InterruptedException {
//        demo1();
//        demo2();
        demo3();
    }
    static class Counter{
        public int cnt = 0;
        public void add(){
            cnt++;
        }
        synchronized public void increase(){
            cnt++;
        }
    }
    public static void demo1() throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
//                    counter.add();
                    counter.increase();
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
//                    counter.add();
                    counter.increase();
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.cnt);
    }
    public static void demo2(){
        Object locker1 = new Object();
        Object locker2 = new Object();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                synchronized (locker1) {
                    System.out.println("请输入一个整数");
                    int num = scanner.nextInt(); // 用户如果不输入, 就会一直阻塞在 nextInt, 这个锁就会被一直占有
                    System.out.println("num = " + num);
                }
            }
        };
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (locker1) {
                        System.out.println("线程2 获取到锁啦");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t2.start();
    }
    static class MyThread implements Runnable{
        private int ticketNumber = 500;
        @Override
        public void run() {
            while (true){
                synchronized (this){
                    if(this.ticketNumber > 0){
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ", 还有" +
                                --this.ticketNumber + " 张票");
                    }else{
                        break;
                    }
                }
            }

        }
    }
    public static void demo3() throws InterruptedException {
        MyThread mt = new MyThread() ;
        Thread t1 = new Thread(mt,"黄牛A");
        Thread t2 = new Thread(mt,"黄牛B");
        Thread t3 = new Thread(mt,"黄牛C");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
