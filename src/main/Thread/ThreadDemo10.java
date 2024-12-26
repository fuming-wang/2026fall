package src.main.Thread;


/**
 * 阻塞队列
 * 生产者-消费者模型
 * 生产者生产的东西应该被消费者使用，两者对应，如果有一方过快就会阻塞
 * 它是先进先出的队列
 * 入队不能满，否则就阻塞
 * 出队不能空，否则就阻塞
 */
public class ThreadDemo10 {
    static class BlockingQueue{
        private final int[] array = new int[1000];
        private int front = 0;
        private int rear = 0;
        private volatile int size = 0;

        public void put(int value){
            synchronized (this){
                if(size == array.length){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                array[rear] = value;
                rear++;
                if(rear == array.length){
                    rear = 0;
                }
                size++;
                notify();
            }

        }
        public int take(){
            synchronized (this){
                while(size==0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int ans=array[front];
                front++;
                if(front==array.length){
                    front=0;
                }
                size--;
                notify();
                return ans;
            }
        }
        public static void main(String[] args) {
            BlockingQueue blockingQueue=new BlockingQueue();
            /**
             *          搞两个线程, 分别模拟生产者和消费者.
             *          第一次, 让给消费者消费的快一些, 生产者生产的慢一些.
             *          此时就预期看到, 消费者线程会阻塞等待. 每次有新生产的元素的时候, 消费者才能消费
             *          第二次, 让消费者消费的慢一些, 生产者生产的快一些.
             *          此时就预期看到, 生产者线程刚开始的时候会快速的往队列中插入元素, 插入满了之后就会阻塞等待.
             *          随后消费者线程每次消费一个元素, 生产者才能生产新的元素.
             */
            Thread producer = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    try {
                        blockingQueue.put(i);
                        System.out.println("生产元素: " + i + "    size:" + blockingQueue.size);
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            producer.start();

            Thread consumer = new Thread(() -> {
                while (true) {
                    try {
                        int ret = blockingQueue.take();
                        System.out.println("消费元素: " + ret + "   size:" + blockingQueue.size);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            consumer.start();
        }

    }
}
