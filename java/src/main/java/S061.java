public class S061 {

    private static final int MIN = 1000;
    private static final int LIMIT = 10 * 1000;
    private static final int N_FUNCTIONS = 6;

    public static int solve() {
        boolean[][] table = new boolean[N_FUNCTIONS][LIMIT];
        setupTables(table);
        return initSearch(table);
    }

    private static int initSearch(boolean[][] table) {
        boolean[] usedFIdx = new boolean[N_FUNCTIONS];
        int[] candidates = new int[N_FUNCTIONS];
        int firstFIdx = 0;
        usedFIdx[firstFIdx] = true;
        int searchResult = -1;
        for (int search = MIN; search < LIMIT; search++) {
            // If second to last digit is 0, skip
            if ((search / 10) % 10 == 0) {
                continue;
            }
            if (table[firstFIdx][search]) {
                candidates[0] = search;
                searchResult = Math.max(searchResult, search(table, usedFIdx, candidates, 1));
            }
        }
        return searchResult;
    }

    private static int search(boolean[][] table, boolean[] usedFIdx, int[] candidates, int size) {
        if (size == N_FUNCTIONS - 1) {
            // Last candidate is forced
            candidates[size] = 100 * (candidates[size - 1] % 100) + candidates[0] / 100;
            int lastFIdx = -1;
            for (int i = 0; i < N_FUNCTIONS; i++) {
                if (!usedFIdx[i]) {
                    lastFIdx = i;
                    break;
                }
            }
            if (table[lastFIdx][candidates[size]]) {
                return Maths.sum(candidates);
            } else {
                return -1;
            }
        } else {
            int prevBase = 100 * (candidates[size - 1] % 100);
            int searchResult = -1;
            for (int fIdx = 0; fIdx < N_FUNCTIONS; fIdx++) {
                if (usedFIdx[fIdx]) {
                    continue;
                }
                // of the last 2 digits, the first should be positive (start @ 10)
                for (int twoDigitSearch = 10; twoDigitSearch < 100; twoDigitSearch++) {
                    int combined = prevBase + twoDigitSearch;
                    if (table[fIdx][combined]) {
                        usedFIdx[fIdx] = true;
                        candidates[size] = combined;
                        searchResult = Math.max(searchResult, search(table, usedFIdx, candidates, size + 1));
                        usedFIdx[fIdx] = false;
                    }
                }
            }
            return searchResult;
        }
    }

    private static void setupTables(boolean[][] table) {
        for (int fIdx = 0; fIdx < N_FUNCTIONS; fIdx++) {
            for (int i = 1; ; i++) {
                int val = generate(fIdx, i);
                if (val < LIMIT) {
                    table[fIdx][val] = true;
                } else {
                    break;
                }
            }
        }
    }

    // Keeping a collection of anonymous functions is much slower :P
    private static int generate(int funIdx, int n) {
        switch (funIdx) {
            case 0: return Maths.triangleNumber(n);
            case 1: return n * n;
            case 2: return Maths.pentagonalNumber(n);
            case 3: return Maths.hexagonalNumber(n);
            case 4: return Maths.heptagonalNumber(n);
            case 5: return Maths.octagonalNumber(n);
            default: throw new IllegalArgumentException("Invalid idx: " + funIdx);
        }
    }
}
