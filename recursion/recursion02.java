public class recursion02 {
   
    public static void main(String[] args){
        coinChange();
    }

    static void coinChange() {
        int[] coins = {2, 3, 5, 7};
        System.out.println(permutation_INF(coins, 10, ""));
    }

    static int permutation_INF(int[] coins, int tar, String ans) {
        // if(tar < 0) return 0;
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        } 
        

        int count = 0;
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            // if (tar - coin >= 0)
                count += permutation_INF(coins, tar - coin, ans + coin + " ");
        }

        return count;
    }

}