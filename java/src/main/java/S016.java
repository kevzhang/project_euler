import com.google.common.collect.Lists;

import java.math.BigInteger;

public class S016 {
    public static int solve() {
        BigInteger n = new BigInteger("2").pow(1000);
        return Lists.charactersOf(n.toString()).stream()
            .mapToInt(c -> (c - '0'))
            .sum();
    }
}
