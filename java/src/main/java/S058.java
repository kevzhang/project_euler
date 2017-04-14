import com.google.common.math.IntMath;

public class S058 {

    public static int solve() {
        int nPrimes = 0;
        for (int i = 1;; i++) {
            nPrimes += primesAtLayer(i);
            if (nPrimes * 10 < 1 + (4 * i)) {
                return 2 * i + 1;
            }
        }
    }

    private static int primesAtLayer(int layer) {
        int toSquare = 2 * layer + 1;
        int corner = IntMath.pow(toSquare, 2);
        int edgeShift = toSquare - 1;
        return Maths.count(new int[]{corner, corner - edgeShift, corner - 2 * edgeShift, corner - 3 * edgeShift},
                IntMath::isPrime);
    }
}
