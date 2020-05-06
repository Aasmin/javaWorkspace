import java.util.*;

class PriorityQueue {
    // priority = min
    private ArrayList<Integer> list = new ArrayList<>();

    private void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void upheapify(int idx) {
        if(idx == 0)
            return;

        int ci = idx;
        int pi = (idx - 1) / 2;

        if(list.get(ci) < list.get(pi)) {
            // priority disturbed
            swap(ci, pi);
            upheapify(pi);
        }
    }

    private void downheapify(int idx) {
        int  minIdx = idx;
        int lci = 2 * idx + 1; // left child  index
        int rci = 2 * idx + 2; // right child index

        if(lci < list.size() && list.get(lci) <  list.get(minIdx)) {
            minIdx = lci;
        }

        if(rci < list.size() && list.get(rci) < list.get(minIdx)) {
            minIdx = rci;
        }

        if(minIdx != idx) {
            swap(idx, minIdx);
            downheapify(minIdx);
        }
    }
 
    public void add(int val) {
        list.add(val);
        upheapify(list.size() - 1);
    }

    public int remove() {
        int val = list.get(0);
        swap(0, list.size() - 1);
        list.remove(list.size() - 1);
        downheapify(0);
        return val;
    }

    public int peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}

public class pq {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();

        pq.add(10);
        pq.add(5);
        pq.add(20);
        pq.add(9);
        pq.add(7);
        pq.add(12);
        pq.add(17);


        while(pq.size() > 0) {
            int rem = pq.peek();
            pq.remove();
            System.out.println(rem);
        }
    }
}