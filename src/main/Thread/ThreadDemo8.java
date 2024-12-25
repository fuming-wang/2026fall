package src.main.Thread;

import java.util.Scanner;

/**
 * wait和notify
 * 前者表示等待，后者表示通知
 */
public class ThreadDemo8 {
    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
        method3();
    }
    public static void method1() {
        Object obj = new Object();
        System.out.println("wait前");
        /**
         * 需要在锁内，加上sync锁
         */
        synchronized (obj){
            try {
                obj.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("wait后");
    }
    public static void method2(){
        Object locker = new Object();

        Thread t1 = new Thread() {
            boolean isQuit = true;
            @Override
            public void run() {
                synchronized (locker) {
                    while (isQuit) {
                        try {
                            System.out.println("wait 开始");
                            locker.wait();
                            System.out.println("wait 结束");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        isQuit = !isQuit;
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                // 如果不输入数字，很有可能线程2先执行完notify
                System.out.println("输入任意一个整数, 继续执行 notify");
                int num = scanner.nextInt();
                synchronized (locker) {
                    System.out.println("notify 开始");
                    locker.notify();
                    System.out.println("notify 结束");
                }
            }
        };
        t2.start();
        t1.start();
    }
    static class MyThread implements Runnable {
        private boolean flag;
        private Object obj;
        public MyThread(boolean flag, Object obj) {
            super();
            this.flag = flag;
            this.obj = obj;
        }
        public void waitMethod() {
            synchronized (obj) {
                try {
                    while (true) {
                        System.out.println("wait()方法开始.. " +
                                Thread.currentThread().getName());
                        obj.wait();
                        System.out.println("wait()方法结束.. " +
                                Thread.currentThread().getName());
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        public void notifyMethod() {
            synchronized (obj) {
                try {
                    System.out.println("notify()方法开始.. " + Thread.currentThread().getName());
                    obj.notify();
                    System.out.println("notify()方法结束.. " + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void run() {
            if (flag) {
                this.waitMethod();
            } else {
                this.notifyMethod();
            }
        }
    }
    public static void method3() throws InterruptedException {
        Object object = new Object();
        MyThread waitThread = new MyThread(true, object);
        MyThread notifyThread = new MyThread(false, object);
        Thread thread1 = new Thread(waitThread, "wait线程");
        Thread thread2 = new Thread(notifyThread, "notify线程");
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        System.out.println("main方法结束!!");
    }
}
