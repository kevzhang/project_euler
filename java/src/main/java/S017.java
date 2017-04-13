import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class S017 {

    private static final Map<Integer, String> VALUE_TO_WORD = ImmutableMap.<Integer, String>builder()
            .put(1, "one")
            .put(2, "two")
            .put(3, "three")
            .put(4, "four")
            .put(5, "five")
            .put(6, "six")
            .put(7, "seven")
            .put(8, "eight")
            .put(9, "nine")
            .put(10, "ten")
            .put(11, "eleven")
            .put(12, "twelve")
            .put(13, "thirteen")
            .put(14, "fourteen")
            .put(15, "fifteen")
            .put(16, "sixteen")
            .put(17, "seventeen")
            .put(18, "eighteen")
            .put(19, "nineteen")
            .put(20, "twenty")
            .put(30, "thirty")
            .put(40, "forty")
            .put(50, "fifty")
            .put(60, "sixty")
            .put(70, "seventy")
            .put(80, "eighty")
            .put(90, "ninety")
            .put(100, "one hundred")
            .put(200, "two hundred")
            .put(300, "three hundred")
            .put(400, "four hundred")
            .put(500, "five hundred")
            .put(600, "six hundred")
            .put(700, "seven hundred")
            .put(800, "eight hundred")
            .put(900, "nine hundred")
            .put(1000, "one thousand")
            .build();

    public static int solve() {
        return IntStream.range(1, 1001).map(S017::letterCount).sum();
    }

    private static int letterCount(int n) {
        int remaining = n;
        List<String> words = Lists.newArrayList();
        while (remaining > 0) {
            int denom = getHighestDenom(remaining);
            words.add(VALUE_TO_WORD.get(denom));
            remaining -= denom;
        }
        String word = Joiner.on(' ').join(words);
        int length = CharMatcher.whitespace().removeFrom(word.toString()).length();
        if (n > 100 && n < 1000 && n % 100 != 0) {
            length += 3;
        }
        return length;
   }

    private static int getHighestDenom(int n) {
        if (n >= 1000) {
            return 1000;
        } else if (n >= 100) {
            return (n / 100) * 100;
        } else if (n >= 20) {
            return (n / 10) * 10;
        } else {
            return n;
        }
    }
}
