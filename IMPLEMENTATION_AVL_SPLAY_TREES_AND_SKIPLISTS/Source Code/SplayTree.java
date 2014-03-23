/**
 * Class implementing Splay Tree operations
 */

public class SplayTree {
    private BinaryNode root;
    private int removeCount = 0;
    private int sizeCount = 0;
    private long _size = 1;


    public SplayTree() {
        root = null;
    }

    /**
     * Insert into the tree.
     */
    public void insert(Long key, Long value) {
        BinaryNode n;
        int c;
        if (root == null) {
            root = new BinaryNode(key, value);
            return;
        }
        splay(key);
        if ((c = key.compareTo(root.key)) == 0) {
            root.value = value;
            return;
        }
        n = new BinaryNode(key, value);
        if (c < 0) {
            n.left = root.left;
            n.right = root;
            root.left = null;
        } else {
            n.right = root.right;
            n.left = root;
            root.right = null;
        }
        root = n;
        _size++;
    }

    /**
     * Remove from the tree.
     */
    public void remove(Long key) {
        BinaryNode x;
        splay(key);
        if (key.compareTo(root.key) != 0) {
            return;
        }
        // Now delete the root
        if (root.left == null) {
            root = root.right;
        } else {
            x = root.right;
            root = root.left;
            splay(key);
            root.right = x;
        }
        _size--;
    }

    /**
     * Remove from the tree.
     */
    public int removeValue(Long value) {

        BinaryNode x = root;
        int count = 0;
        count = removeValuePreOrderTraverse(x, value);
        removeCount = 0;
        return count;
    }

    /**
     * Remove all matching values from the tree.
     */
    public int removeValuePreOrderTraverse(BinaryNode head, Long v) {

        BinaryNode x = head;
        if (x == null)
            return removeCount;
        if (x.value.compareTo(v) == 0) {
            remove(x.key);
            removeCount++;
        }
        removeValuePreOrderTraverse(x.left, v);
        removeValuePreOrderTraverse(x.right, v);
        return removeCount;
    }

    /**
     * return the size of the tree.
     */
    public Long size() {
        return _size;
    }

    /**
     * return the size of the tree.
     */
    public int sizeHandler(BinaryNode head) {

        BinaryNode x = head;
        if (x == null)
            return sizeCount;
        sizeCount++;
        sizeHandler(head.left);
        sizeHandler(head.right);
        return sizeCount;
    }


    /**
     * Find the smallest item in the tree.
     */
    public Long findMin() {
        BinaryNode x = root;
        if (root == null) return null;
        while (x.left != null) x = x.left;
        splay(x.key);
        return x.value; //Modified
    }

    /**
     * Find the largest item in the tree.
     */
    public Long findMax() {
        BinaryNode x = root;
        if (root == null) return null;
        while (x.right != null) x = x.right;
        splay(x.key);
        return x.value;        //Modified
    }

    /**
     * Find an item in the tree.
     */
    public Long find(Long key) {
        if (root == null) return null;
        splay(key);
        if (root.key.compareTo(key) != 0) return null;
        return root.value;
    }

    /**
     * Test if the tree is logically empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * this method just illustrates the top-down method of
     * implementing the move-to-root operation
     */
    private void moveToRoot(Long key) {
        BinaryNode l, r, t, y;
        l = r = header;
        t = root;
        header.left = header.right = null;
        for (; ; ) {
            if (key.compareTo(t.key) < 0) {
                if (t.left == null) break;
                r.left = t;                                 /* link right */
                r = t;
                t = t.left;
            } else if (key.compareTo(t.key) > 0) {
                if (t.right == null) break;
                l.right = t;                                /* link left */
                l = t;
                t = t.right;
            } else {
                break;
            }
        }
        l.right = t.left;                                   /* assemble */
        r.left = t.right;
        t.left = header.right;
        t.right = header.left;
        root = t;
    }

    private static BinaryNode header = new BinaryNode(null, null); // For splay //Modified

    /**
     * Internal method to perform a top-down splay.
     * <p/>
     * splay(key) does the splay operation on the given key.
     * If key is in the tree, then the BinaryNode containing
     * that key becomes the root.  If key is not in the tree,
     * then after the splay, key.root is either the greatest key
     * < key in the tree, or the lest key > key in the tree.
     * <p/>
     * This means, among other things, that if you splay with
     * a key that's larger than any in the tree, the rightmost
     * node of the tree becomes the root.  This property is used
     * in the delete() method.
     */

    private void splay(Long key) {
        BinaryNode l, r, t, y;
        l = r = header;
        t = root;
        header.left = header.right = null;
        for (; ; ) {
            if (key.compareTo(t.key) < 0) {
                if (t.left == null) break;
                if (key.compareTo(t.left.key) < 0) {
                    y = t.left;                            /* rotate right */
                    t.left = y.right;
                    y.right = t;
                    t = y;
                    if (t.left == null) break;
                }
                r.left = t;                                 /* link right */
                r = t;
                t = t.left;
            } else if (key.compareTo(t.key) > 0) {
                if (t.right == null) break;
                if (key.compareTo(t.right.key) > 0) {
                    y = t.right;                            /* rotate left */
                    t.right = y.left;
                    y.left = t;
                    t = y;
                    if (t.right == null) break;
                }
                l.right = t;                                /* link left */
                l = t;
                t = t.right;
            } else {
                break;
            }
        }
        l.right = t.left;                                   /* assemble */
        r.left = t.right;
        t.left = header.right;
        t.right = header.left;
        root = t;
    }
}

