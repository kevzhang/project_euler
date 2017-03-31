import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

import java.util.List;

public class S033 {

    public static int solve() {
        List<int[]> curiousFractions = Lists.newArrayList();
        for (int a = 11; a < 100; a++) {
            for (int b = a + 1; b < 100; b++) {
                boolean curious = false;
                int numX = a / 10, numY = a % 10;
                int denomX = b / 10, denomY = b % 10;
                if (numY == 0 || denomY == 0) {
                    continue;
                }
                if (numX == denomX) {
                    curious |= a * denomY == b * numY;
                } else if (numX == denomY) {
                    curious |= a * denomX == b * numY;
                } else if (numY == denomX) {
                    curious |= a * denomY == b * numX;
                } else if (numY == denomY) {
                    curious |= a * denomX == b * numX;
                }
                if (curious) {
                    curiousFractions.add(new int[] {a, b});
                }
            }
        }
        int numProduct = curiousFractions.stream()
                .mapToInt(f -> f[0]).reduce((x, y) -> x * y).getAsInt();
        int denomProduct = curiousFractions.stream()
                .mapToInt(f -> f[1]).reduce((x, y) -> x * y).getAsInt();
        return denomProduct / IntMath.gcd(numProduct, denomProduct);
    }
}
