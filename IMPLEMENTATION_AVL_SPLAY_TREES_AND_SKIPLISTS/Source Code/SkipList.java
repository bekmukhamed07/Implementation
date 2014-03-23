import java.util.Random;

/**
 * Class implementing operations for Skip List
 */
public class SkipList<T extends Comparable<T>, U extends Comparable<U>> {
    private class Node {
        public T key;
        public U value;
        public long level;
        public Node next;
        public Node down;

        public Node(T key, U value, long level, Node next, Node down) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.next = next;
            this.down = down;
        }
    }

    private Node _head;
    private Random _random;
    private long _size;
    private double _p;

    private long _level() {
        long level = 0;
        while (level <= _size && _random.nextDouble() < _p) {
            level++;
        }

        return level;
    }

    public SkipList() {
        _head = new Node(null, null, 0, null, null);
        _random = new Random(40);
        _size = 0;
        _p = 0.5;
    }

    public void add(T key, U value) {
        long level = _level();
        if (level > _head.level) {
            _head = new Node(null, null, level, null, _head);
        }

        Node cur = _head;
        Node last = null;

        while (cur != null) {
            if (cur.next == null || cur.next.key.compareTo(key) > 0) {
                if (level >= cur.level) {
                    Node n = new Node(key, value, cur.level, cur.next, null);
                    if (last != null) {
                        last.down = n;
                        _size++;
                    }

                    cur.next = n;
                    last = n;
                }
                //_size++;
                cur = cur.down;
                continue;
            } else if (cur.next.key.equals(key)) {
                cur.next.value = value;
                return;
            }

            cur = cur.next;
        }
    }

    public U remove(T key) {
        U value = null;

        Node cur = _head;
        while (cur != null) {
            if (cur.next == null || cur.next.key.compareTo(key) >= 0) {
                if (cur.next != null && cur.next.key.equals(key)) {
                    value = cur.next.value;
                    cur.next = cur.next.next;
                    _size--;
                }

                cur = cur.down;
                continue;
            }

            cur = cur.next;
        }
        return value;
    }

    public U get(T key) {
        Node cur = _head;
        while (cur != null) {
            if (cur.next == null || cur.next.key.compareTo(key) > 0) {
                cur = cur.down;
                continue;
            } else if (cur.next.key.equals(key)) {
                return cur.next.value;
            }

            cur = cur.next;
        }

        return null;
    }

    public U findMin() {
        Node cur = _head;
        if (cur == null)
            return null;
        while (cur.down != null)
            cur = cur.down;
        return cur.next.value;
    }

    public U findMax() {
        Node cur = _head;
        if (cur == null)
            return null;
        while (cur.next.next != null)
            cur = cur.next;
        while (cur.down != null)
            cur = cur.down;
        while (cur.next.next != null)
            cur = cur.next;
        return cur.next.value;
    }

    public long size() {
        Node cur = _head;
        Long sizeCount = 0L;
        if (cur == null)
            return 0L;
        while (cur.down != null)
            cur = cur.down;
        while (cur.next != null) {
            sizeCount++;
            cur = cur.next;
        }

        return sizeCount;
    }

    public boolean isEmpty() {
        Node cur = _head;

        while (cur.down != null)
            cur = cur.down;
        if (cur.next == null)
            return true;
        return false;
    }

    public Long removeValue(U value) {
        Node cur = _head;
        Long tempCount = 0L;
        if (cur == null)
            return null;
        while (cur.down != null)
            cur = cur.down;
        if (cur.next != null)
            cur = cur.next;
        while (cur.next != null) {
            if ((cur.value.compareTo(value)) == 0) {
                remove(cur.key);
                tempCount++;
            }
            cur = cur.next;
        }
        return tempCount;
    }
}