package src.main.DS.list;

import java.nio.channels.NotYetBoundException;
import java.util.Queue;

public class MyCircularQueue<T> {
    private T[] elem;
    private int size = 0;
    private int front = 0;
    public MyCircularQueue(){
        this.elem = (T[]) new Object[10];
    }

    // 我们认为front指向第一个元素，而rear指向最后一个元素
    private int rear = -1;
    public void EnQueue(T data){
        if(isFull()){
            // 没有实现扩容机制
            return;
        }
        this.rear++;
        this.elem[this.rear] = data;
        this.size++;
    }
    public T DeQueue(){
        if(isEmpty()){
            this.front = 0;
            this.rear = -1;
            System.out.println("queue is empty");
            return (T) new Object();
        }
        T data = this.elem[rear];
        this.front++;
        this.size--;
        return data;
    }
    public T Front() {
        if(!isEmpty()){
            return this.elem[this.front];
        }else{
            return (T) new Object();
        }
    }

    public T Rear() {
        if(!isEmpty()){
            return this.elem[rear];
        }else{
            return (T) new Object();
        }
    }
    public boolean isFull(){
        return this.size == this.elem.length;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }

    public static void main(String[] args) {
        MyCircularQueue<Integer> queue = new MyCircularQueue<>();
        queue.EnQueue(1);
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        queue.EnQueue(2);
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        queue.DeQueue();
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        queue.DeQueue();
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        queue.EnQueue(3);
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        queue.EnQueue(4);
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
    }
}
