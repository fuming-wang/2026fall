package src.main.Thread;

import java.util.concurrent.PriorityBlockingQueue;


/**
 * 定时器
 * 有些逻辑并不想立刻执行，而是等到一定时间后执行
 * 使用场景：定时完成任务
 * 数据结构：优先级队列
 * 1、需要一个类来描述任务
 * 2、使用队列来组织这些任务
 * 3、来一个线程扫描队首元素，如果满足条件就弹出
 */


public class ThreadDemo11 {
    static class Task implements Comparable<Task> {
        // Runnable 中有一个 run 方法, 就可以借助这个 run 方法来描述要执行的具体的任务是啥.
        private Runnable command;
        // time 表示啥时候来执行 command, 是一个绝对时间(ms级别的时间戳)
        private long time;

        // 构造方法的参数表示: 多少毫秒之后执行. (相对时间) 这个相对时间的参数是为了用起来方便
        public Task(Runnable command, long after) {
            this.command = command;
            this.time = System.currentTimeMillis() + after;
        }

        // 执行任务的具体逻辑
        public void run() {
            command.run();
        }

        @Override
        public int compareTo(Task o) {
            return (int) (this.time - o.time);
        }
    }

    static class Worker extends Thread {
        private PriorityBlockingQueue<Task> queue = null;
        private Object mailBox = null;

        public Worker(PriorityBlockingQueue<Task> queue, Object mailBox) {
            this.queue = queue;
            this.mailBox = mailBox;
        }

        @Override
        public void run() {
            // 实现具体的线程执行的内容
            while (true) {
                try {
                    // 1. 取出队首元素, 检查时间是否到了
                    Task task = queue.take();
                    // 2. 检查当前任务时间是否到了
                    long curTime = System.currentTimeMillis();
                    if (task.time > curTime) {
                        // 时间还没到~, 就把任务再塞回队列中
                        queue.put(task);
                        synchronized (mailBox) {
                            /**
                             * 加上时间就不会一直等，到时间就不等了
                             * 有了新任务也需要扫描
                             */
                            mailBox.wait(task.time - curTime);
                        }
                    } else {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    static class Timer {
        // 为了避免忙等, 需要使用 wait 方法.
        // 使用一个单独的对象来辅助进行 wait
        // 使用 this 也行.
        private Object mailBox = new Object();

        // 定时器的基本构成, 有三个部分.
        // 1. 用一个类来描述 "任务"
        // 2. 用一个阻塞优先队列来组织若干个任务. 让队首元素就是时间最早的任务.
        //    如果队首元素时间未到, 那么其他元素也肯定不能执行.
        private PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        // 3. 用一个线程来循环扫描当前的阻塞队列的队首元素, 如果时间到, 就执行指定的任务.
        public Timer() {
            // 创建线程
            Worker worker = new Worker(queue, mailBox);
            worker.start();
        }
        // 4. 还需要提供一个方法, 让调用者能把任务给 "安排" 进来.
        //    schedule => 安排
        public void schedule(Runnable command, long after) {
            Task task = new Task(command, after);
            queue.put(task);
            synchronized (mailBox) {
                mailBox.notify();
            }
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehe");
                timer.schedule(this, 2000);
            }
        }, 2000);
    }
}
