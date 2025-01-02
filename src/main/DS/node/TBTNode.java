package main.DS.node;

public class TBTNode {
    public int val;
    public int ltag;
    public int rtag;
    public TBTNode left;
    public TBTNode right;
    public TBTNode(){}
    public TBTNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "TBTNode{" +
                "val=" + val +
                ", ltag=" + ltag +
                ", rtag=" + rtag +
                ", left=" + deal(left) +
                ", right=" + deal(right) +
                '}';
    }
    public String deal(TBTNode node){
        if(node==null){
            return null;
        }else{
            return node.val+"";
        }
    }
}
