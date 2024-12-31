package main.DS.list;

import src.main.DS.node.ListNode;

public class MyQueue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;
    public int size(){
        return this.size;
    }
    public boolean empty(){
        return this.size == 0;
    }
    public void offer(T val){
        ListNode<T> node = new ListNode<T>(val);
        if(this.empty()){
            head = node;
            tail = node;
        }else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }
    public T poll(){
        if(this.empty()){
            throw new UnsupportedOperationException("queue is empty");
        }
        T val = head.val;
        head = head.next;
        size--;
        return val;
    }
    public T peek(){
        if (empty()){
            throw new UnsupportedOperationException("queue is empty");
        }else{
            return head.val;
        }
    }
}
