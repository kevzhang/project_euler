import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;

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