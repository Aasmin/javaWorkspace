public class l002 {
    public static void main(String[] args) {
        coinChange();
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(coinChangePermutation_INF(arr, tar, ""));
        System.out.println(coinChangeComb_ONE(arr, 0, tar, ""));

    }

    public static int coinChangePermutation_INF(int[] arr, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            int coin = arr[i];
            if(tar - coin >= 0) {
                count += coinChangePermutation_INF(arr, tar - coin, ans + coin);
            }
        }

        return count;
    }

    public static int coinChangeComb_ONE(int[] arr, int idx, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++) {
            int coin = arr[i];
            if(tar - coin >= 0) {
                count += coinChangeComb_ONE(arr, idx, tar - coin, ans + coin);
            }
        }
        return count;
    }
}
