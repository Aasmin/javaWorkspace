import java.util.*;

// key = String, value = integer -- Assumption
class Hashmap {
    private class Node {
        String key;
        int value;

        Node(String key, int value) {
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

    private int hashfun(String key) {
        int bi = Math.abs(key.hashCode()) % bucket.length;
        return bi;
    }

    private int findWithinBucket(String key, int bi) {
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

    public Hashmap() {
        init(4);
        size = 0;
    }

    public void put(String key, int value) {
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

    public int remove(String key) {
        int bi = hashfun(key); // hashfun return bucket index for particular key
        int di = findWithinBucket(key, bi);
        if(di == -1) {
            return -1;
        } else {
            int value = bucket[bi].remove(di).value;
            size--;
            return value;
        }
    }

    public int get(String key) {
        int bi = hashfun(key); // hashfun return bucket index for particular key
        int di = findWithinBucket(key, bi);
        if(di == -1) {
            return -1;
        } else {
            // key is present
            return bucket[bi].get(di).value;
        }
    }

    public ArrayList<String> keySet() {
        ArrayList<String> keys = new ArrayList<>();

        for(LinkedList<Node> list : bucket) {
            for(Node n : list) {
                keys.add(n.key);
            }
        }
        return keys;
    }

    public boolean containsKey(String key) {
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

public class hmap {
    public static void main(String[] args) {
        Hashmap map = new Hashmap();
        map.put("india", 1000);
        map.put("pak", 10);
        map.put("UK", 150);
        map.put("America", 400);
        map.put("Canada", 500);
        map.put("China", 10);
        map.put("india", 2000);
        map.put("india1", 1000);
        map.put("pak1", 10);
        map.put("UK1", 150);
        map.put("America1", 400);
        map.put("Canada1", 500);
        map.put("China1", 10);
        map.put("india1", 2000);

        System.out.println(map.remove("India"));

        // System.out.println(map.get("india"));
        // System.out.println(map.get("India"));
        // System.out.println(map.containsKey("India"));
        // System.out.println(map.containsKey("india"));

        map.display();

    }
}