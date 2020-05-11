public class bits {
    public static void main(String[] args) {
        // System.out.println(countAllSetBits_03(-1));
        int[] arr = {2, 2, 3, 4, 2, 2, 4};
        System.out.println(uniqueNumbInArray(arr));
    }

    public static int countAllSetBits(int num) {
        int count = 0;
        for(int i = 0; i < 32; i++) {
            int mask = (1 << i);
            if((num & mask) != 0)   count++;
        }
        return count;
    }

    public static int countAllSetBits_02(int num) {
        int count = 0;
        while(num != 0) {
            if((num & 1) == 1) count++;
            num = (num >>> 1);
        }
        return count;
    }

    public static int countAllSetBits_03(int num) {
        int count = 0;
        while(num != 0) {
            count++;
            num &= (num - 1);
        }
        return count;
    }

    public static int uniqueNumbInArray(int[] arr) {
        int res = 0;
        for(int ele : arr) {
            res = (res ^ ele);
        }
        return res;
    }
}