import java.util.*;

class Priority_Queue<T extends Comparable<T>> {
    // priority = min
    private ArrayList<T> list = new ArrayList<>();

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void upheapify(int idx) {
        if(idx == 0)
            return;

        int ci = idx;
        int pi = (idx - 1) / 2;

        if(list.get(ci).compareTo(list.get(pi)) < 0) {
            // priority disturbed
            swap(ci, pi);
            upheapify(pi);
        }
    }

    private void downheapify(int idx) {
        int  minIdx = idx;
        int lci = 2 * idx + 1; // left child  index
        int rci = 2 * idx + 2; // right child index

        if(lci < list.size() && list.get(lci).compareTo(list.get(minIdx)) < 0) {
            minIdx = lci;
        }

        if(rci < list.size() && list.get(rci).compareTo(list.get(minIdx)) < 0) {
            minIdx = rci;
        }

        if(minIdx != idx) {
            swap(idx, minIdx);
            downheapify(minIdx);
        }
    }
 
    public void add(T val) {
        list.add(val);
        upheapify(list.size() - 1);
    }

    public T remove() {
        T val = list.get(0);
        swap(0, list.size() - 1);
        list.remove(list.size() - 1);
        downheapify(0);
        return val;
    }

    public T peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}

public class genericPQ {

    static class demo implements Comparable<demo>{
        String name;
        int price;

        demo(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public int compareTo(demo o) {
            return this.price - o.price;
        }
    }

    public static void main(String[] args) {
        Priority_Queue<demo> pq = new Priority_Queue<>();

        pq.add(new demo("A0", 10));
        pq.add(new demo("A1", 100));
        pq.add(new demo("A2", 110));
        pq.add(new demo("A3", 1));
        pq.add(new demo("A4", 107));
        pq.add(new demo("A5", 19));
        pq.add(new demo("A6", 55));
        
        while(!pq.isEmpty()) {
            demo rem = pq.remove();
            System.out.println(rem.name + " -> " + rem.price);
        }

        // Priority_Queue<Integer> pq = new Priority_Queue<>();

        // pq.add(10);
        // pq.add(5);
        // pq.add(20);
        // pq.add(9);
        // pq.add(7);
        // pq.add(12);
        // pq.add(17);


        // while(pq.size() > 0) {
        //     int rem = pq.peek();
        //     pq.remove();
        //     System.out.println(rem);
        // }
    }
}