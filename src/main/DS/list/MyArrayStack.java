package src.main.DS.list;

import java.util.Arrays;

public class MyArrayStack<T> {
    private T[] elem;
    private int size = 0;
    public MyArrayStack(int k){
        this.elem = (T[]) new Object[k];
    }
    public MyArrayStack(){
        this.elem = (T[]) new Object[10];
    }
    public void display(){
        for(int i = 0; i < this.size; i++){
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
    }
    public boolean isFull(){
        return this.size == this.elem.length;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public void push(T data){
        if(isFull()){
            elem= Arrays.copyOf(elem,2*elem.length);
        }
        this.elem[this.size] = data;
        this.size++;
    }
    public T peek(){
        if(isEmpty()){
            System.out.println("stack is empty");
            return (T) new Object();
        }
        return this.elem[this.size - 1];
    }
    public T pop(){
        if(isEmpty()){
            System.out.println("stack is empty");
            return (T) new Object();
        }
        T data = this.elem[this.size - 1];
        this.size--;
        return data;
    }

    public static void main(String[] args) {
        MyArrayStack<Integer> myArrayStack = new MyArrayStack<>();
        myArrayStack.push(1);
        myArrayStack.push(2);
        myArrayStack.display();
        Integer peekData = myArrayStack.peek();
        System.out.println(peekData);
        Integer popData = myArrayStack.pop();
        System.out.println(popData);
        popData = myArrayStack.pop();
        System.out.println(popData);
        popData  = myArrayStack.pop();
        System.out.println(popData);
    }
}
