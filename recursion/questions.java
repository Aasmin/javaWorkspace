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


    public static void main(String[] args) {
        // int[] candidates = {10,1,2,7,6,1,5};
        // List<List<Integer>> ans = combinationSum2(candidates, 8);
        for(List<Integer> arr : c) {
            for(int ele : arr){
                System.out.println(ele);
            }
        }
    }
}