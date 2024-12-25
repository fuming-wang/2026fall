package src.main.Thread;
import java.util.Scanner;
public class ThreadDemo7 {
    static class Counter{
        public volatile int count=0;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(){
            @Override

            /**
             * 线程1的核心代码中，循环什么也没有干，只是在反复的快速地的比较循环条件
             * 在当前线程中并没有改变count的值，所以编译器错误的优化了以后直接从cpu中读取数据
             * 其他线程改变内存的数据后，但是线程1已经不会从内存中读取数据了
             * 可以通过valatile禁止优化
             * 使用场景：一个线程读，一个线程写，就需要使用valatile来防止优化
             * 对于两个线程同时写无法使用这个来解决，只能用锁来解决
             */
            public void run() {
                while(counter.count == 0){
                }
                System.out.println("线程1结束了");
            }
        };
        t1.start();
        Thread t2= new Thread(() -> {
            Scanner scanner=new Scanner(System.in);
            System.out.print("输入一个整数>>");
            counter.count=scanner.nextInt();

        });
        t2.start();
    }
}
