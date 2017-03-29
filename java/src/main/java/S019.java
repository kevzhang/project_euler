
public class S019 {
    public static int solve() {
        int dayOfWeek = 1;
        int nSundays = 0;
        for (int year = 1900; year <= 2000; year++) {
            for (int month = 0; month < 12; month++) {
                if (dayOfWeek == 0 && year >= 1901) {
                    nSundays++;
                }
                dayOfWeek = (dayOfWeek + numDays(year, month)) % 7;
            }
        }
        return nSundays;
    }

    private static final int[] DEFAULT_DAYS = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int numDays(int year, int month) {
        if (month != 1) {
            return DEFAULT_DAYS[month];
        } else {
            if (year % 400 == 0) {
                return 29;
            } else if (year % 100 == 0) {
                return 28;
            } else if (year % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
    }
}
