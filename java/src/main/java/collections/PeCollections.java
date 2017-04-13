package collections;

import java.util.Arrays;
import java.util.Collection;

public class PeCollections {
    public static String[] toArray(Collection<String> strings) {
        String[] arr = new String[strings.size()];
        int idx = 0;
        for (String str : strings) {
            arr[idx++] = str;
        }
        return arr;
    }

    public static String[] toArray(String... strings) {
        return Arrays.copyOf(strings, strings.length);
    }

}
