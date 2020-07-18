public class heap {
    public static int height(int[] arr, int idx) {  //in terms of edges
        if(idx >= arr.length)   return -1;
        int lh = height(arr, idx * 2 + 1);
        int rh = height(arr, idx * 2 + 2);
        return Math.max(lh, rh) + 1;
    }
    public static int size(int[] arr, int idx) {  
        if(idx >= arr.length)   return 0;
        int lh = size(arr, idx * 2 + 1);
        int rh = size(arr, idx * 2 + 2);
        return lh + rh + 1;
    }
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        System.out.println(height(arr, 0));
        System.out.println(size(arr, 0));
    }
}