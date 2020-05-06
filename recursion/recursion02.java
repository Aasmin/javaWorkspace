package recursion;

public class recursion02 {
   
    public static void main(String[] args){
        coinChange();
    }

    static void coinChange() {
        int[] coins = {2, 3, 5, 7};
        System.out.println(permutations_INF(coins, 10, ""));
    }

    static int permutations_INF(int[] arr, int tar, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int coin : arr) {
            if(tar - coin >= 0){
            count += permutations_INF(arr, tar - coin, ans + " " + coin);
            }
        }
        return count;
    }

}