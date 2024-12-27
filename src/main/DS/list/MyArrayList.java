package src.main.DS.list;

import java.util.Arrays;

/**
 * 实现一个ArrayList的核心功能并使用泛型进行测试
 *
 * */
public class MyArrayList<T> {
    private T[] elem; // 存储数组
    private int usedSize; // 数组使用了多少
    private MyArrayList(){
        // 默认初始化为10
        this.elem = (T[]) new Object[10];
    }

    public int size(){
        return this.usedSize;
    }
    // for debug
    public void display(){
        for(int i = 0; i < this.usedSize; i++){
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
    }
    public void add(int pos, T data){
        if(pos < 0 || pos > this.usedSize){
            System.out.println("input position error");
            return;
        }
        if(isFull()){
            this.elem = Arrays.copyOf(this.elem, (this.elem.length + this.elem.length << 1) >> 1);
        }
        for(int i = this.usedSize - 1; i >= pos; i--){
            this.elem[i+1] = this.elem[i];
        }
        this.elem[pos] = data;
        this.usedSize++;

    }
    public void add(T data){
        if(isFull()){
            this.elem = Arrays.copyOf(this.elem, (this.elem.length + this.elem.length << 1) >> 1);
        }
        this.elem[this.usedSize] = data;
        this.usedSize++;
    }
    public boolean isFull(){
        return this.usedSize == elem.length;
    }
    public boolean isEmpty(){
        return this.usedSize == 0;
    }

    public boolean contains(T toFind){
        for(T e: this.elem){
            // 不能使用 ==
            if(e.equals(toFind)){
                return true;
            }
        }
        return false;
    }
    public int search(T toFind) {
        for (int i = 0; i < elem.length; i++) {
            if(this.elem[i] == toFind) {
                return i;
            }
        }
        return -1;
    }
    public T getPos(int pos){
        if(pos < 0 || pos > this.usedSize){
            System.out.println("error position");
            return (T) new Object();
        }
        if (isEmpty()){
            System.out.println("empty list");
            return (T) new Object();
        }
        return this.elem[pos];
    }
    public void setPos(int pos, T value) {
        if(isEmpty()){
            System.out.println("empty list");
            return;
        }
        if(pos < 0 || pos >= this.usedSize){
            System.out.println("error position");
        }else{
            this.elem[pos] = value;
            System.out.println("success");
        }
    }
    //删除第一次出现的关键字key
    public void remove(T toRemove) {
        if(isEmpty()){
            System.out.println("empty list");
            return;
        }
        int pos=this.search(toRemove);
        if(pos == -1){
            System.out.println("not find");
        }else{
            for(int i = pos; i < this.usedSize - 1; i++){
                this.elem[i] = this.elem[i + 1];
            }
            this.usedSize--;
            System.out.println("delete success");
        }
    }
    // 清空顺序表
    public void clear() {
        for(int i = 0; i < this.usedSize; i++){
            this.elem[i] = (T) new Object();
        }
        this.usedSize = 0;
        System.out.println("clear all success");
    }

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(1223);
        arrayList.display();
        System.out.println(arrayList.contains(1223));
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.display();
        System.out.println(arrayList.getPos(0));
        System.out.println(arrayList.getPos(1));
        System.out.println(arrayList.getPos(3));
        arrayList.setPos(3, 4);
        System.out.println(arrayList.getPos(3));
        arrayList.remove(4);
        arrayList.display();
        arrayList.clear();
        arrayList.display();


    }
}
