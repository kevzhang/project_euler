import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.stream.IntStream;

public class S059 {

    private static final int KEY_SIZE = 3;
    private static final CharMatcher LETTER_MATCHER =
            CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));

    public static long solve() {
        int[] cipher = parseInput(PeResources.getResource("059.txt"));
        int[] keys = new int[]{bestKey(cipher, 0), bestKey(cipher, 1), bestKey(cipher, 2)};
        return IntStream.range(0, cipher.length).map(i -> keys[i % 3] ^ cipher[i]).sum();
    }

    private static int bestKey(int[] cipher, int skip) {
        int bestKey = -1;
        double bestScore = -1;
        for (int i = 'a'; i <= 'z'; i++) {
            double score = tryKey(cipher, i, skip);
            if (score > bestScore) {
                bestKey = i;
                bestScore = score;
            }
        }
        return bestKey;
    }

    private static double tryKey(int[] cipher, int key, int skip) {
        int hits = 0;
        for (int i = skip; i < cipher.length; i += KEY_SIZE) {
            if (LETTER_MATCHER.matches((char) (cipher[i] ^ key))) {
                hits++;
            }
        }
        return ((double) hits) / cipher.length;
    }

    private static int[] parseInput(String input) {
        String cleaned = CharMatcher.whitespace().removeFrom(input);
        return Splitter.on(',').splitToList(cleaned).stream()
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}

