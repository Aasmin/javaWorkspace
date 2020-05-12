import java.util.HashMap;
import java.util.Map;

public class questions{
    static char maxFreq(String ans) {
        HashMap<Character, Integer> fMap = new HashMap<>();
        for(int i = 0; i < ans.length(); i++) {
            char ch = ans.charAt(i);
            if(fMap.containsKey(ch) == false) {
                fMap.put(ch, 1);
            } else {
                fMap.put(ch, fMap.get(ch) + 1);
            }
        }

        char res = '.';
        int maxFreq = 0;
        for(Map.Entry<Character, Integer> ele : fMap.entrySet()) {
            char key = ele.getKey();
            int val = ele.getValue();

            if(val > maxFreq) {
                maxFreq = val;
                res = key;
            }
        }
        System.out.print(maxFreq + " ");
        return res;
    }
     
    //get common element
    static void gce1(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> fMap = new HashMap<>();
        for(int val : arr1) {
            if(fMap.containsKey(val) == false) {
                fMap.put(val, 1);
            } else {
                fMap.put(val, fMap.get(val) + 1);
            }
        }

        for(int val : arr2) {
            if(fMap.containsKey(val)) {
                System.out.print(val + " ");
                fMap.remove(val);
            }
        }
    }
    
    static void gce2(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> fMap = new HashMap<>();
        for(int val : arr1) {
            if(fMap.containsKey(val) == false) {
                fMap.put(val, 1);
            } else {
                fMap.put(val, fMap.get(val) + 1);
            }
        }

        for(int val : arr2) {
            if(fMap.containsKey(val) == true && fMap.get(val) > 0) {
                System.out.print(val + " ");
                fMap.put(val,fMap.get(val) - 1);
            }
        }
    }
    public static void main(String[] args) {
        // String ans = "dababbassasba";
        // System.out.println(maxFreq(ans));

        int[] arr1 = {5, 1, 3, 2, 2, 1};
        int[] arr2 = {1, 4, 1, 2, 2, 1, 5};
        gce1(arr1, arr2);
        System.out.println();
        gce2(arr1, arr2);
        System.out.println();
    }
}