import java.util.ArrayList;
import java.util.List;
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
        int count = 0;
        
        
        
        if(idx == arr.length) { 
            if(sum1 == sum2 && sum1 != 0) {
            System.out.println(set1 + "== " + set2);
            count++;
        }
            return count;
        }
        count += equeSet2(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " " , set2);
        count += equeSet2(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");
        count += equeSet2(arr, idx + 1, sum1, sum2, set1, set2);

        return count;
        }
    
    //laxicographical 
    public static void laxicographic(int st, int end) {
        if(st > end)   return;
        System.out.println(st);
        for(int i = 0; i < 10; i++) {
            if(st * 10 + i < end) {
                laxicographic(st * 10 + i, end);
            }
        }   

        if(st + 1 < 10) {
            laxicographic(st + 1, end);
        }
    }

    //leetcode 1079
    public int numTilePossibilities(String str) {
        if(str.length() == 0)   return 0;
        
        // boolean[] vis = new boolean[26];
        int vis = 0;
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            int chIdx = str.charAt(i) - 'A';
            int mask = (1 << chIdx);
            // if(vis[chIdx] == false) {
                // vis[chIdx] = true;
            if((vis & mask) == 0)   {
                vis ^= mask;
                String nstr = str.substring(0, i) + str.substring(i+1);
                count += numTilePossibilities(nstr) + 1;
            }
           
        }
        
        return count;
    }
    //leetcode 22
    static List<String> res;
    public static List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        generateParenthesis_(n, 0, 0, "");
        return res;
    }

    public static void generateParenthesis_(int n, int OB, int CB, String ans) {
        if(OB + CB == 2 * n) {
            res.add(ans);
            return;
        }

        if(OB < n)
            generateParenthesis_(n, OB + 1, CB, ans + "(");
        if(CB < OB)
            generateParenthesis_(n, OB, CB  + 1, ans + ")");
    }


    static void setProblem() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        // System.out.println(equeSet(arr, 0, 0, 0, "", ""));
        System.out.println(equeSet2(arr, 0, 0, 0, "", ""));

    }
    public static void main(String[] args) {
        // setProblem();
        // laxicographic(1, 1000);
        generateParenthesis(3);
        System.out.println(res);
    }
}