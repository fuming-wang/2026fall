package main.DS.node;

public class AVLNode {
    public int val;
    public AVLNode left;
    public AVLNode right;
    public AVLNode parent;
    //定义为h.left-h.right，取值只有-1，0，1
    public int bf;
    public AVLNode(){}
    public AVLNode(int val){
        this.val=val;
    }
    public AVLNode(int val,int bf){
        this(val);
        this.bf = bf;
    }

}
