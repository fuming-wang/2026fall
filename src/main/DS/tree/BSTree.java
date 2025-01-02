package main.DS.tree;

import main.DS.node.TreeNode;


/**
 * 一个BST树满足下面的条件
 * 1. 是一个二叉树
 * 2. 根节点的左子树的所有节点都小于根节点
 * 3. 根节点的右子树的所有节点都大于根节点
 * 4. 子树也满足上面的条件
 * */
public class BSTree {
    private TreeNode root;
    public TreeNode getRoot(){
        return root;
    }
    public TreeNode findKey(int key){
        TreeNode cur = root;
        while(cur != null){
            if(cur.val == key){
                return cur;
            } else if (cur.val > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }
    public void insert(int key){
        if(root == null){
            root = new TreeNode(key);
            return;
        }
        TreeNode cur = root;
        while(true){
            if(cur.val == key){
                throw new UnsupportedOperationException("the key exists");
            } else if(cur.val > key){
                if(cur.left == null){
                    cur.left = new TreeNode(key);
                    return;
                }
                cur = cur.left;
            }else {
                if(cur.right == null){
                    cur.right = new TreeNode(key);
                    return;
                }
                cur = cur.right;
            }
        }
    }
    private TreeNode getMinNode(TreeNode root){
        if(root == null){
            return null;
        }
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
    public TreeNode remove(int key, TreeNode root){
        if(root == null) return null;
        if(root.val == key){
            if(root.left == null && root.right == null){
                return  null;
            }
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            TreeNode minNode = getMinNode(root.right);
            root.right = remove(minNode.val, root.right);
            minNode.right = root.right;
            minNode.left = root.left;
            root = minNode;
        } else if (root.val > key) {
            root.left = remove(key, root.left);
        } else {
            root.right = remove(key, root.right);
        }
        return root;
    }
    public void remove(TreeNode child, TreeNode parent){
        if(child.left == null){
            if(child == this.root){
                this.root = root.right;
            } else if(child == parent.left){
                parent.left = root.right;
            } else{
                parent.left = root.left;
            }
            return;
        }
        if(child.right == null){
            if(child == this.root){
                this.root = root.left;
            } else if (child == parent.left){
                parent.left = root.right;
            } else {
                parent.right = root.left;
            }
            return;
        }
        TreeNode prev = child;
        TreeNode cur = prev.right;
        while(cur.left != null){
            prev = cur;
            cur = cur.left;
        }
        if(prev == child){
            prev.right = cur.right;
        } else {
            prev.left = null;
        }
        cur.left = child.left;
        cur.right = child.right;
        if (child == root){
            this.root = cur;
        } else if (child == parent.left){
            parent.left = cur;
        } else {
            parent.right = cur;
        }

    }



}
