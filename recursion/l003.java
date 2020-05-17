import java.util.ArrayList;
public class l003 {


    
//EquiSet Problem
public static int equeSet(int[] arr, int sum1, int sum2, int idx, String set1, String set2) {
    if(idx == arr.length) {
        if(sum1 == sum2) {
            System.out.println(set1 + "== " + set2);
            return 1;
        }
        return 0;
    }
    int count = 0;
    count += equeSet(arr, sum1 + arr[idx], sum2, idx + 1, set1 + arr[idx] + " " , set2);
    count += equeSet(arr, sum1, sum2 + arr[idx], idx + 1, set1, set2 + arr[idx] + " ");
    count += equeSet(arr, sum1, sum2, idx + 1, set1, set2);
  
    return count;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        System.out.println(equeSet(arr, 0, 0, 0, "", ""));
        // System.out.println(equiSet(arr, 0, 0, 0, "", ""));
    }
}