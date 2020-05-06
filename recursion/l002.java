public class l002 {
    public static void main(String[] args) {
        coinChange();
    }

    public static void queesn() {
        int[] arr = {}
    }

    public static int queensPermutations(int[] arr, int lidx, int qpsf, int tnq, String ans) {

    }


    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        System.out.println(coinChangeCombination_INF(arr, 0, tar, ""));

    }

    public static int coinChangePermutation_INF(int[] arr, int lidx, int tar, String ans) {
        if (lidx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[lidx] >= 0)
            count += coinChangePermutation_INF(arr, 0, tar - arr[lidx], ans + arr[lidx] + " ");
        count += coinChangePermutation_INF(arr, lidx + 1, tar, ans);

        return count;
    }

    public static int coinChangePermutation(int[] arr, int lidx, int tar, String ans) {
        if (lidx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[lidx] > 0 && tar - arr[lidx] >= 0) {
            int temp = arr[lidx];
            arr[lidx] = -arr[lidx];
            count += coinChangePermutation_INF(arr, 0, tar - temp, ans + temp + " ");
            arr[lidx] = -arr[lidx];
        }
        count += coinChangePermutation_INF(arr, lidx + 1, tar, ans);

        return count;
    }

    public static int coinChangeCombination_INF(int[] arr, int lidx, int tar, String ans) {
        if (lidx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[lidx] >= 0)
            count += coinChangeCombination_INF(arr, 0, tar - arr[lidx], ans + arr[lidx] + " ");
        count += coinChangeCombination_INF(arr, lidx + 1, tar, ans);

        return count;
    }

    public static int coinChangeCombination(int[] arr, int lidx, int tar, String ans) {
        if (lidx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[lidx] >= 0)
            count += coinChangeCombination(arr, lidx + 1, tar - arr[lidx], ans + arr[lidx] + " ");
        count += coinChangeCombination(arr, lidx + 1, tar, ans);

        return count;
    }

}