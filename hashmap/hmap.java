import java.util.*;

class HashMap {
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

    //nob = number of buckets
    private void init(int nob) {
        bucket = new LinkedList[nob];

        for(int i = 0; i < nob; i++) {
            bucket[i] = new LinkedList<Node>();
        }
    }

    private int hashfun(String key) {
        int bi = key.hashCode() % bucket.length;
        return bi;
    }

    public HashMap() {
        init(4);
        size = 0;
    }

    private int findWithinBucket(String key, int bi) {
        int di = -1;
        int i = 0;
        for (Node n : bucket[bi]) {
            if(n.key == key) {
                di = i;
                break;
            }
            i++;
        }
        return di;
    }

    public void put(String key, int value) {
        int bi = hashfun(key); //return bucket index
        int di = findWithinBucket(key, bi); //returns data index
        if(di == -1) {
            bucket[bi].addLast(new Node(key, value));
            size++;
        } else {
            bucket[bi].get(di).value = value;
        }
    }

    public int remove(String key) {
        int bi = hashfun(key); //return bucket index
        int di = findWithinBucket(key, bi); //returns data index
        if(di == -1) {
           return -1;
        } else {
            int value = bucket[bi].remove(di).value;
            size--;
            return value;
        }
    }

    public int get(String key) {
        int bi = hashfun(key); //return bucket index
        int di = findWithinBucket(key, bi); //returns data index
        if(di == -1) {
            return -1;
        } else {
            return bucket[bi].get(di).value;
        }
    }

    public ArrayList<String> keySet() {
        ArrayList<String> keys = new ArrayList<>();

        for (LinkedList<Node> list: bucket) {
            for(Node n: list) {
                keys.add(n.key);
            }
        }

        return keys;
    }

    public boolean containsKey(String key) {
        int bi = hashfun(key); //return bucket index
        int di = findWithinBucket(key, bi); //returns data index
        if(di == -1) {
            return false;
        } else {
            return true;
        }
    }
}

public class hmap {
    public static void main(String[] args) {
        System.out.println("India".hashCode());
    }
}