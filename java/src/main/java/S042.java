import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.math.DoubleMath;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class S042 {

    public static int solve() {
        String resource = PeResources.getResource("042.txt");
        return (int) parseWords(resource).stream().filter(S042::isTriangleWord).count();
    }

    private static boolean isTriangleWord(String word) {
        return isTriangleNumber(getWordValue(word));
    }

    private static boolean isTriangleNumber(int t) {
        int candidate = DoubleMath.roundToInt((Math.sqrt(1 + 8 * t) - 1) / 2, RoundingMode.DOWN);
        return Maths.triangleNumber(candidate) == t;
    }

    private static int getWordValue(String word) {
        int value = 0;
        for (char c : word.toCharArray()) {
            value += (c - 'A' + 1);
        }
        return value;
    }

    private static List<String> parseWords(String input) {
        return Splitter.on(',').splitToList(input).stream()
                .map(s -> CharMatcher.anyOf("\"").trimFrom(s))
                .collect(Collectors.toList());
    }
}

