import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class questions {

    static void kSmallest1(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        for(int j = 0; j < k; j++) {
            System.out.print(pq.peek() + " ");
            pq.remove();
        }
    }
    
    static void kSmallest2(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int j = 0; j < k; j++) {
            pq.add(arr[j]);
        }
        for(int i = k; i < arr.length; i++) {
            if(pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        for(int i = 0; i < k; i++) {
            System.out.print(pq.remove() + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 3, 5, 9, 5, 2, 55};
        kSmallest2(arr, 4);
    }
}