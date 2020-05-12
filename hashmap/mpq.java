import java.util.*;

class medianPQ {

    // left is maximum priority Queue
    private PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    // right is minimum Priority Queue
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    private void handleBalance() {
        if (left.size() - right.size() == 2) {
            int rem = left.remove();
            right.add(rem);
        } else if (right.size() - left.size() == 2) {
            int rem = right.remove();
            left.add(rem);
        }
    }

    public void add(int val) {
        if (left.size() > 0 && left.peek() > val) {
            left.add(val);
        } else {
            right.add(val);
        }

        handleBalance();
    }

    public int remove() {
        if (left.size() >= right.size()) {
            return left.remove();
        } else {
            return right.remove();
        }
    }

    public int peek() {
        if (left.size() >= right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }

    public int size() {
        return left.size() + right.size();
    }
}

public class mPQ {
    public static void main(String[] args) {
        medianPQ pq = new medianPQ();
        pq.add(10);
        pq.add(20);
        pq.add(5);
        pq.add(19);
        System.out.println(pq.peek());
        pq.add(50);
        System.out.println(pq.peek());
        System.out.println(pq.remove());
        System.out.println(pq.peek());
        pq.add(3);
        pq.add(2);
        System.out.println(pq.peek());
    }
}