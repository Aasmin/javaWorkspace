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
     
    public static void main(String[] args) {
        String ans = "dababbassasba";
        System.out.println(maxFreq(ans));
    }
}