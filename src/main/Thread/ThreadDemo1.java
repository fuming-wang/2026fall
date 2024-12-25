package src.main.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 创建线程的5种方式
 * 1. 继承 Thread 类或者创建Thread的匿名内部类
 * 2. 实现 runnable 接口或者实现 runnable 的匿名接口
 * 3. 使用 Lambda 表达式实现
 * 4. 使用线程池实现
 * ★★★ 当 Thread 对象被创建出来的时候, 内核中并没有随之产生一个线程(PCB).
 * ★★★ 调用start后，代码执行后内核创建（线程）PCB.
 * ★★★ 执行上面run方法的代码.
 * Callable接口可以使用[线程池]和和借助FutureTask来执行
 * Callable接口是可以有返回值的接口
* */
public class ThreadDemo1 {
    public static void main(String[] args) {
        createThreadMethod1();
        createThreadMethod1_1();
        createThreadMethod2();
        createThreadMethod2_2();
        createThreadMethod3();
        createThreadMethod4();
        createThreadMethod5();
    }
    static class MyThead extends Thread{
        @Override
        public void run() {
            System.out.println("this is a new thread");
        }
    }
    public static void createThreadMethod1(){
        MyThead thead = new MyThead();
        thead.start();
    }
    public static void createThreadMethod1_1(){
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("this is a thread in noname");
            }
        };
        t.start();
    }
    static class MyRunnable implements Runnable{


        @Override
        public void run() {
            System.out.println("this is a thread in runnanle");
        }
    }
    public static void createThreadMethod2(){
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
    public static void createThreadMethod2_2(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is a thread in runnable noname");
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }
    public static void createThreadMethod3(){
        Thread t = new Thread(() -> System.out.println("this is a thread in lambda"));
        t.start();
    }
    public static void createThreadMethod4() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable c = new MyCallable("i:" + i);
            Future future = pool.submit(c);
            System.out.println("submit a callable thread:" + i);
            list.add(future);
        }
        pool.shutdown();
        for (Future future : list) {
            try {
                System.out.println("get the result from callable thread:" + future.get().toString());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class MyCallable implements Callable<String>{
        private String name;
        public MyCallable(String name){
            this.name = name;
        }
        public MyCallable(){}
        @Override
        public String call() throws Exception {
            return name;
        }
    }
    public static void createThreadMethod5(){
        Callable<String> mycallable = new MyCallable("xxxx");
        //开始线程
        FutureTask<String> futuretask= new FutureTask<String>(mycallable);
        new Thread(futuretask).start();
        try {
            futuretask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
