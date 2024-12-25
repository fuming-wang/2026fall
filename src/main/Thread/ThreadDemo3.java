package src.main.Thread;

/**
 * 查看线程的各个状态
 * */
public class ThreadDemo3 {
    public static void main(String[] args) {
        Thread t = new Thread("线程1") {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // run 方法的执行过程就代表着系统内线程的生命周期.
                // run 方法执行中, 内核的线程就存在.
                // run 方法执行完毕, 内核中的线程就随之销毁.
                System.out.println("线程要退出了!");
            }
        };

        // 这一组属性, 只要线程创建完毕, 属性就不变了.
        System.out.println(t.getName());
        System.out.println(t.getPriority());
        System.out.println(t.isDaemon());
        System.out.println(t.getId());
        // 这俩属性会随着线程的运行过程而发生改变.
        System.out.println(t.isAlive());
        System.out.println(t.isInterrupted());
        System.out.println(t.getState());

        t.start();
        while (t.isAlive()) {
            System.out.println("线程1 线程正在运行!");
            System.out.println(t.getState());
            System.out.println(t.isInterrupted());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
