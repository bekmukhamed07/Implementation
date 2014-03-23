/**
 * Node class for AVL tree
 */
public class AVLNode {
    public AVLNode left;
    public AVLNode right;
    public AVLNode parent;
    public Long key;
    public int balance;
    public int height;
    public Long value;

    public AVLNode(long k, long value) {
        left = right = parent = null;
        balance = 0;
        height = 0;
        key = k;
        this.value = value;
    }

    public String toString() {
        return "" + key + " --> " + value;
    }

}

