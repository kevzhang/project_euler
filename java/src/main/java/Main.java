import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Main {

    private static final int LIMIT = 200;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Running all solutions");
            for (int i = 1; i <= LIMIT; i++) {
                String className = String.format("S%03d", i);
                Class<?> clazz;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    return;
                }
                Stopwatch start = Stopwatch.createStarted();
                Object ans = clazz.getMethod("solve").invoke(null);
                long elapsedMs = start.elapsed(TimeUnit.MILLISECONDS);
                System.out.println(i + ": " + ans + " (" + elapsedMs + "ms)");
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
            Object ans = clazz.getMethod("solve").invoke(null);
            long elapsedMs = start.elapsed(TimeUnit.MILLISECONDS);
            System.out.println(index + ": " + ans + " (" + elapsedMs + "ms)");
        } else {
            throw new IllegalArgumentException("Num args exceeds 1");
        }
    }
}
