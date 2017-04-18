import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.IntPredicate;

public class Maths {

    public static final int MILLION = 1000 * 1000;

    public static ArrayList<Integer> generatePrimes(int numPrimes) {
        int sieveLimit = upperBoundNthPrime(numPrimes);
        boolean[] sieve = generateSieve(sieveLimit);
        int sieveIndex = 3;
        ArrayList<Integer> primes = Lists.newArrayListWithCapacity(numPrimes);
        primes.add(2);
        while (primes.size() < numPrimes) {
            if (!sieve[sieveIndex]) {
                primes.add(sieveIndex);
            }
            sieveIndex+=2;
        }
        return primes;
    }

    public static ArrayList<Integer> generatePrimesBelow(int limit) {
        boolean[] sieve = generateSieve(limit - 1);
        ArrayList<Integer> primes = Lists.newArrayList();
        for (int i = 0; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static long triangleNumber(long index) {
        return (index * (index + 1)) / 2;
    }

    public static int triangleNumber(int index) {
        return (index * (index + 1)) / 2;
    }

    public static long pentagonalNumber(long index) {
        return index * (3 * index - 1) / 2;
    }

    public static int pentagonalNumber(int index) {
        return index * (3 * index - 1) / 2;
    }

    public static long hexagonalNumber(long index) {
        return index * (2 * index - 1);
    }

    public static int hexagonalNumber(int index) {
        return index * (2 * index - 1);
    }

    public static long heptagonalNumber(long index) {
        return index * (5 * index - 3) / 2;
    }

    public static int heptagonalNumber(int index) {
        return index * (5 * index - 3) / 2;
    }

    public static long octagonalNumber(long index) {
        return index * (3 * index - 2);
    }

    public static int octagonalNumber(int index) {
        return index * (3 * index - 2);
    }

    public static List<Long> getDivisors(long n) {
        ArrayList<Map.Entry<Long, Integer>> entries = Lists.newArrayList(getPrimeFactors(n).entrySet());
        int[] multiplicityLimits = new int[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            multiplicityLimits[i] = entries.get(i).getValue();
        }
        int[] multiplicity = new int[entries.size()];

        List<Long> divisors = Lists.newArrayList();
        divisors.add(1L);
        while (incrementVector(multiplicity, multiplicityLimits)) {
            long curDivisor = 1L;
            for (int i = 0; i < multiplicity.length; i++) {
                curDivisor *= LongMath.pow(entries.get(i).getKey(), multiplicity[i]);
            }
            divisors.add(curDivisor);
        }
        Collections.sort(divisors);
        return divisors;
    }

    public static int[] initializeChooseVector(int size) {
        int[] vector = new int[size];
        for (int i = 0; i < size; i++) {
            vector[i] = i;
        }
        return vector;
    }

    // (0, 1) -> (0, 2) -> (0, 3) -> (1, 2) -> (1, 3) -> (2, 3)
    public static boolean incrementChooseVector(int[] vector, int limit) {
        int size = vector.length;
        for (int i = size - 1; i >= 0; i--) {
            int adjustedLimit = limit - ((size - 1) - i);
            if (vector[i] < adjustedLimit - 1) {
                vector[i]++;
                for (int j = i + 1; j < size; j++) {
                    vector[j] = vector[j - 1] + 1;
                }
                return true;
            }
        }
        return false;
    }

    // (0, 0) -> (0, 1) -> ... -> (0, 3) -> (1, 0) -> (1, 1) -> ... -> (3, 3)
    public static boolean incrementVector(int[] vector, int[] limits) {
        Preconditions.checkArgument(vector.length == limits.length, "Vectors not of same length.");
        int size = vector.length;
        for (int i = size - 1; i >= 0; i--) {
            if (vector[i] < limits[i]) {
                vector[i]++;
                for (int j = i + 1; j < size; j++) {
                    vector[j] = 0;
                }
                return true;
            }
        }
        return false;
    }

    public static int numDivisors(long n) {
        Map<Long, Integer> primeFactors = getPrimeFactors(n);
        int numFactors = 1;
        for (int multiplicity : primeFactors.values()) {
            numFactors *= (multiplicity + 1);
        }
        return numFactors;
    }

    public static Map<Long, Integer> getPrimeFactors(long n) {
        Map<Long, Integer> primeFactors = Maps.newHashMap();
        long curFactor = 2;
        long remaining = n;
        while (remaining > 1) {
            int count = 0;
            while (remaining % curFactor == 0) {
                remaining /= curFactor;
                count ++;
            }
            if (count > 0) {
                primeFactors.put(curFactor, count);
            }
            if (curFactor > 2) {
                curFactor += 2;
            } else {
                curFactor++;
            }
        }
        return primeFactors;
    }

    /**
     * false => prime
     * true => composite
     */
    public static boolean[] generateSieve(int sieveLimit) {
        boolean[] sieve = new boolean[sieveLimit + 1];
        sieve[0] = true;
        sieve[1] = true;
        int largestFactor = IntMath.sqrt(sieveLimit, RoundingMode.DOWN);
        for (int factor = 2; factor <= largestFactor; factor++) {
            // current factor is prime
            if (!sieve[factor]) {
                for (int composite = 2 * factor; composite <= sieveLimit; composite += factor) {
                    sieve[composite] = true;
                }
            }
        }
        return sieve;
    }

    public static int upperBoundNthPrime(int n) {
        double dN = (double) n;
        double upperBound;
        if (n < 7022) {
            upperBound = dN * Math.log(n) + dN * Math.log(Math.log(n));
        } else {
            upperBound = dN * Math.log(n) + dN * (Math.log(Math.log(n)) - 0.9385);
        }
        return DoubleMath.roundToInt(upperBound, RoundingMode.UP);
    }

    public static int[] initPermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }
        return permutation;
    }

    /**
     * @param a array of UNIQUE elements
     * @return true if next lexicographic permutation is achieved. false if lexicographic order is maxed out
     */
    public static boolean incrementPermutation(int[] a) {
        int inversionPoint = -1;
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                inversionPoint = i;
                break;
            }
        }
        if (inversionPoint == -1) {
            return false;
        }
        int nextBiggerIdx = inversionPoint + 1;
        for (int i = a.length - 1; i > inversionPoint; i--) {
            if (a[i] > a[inversionPoint] && a[i] < a[nextBiggerIdx]) {
                nextBiggerIdx = i;
            }
        }
        int tmp = a[inversionPoint];
        a[inversionPoint] = a[nextBiggerIdx];
        a[nextBiggerIdx] = tmp;
        Arrays.sort(a, inversionPoint + 1, a.length);
        return true;
    }

    public static int sum(int[] values, int limit) {
        int sum = 0;
        for (int i = 0; i < limit; i++) {
            sum += values[i];
        }
        return sum;
    }

    public static int sum(int[] values) {
        int sum = 0;
        for (int v : values) {
            sum += v;
        }
        return sum;
    }

    public static long[] getPartialSums(long[] values) {
        long[] partialSums = new long[values.length + 1];
        long sum = 0;
        partialSums[0] = sum;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            partialSums[i + 1] = sum;
        }
        return partialSums;
    }

    /**
     * @param n
     * @return array of exactly size 10
     */
    public static int[] getDigitFrequency(long n) {
        long remaining = n;
        int[] digitFrequency = new int[10];
        while (remaining > 0) {
            digitFrequency[(int) (remaining % 10)]++;
            remaining /= 10;
        }
        return digitFrequency;
    }

    public static int count(int[] arr, IntPredicate pred) {
        int sum = 0;
        for (int i : arr) {
            if (pred.test(i)) {
                sum ++;
            }
        }
        return sum;
    }

    /**
     * @param n >= 0
     */
    public static int[] getDigits(BigInteger n) {
        String s = n.toString();
        int[] digits = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digits[i] = s.charAt(i) - '0';
        }
        return digits;
    }

    public static int[] getDigits(long n) {
        int nDigits = LongMath.log10(n, RoundingMode.DOWN) + 1;
        long remaining = n;
        int[] digits  = new int[nDigits];
        int digitIdx = nDigits - 1;
        while (remaining > 0) {
            digits[digitIdx--] = (int) (remaining % 10);
            remaining /= 10;
        }
        return digits;
    }

    public static long digitToLong(int[] digits) {
        long number = digits[0];
        for (int i = 1; i < digits.length; i++) {
            number = number * 10 + digits[i];
        }
        return number;
    }

    public static int numDigits(BigInteger n) {
        return BigIntegerMath.log10(n, RoundingMode.DOWN) + 1;
    }

    public static boolean isSquare(long n) {
        return LongMath.pow(LongMath.sqrt(n, RoundingMode.DOWN), 2) == n;
    }

}