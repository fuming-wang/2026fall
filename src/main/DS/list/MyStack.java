package main.DS.list;
import src.main.DS.node.ListNode;
public class MyStack<T> {
    private ListNode<T> head;
    public MyStack(){
    }
    public void push(T val){
        ListNode<T> curr=new ListNode<>(val);
        curr.next = this.head;
        this.head = curr;
    }
    public T pop(){
        if(isEmpty()){
            throw new UnsupportedOperationException("stack empty");
        }
        T val = this.head.val;
        this.head = this.head.next;
        return val;
    }
    public boolean isEmpty(){
        return this.head == null;
    }
    public T peek(){
        if(isEmpty()){
            throw new UnsupportedOperationException("stack empty");
        }else{
            return this.head.val;
        }
    }
}
