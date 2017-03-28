import java.util.Map;

public class S012 {
    public static long solve() {
        int index = 1;
        while (true) {
            if (numFactors(Maths.getPrimeFactors(Maths.triangleNumber(index))) > 500) {
                return Maths.triangleNumber(index);
            }
            index++;
        }
    }

    private static int numFactors(Map<Long, Integer> primeFactors) {
        int numFactors = 1;
        for (int factorMultiplicity : primeFactors.values()) {
            numFactors *= (factorMultiplicity + 1);
        }
        return numFactors;
    }
}
