package src.main.DS.list;


import java.util.Arrays;

public class MyHeap {
    private int[] elem;
    private int usedSize;

    public MyHeap(){
        this.elem = new int[10];
    }
    public int getUsedSize() {
        return usedSize;
    }
    private void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    private void adjustDown(int k, int useSize){
        int parent = k;
        int child = 2 * k + 1;
        while(child < useSize){
            if(child + 1 < useSize && elem[child] < elem[child + 1]){
                child++;
            }
            if(elem[child] > elem[parent]){
                swap(elem, child, parent);
                parent = child;
                child = 2 * child + 1;
            }else{
                break;
            }
        }
    }
    private void adjustUp(int child){
        while(child > 0){
            int parent = (child - 1) / 2;
            if(elem[parent] < elem[child]){
                swap(elem, child, parent);
                child = parent;
            }else{
                break;
            }
        }
    }
    //入队操作
    public void offer(int val) {
        if(isFull()) {
            //扩容
            this.elem = Arrays.copyOf(this.elem,2 * this.elem.length);
        }
        this.elem[this.usedSize] = val; //10
        this.usedSize++; //11
        adjustUp(this.usedSize-1); //10下标
    }

    public void poll() {
        if(isEmpty()) {
            return;
        }
        //交换到最后删除
        swap(elem,0,this.usedSize-1);
        this.usedSize--;
        //除了第一个元素，其他都满足大堆条件，所以对零位置进行向下调整
        adjustDown(0,this.usedSize);
    }

    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return this.elem[0];
    }
    public boolean isFull() {
        return this.usedSize == this.elem.length;
    }


    public boolean isEmpty() {
        return this.usedSize == 0;
    }


}
