package src.main.DS.list;


import src.main.DS.node.ListNode;

import java.util.List;

public class MyLinkedList<T> {
    private ListNode<T> dummy = new ListNode<>();
    private ListNode<T> head = dummy;
    private ListNode<T> last = dummy;
    private int size = 0;
    public void displayForward(){
        if(this.size == 0){
            System.out.println("the list is empty");
        } else {
            ListNode<T> cur = dummy.next;
            while(cur != null){
              System.out.print(cur.val + " ");
              cur = cur.next;
            }
            System.out.println();
        }
    }
    public void displayBackward(){
        if(this.size == 0){
            System.out.println("the list is empty");
        } else {
            ListNode<T> cur = dummy.next;
            while(cur.next != null){
                cur = cur.next;
            }
            while (cur != dummy){
                System.out.print(cur.val + " ");
                cur = cur.prior;
            }
            System.out.println();
        }
    }
    public void addHead(T data){
        ListNode<T> node = new ListNode<>(data);
        if(this.size == 0){
            dummy.next = node;
            node.prior = dummy;
            head = node;
            last = node;
        } else {
            node.next = head;
            node.prior = dummy;
            head.prior = node;
            dummy.next = node;
            head = node;
        }
        this.size++;
    }
    public void addLast(T data){
        ListNode<T> node = new ListNode<>(data);
        last.next = node;
        node.prior = last;
        this.last = node;
        if(this.size ==0){
            this.head = node;
        }
        this.size++;
    }
    public boolean findKey(T key) {
        if(this.size == 0) return false;
        ListNode<T> cur = head;
        while (cur != null) {
            if (cur.val.equals(key)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    public void delFirstKey(T data){
        if(this.size == 0){
            return;
        }
        ListNode<T> cur = head;
        while (cur != null){
            if(cur.val.equals(data)){
                // delete
                // 最后一个节点
                if(!(cur != this.last)){
                    this.last = this.last.prior;
                    this.last.next = null;
                } else {
                    cur.prior.next = cur.next;
                    cur.next.prior = cur.prior;
                }
                this.size--;
                break;
            }
            cur = cur.next;
        }

    }
    public void delAllKey(T data){
        if (this.size == 0) return;
        ListNode<T> cur = head;
        while (cur != null){
            if(cur.val.equals(data)){
                if(cur == this.last){
                    this.last = this.last.prior;
                    this.last.next = null;
                } else {
                    cur.prior.next = cur.next;
                    cur.next.prior = cur.prior;
                }
                this.size--;
            }
            cur = cur.next;
        }

    }
    public void indexList(int idx, T data){
        if(idx < 0 || idx > this.size){
            System.out.println("position error");
            return;
        }
        if(idx == 0){
            addHead(data);
            return;
        }
        if(idx == this.size){
            addLast(data);
            return;
        }
        ListNode<T> node = new ListNode<>(data);
        ListNode<T> cur = head;
        for(int i = 0; i < idx; i++){
            cur = cur.next;
        }
        cur.prior.next = node;
        node.prior = cur.prior;
        node.next = cur;
        cur.prior = node;

    }
    public void clear(){
        if(size == 0){
            return;
        }
        ListNode<T> cur = head;
        while(cur != null){
            ListNode curNext = cur.next;
            cur.prior = null;
            cur.next = null;
            cur = curNext;
        }
        this.size = 0;
        this.head = dummy;
        this.last = dummy;
    }

    public static void main(String[] args) {
        testMethod1();
        testMethod2();
        testMethod3();
        testMethod4();
    }
    public static void testMethod1(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.displayForward();
        list.displayBackward();

    }
    public static void testMethod2(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addHead(1);
        list.addHead(2);
        list.addLast(3);
        list.displayForward();
        list.displayBackward();
        System.out.println(list.findKey(1));
    }
    public static void testMethod3(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(1);
        list.addLast(1);
        list.addLast(4);
        list.addLast(1);
        list.displayForward();
        list.delFirstKey(1);
        list.displayForward();
        list.delAllKey(1);
        list.displayForward();
        list.delFirstKey(4);
        list.displayForward();
    }
    public static void testMethod4(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.indexList(0, 1);
        list.indexList(1, 2);
        list.indexList(2, 3);
        list.indexList(3, 4);
        list.displayForward();
        list.indexList(0, 9);
        list.indexList(1, 8);
        list.displayForward();
    }

}
