import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.LinkedHashSet;

public class S026 {

    private static final int LIMIT = 1000;

    public static int solve() {
        int maxCycleLength = -1;
        int maxCycleDenom = -1;
        for (int i = 2; i < LIMIT; i++) {
            int curCycle = cycleLength(i);
            if (curCycle > maxCycleLength) {
                maxCycleLength = curCycle;
                maxCycleDenom = i;
            }
        }
        return maxCycleDenom;
    }

    private static int cycleLength(int denom) {
        LinkedHashSet<Integer> remainders = Sets.newLinkedHashSet();
        int adjNumerator = 10;
        while (true) {
            while (adjNumerator < denom) {
                adjNumerator *= 10;
            }
            int remainder = adjNumerator % denom;
            if (remainder == 0) {
                return 0;
            }
            if (remainders.contains(remainder)) {
                return remainders.size() - Lists.newArrayList(remainders).indexOf(remainder);
            } else {
                remainders.add(remainder);
            }
            adjNumerator = remainder;
        }
    }

}
