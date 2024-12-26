package src.main.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo13 {
    public static void main(String[] args) {
        testAtomicInteger();
        testCallable();

    }
    public static void testAtomicInteger(){
        AtomicInteger num = new AtomicInteger(0);
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i = 0;i <= 1000; i++){
                    num.addAndGet(2);
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(int i = 0; i <= 1000; i++){
                    num.addAndGet(2);
                }
            }
        };
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(num);
    }
    public static void testCallable(){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for(int i = 0 ;i <= 1000; i++){
                    sum += i;
                }
                return sum;
            }
        };
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread t=new Thread(task);
        t.start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
