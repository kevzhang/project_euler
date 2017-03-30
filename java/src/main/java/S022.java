import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class S022 {

    public static int solve() {
        String input = PeResources.getResource("022.txt");
        String withoutQuotes = CharMatcher.anyOf("\"").removeFrom(input);
        ArrayList<String> names = Lists.newArrayList(Splitter.on(',').splitToList(withoutQuotes));
        Collections.sort(names);
        return IntStream.range(0, names.size())
            .map(i -> (i + 1) * Lists.charactersOf(names.get(i)).stream()
                    .mapToInt(c -> (c - 'A' + 1))
                    .sum())
            .sum();
    }
}
