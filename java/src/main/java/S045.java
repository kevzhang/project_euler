import com.google.common.primitives.Longs;

public class S045 {

    // given in problem statement
    private static final int LOWER_T = 285;
    private static final int LOWER_P = 165;
    private static final int LOWER_H = 143;

    public static long solve() {
        int t = LOWER_T  + 1;
        int p = LOWER_P + 1;
        int h = LOWER_H + 1;

        long tN = Maths.triangleNumber(t);
        long pN = Maths.pentagonalNumber(p);
        long hN = Maths.hexagonalNumber(h);

        long min = Longs.min(tN, pN, hN);
        long max = Longs.max(tN, pN, hN);

        while (min != max) {
            while (tN < max) {
                t++;
                tN = Maths.triangleNumber(t);
            }
            while (pN < max) {
                p++;
                pN = Maths.pentagonalNumber(p);
            }
            while (hN < max) {
                h++;
                hN = Maths.hexagonalNumber(h);
            }
            min = Longs.min(tN, pN, hN);
            max = Longs.max(tN, pN, hN);
        }
        return tN;
    }
}

