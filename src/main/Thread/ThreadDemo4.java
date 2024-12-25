package src.main.Thread;

/**
 * 中断一个线程
 * 1. 通过共享标志位进行中断
 * 2. 通过interrupt进行中断
 * ★★★★通过interrupt进行中断时，会抛出异常，在异常处理处对线性进行中断
 * */
public class ThreadDemo4 {
    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
//        method4();

        try {
            method5();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // 1. 通过共享标志位进行中断
    private static boolean isQuit = false;
    public static void method1(){
        Thread t = new Thread(){
            @Override
            public void run() {
                while(!isQuit){
                    System.out.println("正在执行");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                        break;
                    }
                }
                System.out.println("终止执行");
            }

        };
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("请求终止");
        isQuit = true;

    }
    public static void method2(){
        Thread t=new Thread(){
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println("正在执行");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("请求终止执行");
        t.interrupt();
    }
    // 不清除标志位
    public static void method3(){
        Thread t=new Thread(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        };
        t.start();
        System.out.println("中断启动");
        t.interrupt();
    }
    public static void method4() {
        Thread t=new Thread(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(Thread.interrupted());
                }
            }
        };
        t.start();
        System.out.println("中断启动");
        t.interrupt();
    }
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("通过异常收到了中断情况");
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().isInterrupted());
            }
        }
    }
    // 不清楚标志位即使线程在休眠也可以感受到中断
    public static void method5() throws InterruptedException {
        MyRunnable target = new MyRunnable();
        Thread thread = new Thread(target, "李四");
        thread.start();
        thread.interrupt();
    }
}
