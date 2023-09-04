import java.util.HashMap;
import java.util.Map;

public class LongestSubstringExtractor {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLength = 0;
        Map<Character, Integer> alreadyReadChars = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char currentItem = s.charAt(i);
            Integer alreadyReadCharIndex = alreadyReadChars.get(currentItem);
            if (alreadyReadCharIndex != null) {

                alreadyReadChars.entrySet()
                        .removeIf(entry ->
                        alreadyReadCharIndex.compareTo(entry.getValue()) > 0);
            }
            alreadyReadChars.put(currentItem, i);

            int currentSize = alreadyReadChars.size();
            if (currentSize > maxLength) {
                maxLength = currentSize;
            }
        }
        return maxLength;
    }
}
