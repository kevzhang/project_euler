import java.math.RoundingMode;

import java.util.*;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

public class Maths {

    public static ArrayList<Integer> generatePrimes(int numPrimes) {
        int sieveLimit = upperBoundNthPrime(numPrimes);
        boolean[] sieve = generateSieve(sieveLimit);
        int sieveIndex = 2;
        ArrayList<Integer> primes = Lists.newArrayListWithCapacity(numPrimes);
        while (primes.size() < numPrimes) {
            if (!sieve[sieveIndex]) {
                primes.add(sieveIndex);
            }
            sieveIndex++;
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
            curFactor++;
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
}