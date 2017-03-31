import com.google.common.collect.Sets;

import java.math.BigInteger;
import java.util.Set;

public class S029 {

    public static int solve() {
        Set<BigInteger> terms = Sets.newHashSet();
        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                terms.add(BigInteger.valueOf(a).pow(b));
            }
        }
        return terms.size();
    }

}
