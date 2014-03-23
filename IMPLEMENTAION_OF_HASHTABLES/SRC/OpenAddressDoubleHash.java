
/**
 * Class for Open Addressing using double hashing
 */
public class OpenAddressDoubleHash {
    private long TABLE_SIZE = 1000001;
    private int size = 0;
    private HashNode[] table;
    private long primeSize;

    private void expand() {
        rehash();
    }

    private void rehash() {
        HashNode[] oldArray = table;    // Create a new double-sized, empty table

        table = new HashNode[oldArray.length*2];

        // Copy table over
        for (HashNode node : oldArray)
            if (node != null)
                insert(node.key, node.value);
    }

    public OpenAddressDoubleHash() {
        primeSize = getPrime();
        table = new HashNode[(int)TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    /* Function to get prime number less than table size for hashTwo function */
    public long getPrime() {
        for (long i = TABLE_SIZE - 1; i >= 1; i--) {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }

    /* Function to get number of key-value pairs */
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /* Function to get value of a key */
    public Long find(Long key) {
        long hash1 = hashOne(key);
        long hash2 = hashTwo(key);

        while (table[((int) hash1)] != null && !table[((int) hash1)].key.equals(key)) {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        if(table[((int) hash1)] != null)
        {
            return table[((int) hash1)].value;
        }
        return null;
    }

    /* Function to insert a key value pair */
    public void insert(long key, long value) {
        if (size >= table.length)
        {
            expand();
        }
        long hash1 = hashOne(key);
        long hash2 = hashTwo(key);
        while (table[((int) hash1)] != null) {
            hash1 += hash2;
            hash1 %= table.length;
        }
        table[((int) hash1)] = new HashNode(key, value);
        size++;
    }

    /* Function to remove a key */
    public void remove(Long key) {
        long hash1 = hashOne(key);
        long hash2 = hashTwo(key);
        while (table[((int) hash1)] != null && !table[((int) hash1)].key.equals(key)) {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[((int) hash1)] = null;
        size--;
    }

    /* Function which gives a hash value for a given string */
    private long hashOne(Long x) {
        long hashVal = x.hashCode();
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }

    /* Function for double hashing */
    private long hashTwo(Long x) {
        int hashVal = x.hashCode();
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return primeSize - hashVal % primeSize;
    }
}