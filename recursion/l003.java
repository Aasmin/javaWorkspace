import java.util.ArrayList;
public class l003 {
//EquiSet Problem
public static int equeSet(int[] arr, int idx, int sum1, int sum2,String set1, String set2) {
    
    if(idx == arr.length) {
        if(sum1 == sum2) {
            System.out.println(set1 + "== " + set2);
            return 1;
        }
        return 0;
    }
    int count = 0;
    count += equeSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " " , set2);
    count += equeSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");
    return count;
    }

    public static int equeSet2(int[] arr, int idx, int sum1, int sum2,String set1, String set2) {
    
        if(idx == arr.length || sum1 == sum2) {
            if(sum1 == sum2) {
                System.out.println(set1 + "== " + set2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += equeSet2(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " " , set2);
        count += equeSet2(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");
        return count;
        }
    
        static void setProblem() {
            int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};
            System.out.println(equeSet(arr, 0, 0, 0, "", ""));
            // System.out.println(equeSet2(arr, 0, 0, 0, "", ""));
    
        }
    
    public static void main(String[] args) {
        setProblem();
    }
}