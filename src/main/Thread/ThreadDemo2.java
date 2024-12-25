package src.main.Thread;

import com.sun.nio.sctp.SctpSocketOption;

import java.util.ArrayList;
import java.util.List;

/**
 * 证明线程能够加速执行
 * */
public class ThreadDemo2 {
    private static long count= 1000000000L;
    public static void main(String[] args) {
        int number = 10;
        long start = System.currentTimeMillis();
        addNumber(number);
        long end = System.currentTimeMillis();
        System.out.println("one thread add time is " + (end - start));
        start = System.currentTimeMillis();
        try {
            addNumberThread(number);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        end = System.currentTimeMillis();
        System.out.println("two thread add time is " + (end - start));

    }
    public static void addNumber(int number){
        for(int n = 0; n < number; n++){
            int tmp = 0;
            for(long i = 0; i < count; i++){
                if((i&1) == 1){
                    tmp++;
                }
            }
            for (long i = 0; i < count; i++){
                if((i&1) == 1){
                    tmp--;
                }
            }
        }
    }
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            int tmp=0;
            for(long i=0;i<count;i++){
                if((i&1)==1){
                    tmp--;
                }
            }
        }
    }
    public static void addNumberThread(int number) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for(int i = 0; i < 2*number; i++){
            list.add(new Thread(new MyRunnable()));
        }

        for(int i = 0; i < list.size(); i++){
            list.get(i).start();
        }
        for (int i = 0; i < list.size(); i++){
            list.get(i).join();
        }
    }
}
