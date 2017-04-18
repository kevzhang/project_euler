import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Main {

    private static final int LIMIT = 200;

    // APPEND AS PROBLEMS ARE COMPLETED
    private static final String[] ANS = new String[] {
            "233168", "4613732", "6857", "906609", "232792560", "25164150", "104743", "23514624000", "31875000",
            "142913828922", "70600674", "76576500", "5537376230", "837799", "137846528820", "1366", "21124", "1074",
            "171", "648", "31626", "871198282", "4179871", "2783915460", "4782", "983", "-59231", "669171001", "9183",
            "443839", "73682", "45228", "100", "40730", "55", "872187", "748317", "932718654", "840", "210", "7652413",
            "162", "16695334890", "5482660", "1533776805", "5777", "134043", "9110846700", "296962999629", "997651",
            "121313", "142857", "4075", "376", "249", "972", "153", "26241", "107359", "26033", "28684",
            "127035954683", "49", "1322", "272", "661"};

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Running all solutions");
            int totalTimeMs = 0;
            for (int i = 1; i <= LIMIT; i++) {
                String className = String.format("S%03d", i);
                Class<?> clazz;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    System.out.println("Total time: " + totalTimeMs + "ms");
                    return;
                }
                Stopwatch start = Stopwatch.createStarted();
                String ans = String.valueOf(clazz.getMethod("solve").invoke(null));
                long elapsedMs = start.elapsed(TimeUnit.MILLISECONDS);
                totalTimeMs += elapsedMs;
                String output = i + ": " + ans + " (" + elapsedMs + "ms)";
                if (i <= ANS.length) {
                    if (!ans.equals(ANS[i - 1])) {
                        output += " ** INCORRECT **";
                    }
                } else {
                    output += " * UNVERIFIED *";
                }
                System.out.println(output);
            }
        } else if (args.length == 1) {
            int index;
            Class<?> clazz;
            try {
                index = Integer.parseInt(args[0]);
                String className = String.format("S%03d", index);
                clazz = Class.forName(className);
            } catch (NumberFormatException | ClassNotFoundException e) {
                throw new IllegalArgumentException("Cannot resolve solution: " + args[0]);
            }
            Stopwatch start = Stopwatch.createStarted();
            String ans = String.valueOf(clazz.getMethod("solve").invoke(null));
            long elapsedMs = start.elapsed(TimeUnit.MILLISECONDS);
            String output = index + ": " + ans + " (" + elapsedMs + "ms)";
            if (index <= ANS.length) {
                if (!ans.equals(ANS[index - 1])) {
                    output += " ** INCORRECT **";
                }
            } else {
                output += " * UNVERIFIED *";
            }
            System.out.println(output);
        } else {
            throw new IllegalArgumentException("Num args exceeds 1");
        }
    }
}
