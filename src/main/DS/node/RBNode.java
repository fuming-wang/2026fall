package main.DS.node;

public class RBNode {
    public int val;
    public int color;

    public RBNode left;
    public RBNode right;
    public RBNode parent;

    public RBNode(int val,int color) {
        this.val = val;
        this.color = color;
    }

    public String getColor() {
        return color==0 ? "黑色" : "红色";
    }

    @Override
    public String toString() {
        return "RDNode{" +
                "val=" + val +
                ", color=" + getColor() +
                '}';
    }
}
