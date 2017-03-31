import com.google.common.math.IntMath;

import java.util.ArrayList;

public class S027 {

    public static int solve() {
        ArrayList<Integer> primes = Maths.generatePrimesBelow(1000);
        int ab = -1, longestChain = -1;
        for (int pIdx = 0; pIdx < primes.size(); pIdx++) {
            int prime = primes.get(pIdx);
            for (int a = -prime; a <= 999; a++) {
                int consec = consecPrimes(a, prime);
                if (consec > longestChain) {
                    longestChain = consec;
                    ab = a * prime;
                }
            }
        }
        return ab;
    }

    private static int consecPrimes(int a, int b) {
        for (int i = 0;;i++) {
            int output = i * i + a * i + b;
            if (output <= 1 || !IntMath.isPrime(output)) {
                return i;
            }
        }
    }

}
