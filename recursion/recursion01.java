import java.util.Scanner;
import java.util.ArrayList;

public class recursion01 {
    public static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) {
        solve();
    }
    
    static void solve() {
        // basics();
        questionSet();
    }

    static void basics() {
        int a = scn.nextInt();
        int b = scn.nextInt();
        System.out.println(fact(a));
        System.out.println(powerBtr(a, b));

    }

    public static int fact(int n) {
        if(n <= 1) return 1;
        return fact(n - 1) * n;
    }

    public static int powerBtr(int a, int b) {
        if(b == 0)  return 1;
        int halfPwr = powerBtr(a, b / 2);
        halfPwr *= halfPwr;
        return halfPwr * (b % 2 == 0 ? 1 : a);
    }

    public static int firstIndex(int[] arr, int idx, int data) {
        if(arr.length == idx)   return -1;
        if(arr[idx] == data) {
            return idx;
        } 
        return firstIndex(arr, idx + 1, data);
    }

    public static int lastIndex(int[] arr, int idx, int data) {
        if(arr.length == idx)   return -1;
        int lisa = lastIndex(arr, idx + 1, data);
        if(lisa != -1) {
            return idx;
        } else {
            if(arr[idx] == data){
                return idx;
            }   else {
                return -1;
            }
        }
    }

    public static int[] allIndexes(int[] arr, int idx, int data, int count) {
        if(arr.length == idx) {
            int[] ans = new int[count];
            return ans;
        }

        if(arr[idx] == data)    count++;
        int[] ans = allIndexes(arr, idx + 1, data, count);
        if(arr[idx] == data) {
            ans[count - 1]  = idx;
        }
        return ans;
    }


    // questionSet()========================
    
    public static void questionSet() {
        // System.out.println(permutation("abcd"));
        // System.out.println(substr("abc", ""));
        // System.out.println(permutation("aba", ""));
        System.out.println(permutationUnique("aba", ""));
    }

    public static ArrayList<String> subsequence(String str) {
        if(str.length() == 0)   {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(0);
        String nstr = str.substring(1);

        ArrayList<String> smallAns = subsequence(nstr);
        ArrayList<String> myAns = new ArrayList<>();

        for(String s : smallAns) {
            myAns.add(s);
            myAns.add(ch + s);
        }

        return myAns;
    } 

    public static ArrayList<String> permutation(String str) {
        if(str.length() == 1){
            ArrayList<String> bres = new ArrayList<>();
            bres.add(str);
            return bres;
        }

        char ch = str.charAt(0);
        String nstr = str.substring(1);

        ArrayList<String> rres = permutation(nstr);
        ArrayList<String> myAns = new ArrayList<>();

        for(String s : rres) {
            for(int i = 0; i <= s.length(); i++){
                myAns.add(s.substring(0, i) + ch + s.substring(i));
            }
        }

        return myAns;
    }

    public static int substr(String str, String ans) {
        if(str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = str.charAt(0);
        String nstr = str.substring(1);
        int count = 0;

        count += substr(nstr, ans + ch);
        count += substr(nstr, ans);

        return count;
    }

    public static int permutation(String str, String ans) {
        if(str.equals("")){     
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String nstr = str.substring(0, i) + str.substring(i + 1);
            count += permutation(nstr, ch + ans);
        }
        return count;
    }

    public static int permutationUnique(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        boolean[] vis=new boolean[26];
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(!vis[ch-'a']){
                vis[ch-'a']=true;
                String nstr=str.substring(0,i) + str.substring(i+1);
                count+=permutationUnique(nstr,ch + ans);
            }
        }
        return count;
    }
}