import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class questions {
 //leetcode 40

 static List<List<Integer>> res;
 static List<Integer> ans;
 public static void combinationSum2_(int[] candidates, int idx, int target) {
    if(target == 0) {
        res.add(ans);
        return;
    }
    int prev = -1;
    for(int i = idx; i < candidates.length; i++) {
        if(prev == candidates[idx])
            continue;
        
        prev = candidates[i];
        if(target - candidates[i] >= 0){
            ans.add(candidates[i]);
            combinationSum2_(candidates, i + 1, target - candidates[i]);
            ans.remove(ans.size() - 1);
        }
    }
}


 public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    res = new ArrayList<List<Integer>>();
    ans = new ArrayList<Integer>(); 
    Arrays.sort(candidates);
    combinationSum2_(candidates, 0, target);
    return res;
}

//leetcode 386
public static List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    for(int i = 1; i < 10; i++) {
        lexi(i, n, res);
    }
    return res;
}

public static void lexi(int cur, int n, List<Integer> res){
    if(cur > n)     return;
    res.add(cur);
    for(int i = 0; i < 10; i++) {
        int newNum = cur * 10 + i;
        if(cur <= n) 
            lexi(newNum, n, res);
    }
}

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

static void missEle(int[] arr, int idx) {
    if(arr.length == idx) {
        // if(min == Integer.MAX_VALUE)    min = -1;
        return;
    }

    if(min > arr[idx] && arr[idx] > 0) {
        min = arr[idx];
    }
    if(max < arr[idx] && arr[idx] > 0) {
        max = arr[idx];
    }
    missEle(arr, idx + 1);
}

public static int solution(int[] A) 
    { 
        int n = A.length; 

        boolean[] present = new boolean[n + 1]; 

        for (int i = 0; i < n; i++) { 

            if (A[i] > 0 && A[i] <= n) 
                present[A[i]] = true; 
        } 

        for (int i = 1; i <= n; i++) 
            if (!present[i]) 
                return i; 

        return n + 1; 
    } 

    public static void main(String[] args) {
        // int[] candidates = {10,1,2,7,6,1,5};
        // List<List<Integer>> ans = combinationSum2(candidates, 8);
        // for(List<Integer> arr : c) {
        //     for(int ele : arr){
        //         System.out.println(ele);
        //     }
        // }
        // List<Integer> ans = lexicalOrder(20);
        // System.out.println(ans);
        // System.out.println(num);
        int[] arr = {-3,  0, -4};
        missEle(arr, 0);
        for(int i = min; i > 0; i--) {
            if(i < min)     min = i;
        }
        System.out.println(min);
        System.out.println(max);
    }
}