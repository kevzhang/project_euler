import java.util.ArrayList;

public class S069 {

    private static final int LIMIT = Maths.MILLION;

    /**
     * if
     *  N = P_1^a_1 * ... * P_k^a_k
     * then
     *  N / phi(N) = P_1(P_1 - 1) * ... * P_k(P_k - 1)
     *  this is maximized by multiplying the first K primes where the product is <= 1,000,000
     * @return
     */
    public static long solve() {
        ArrayList<Integer> primes = Maths.generatePrimesBelow(20);
        int product = 1;
        for (int prime : primes) {
            if (product * prime <= LIMIT) {
               product *= prime;
            } else {
                break;
            }
        }
        return product;
    }
}
