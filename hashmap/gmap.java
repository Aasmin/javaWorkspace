import java.util.*;

// key = K, value = integer -- Assumption
class Hash_map<K, V> {
    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    LinkedList<Node>[] bucket = null;

    private void init(int nob) {
        // nob = number of bucket
        bucket = new LinkedList[nob];
        size = 0;
        for(int i = 0; i < nob; i++) {
            bucket[i] = new LinkedList<Node>();
        }
    }

    private int hashfun(K key) {
        int bi = Math.abs(key.hashCode()) % bucket.length;
        return bi;
    }

    private int findWithinBucket(K key, int bi) {
        int di = -1;
        int i = 0;
        for(Node n : bucket[bi]) {
            if(n.key == key) {
                di = i;
                break;
            }
            i++;
        }
        return di;
    }

    private void  rehash() {
        LinkedList<Node>[] ob = bucket; // preserve pointer of old bucket
        init(2 * ob.length);
        for(LinkedList<Node> list : ob) {
            for(Node n : list) {
                put(n.key, n.value);
            }
        }
    }

    public Hash_map() {
        init(4);
        size = 0;
    }

    public void put(K key, V value) {
        int bi = hashfun(key); // hashfun return bucket index for particular key
        int di = findWithinBucket(key, bi);
        if(di == -1) {
            // key is not present
            bucket[bi].addLast(new Node(key, value));
            size++;
        } else {
            // key is present
            bucket[bi].get(di).value = value;
        }
        double lambda = (size * 1.0) / bucket.length;
        if(lambda > 2) {
            rehash();
        }
    }

    public V remove(K key) {
        int bi = hashfun(key); // hashfun return bucket index for particular key
        int di = findWithinBucket(key, bi);
        if(di == -1) {
            return null;
        } else {
            V value = bucket[bi].remove(di).value;
            size--;
            return value;
        }
    }

    public V get(K key) {
        int bi = hashfun(key); // hashfun return bucket index for particular key
        int di = findWithinBucket(key, bi);
        if(di == -1) {
            return null;
        } else {
            // key is present
            return bucket[bi].get(di).value;
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for(LinkedList<Node> list : bucket) {
            for(Node n : list) {
                keys.add(n.key);
            }
        }
        return keys;
    }

    public boolean containsKey(K key) {
        int bi = hashfun(key); // hashfun return bucket index for particular key
        int di = findWithinBucket(key, bi);
        if(di == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int size() {
        return this.size;
    }

    public void display() {
        for(int b = 0; b < bucket.length; b++) {
            System.out.print("b" + b + " -> ");
            for(Node n : bucket[b]) {
                System.out.print("[" + n.key + " - " + n.value + "], ");
            }
            System.out.println();
        }
    }
}

public class gmap {
    public static void main(String[] args) {
        Hash_map<String, String> map = new Hash_map<>();
        map.put("India", "delhi");
        map.put("India1", "delhi");
        map.put("India2", "delhi");
        map.put("India3", "delhi");
        map.put("India4", "delhi");
        map.put("India12", "delhi");
        map.put("India12", "delhi");
        map.put("India22", "delhi");
        map.put("India32", "delhi");
        map.put("India42", "delhi");
        map.display();

    }
}