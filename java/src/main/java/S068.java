public class S068 {

    public static long solve() {
        // Outer circle by necessity contains 10, and should maximize the smallest outer number (6)
        int[] outerCircle = new int[]{6, 7, 8, 9, 10};
        int[] innerCircle = new int[]{1, 2, 3, 4, 5};
        int[] outerPerm = Maths.initPermutation(5);
        long max = -1;
        do {
            int[] innerPerm = Maths.initPermutation(5);
            do {
                if (isValid(outerCircle, innerCircle, outerPerm, innerPerm)) {
                    max = Math.max(max, getNumber(outerCircle, innerCircle, outerPerm, innerPerm));
                }
            } while (Maths.incrementPermutation(innerPerm));
        } while (Maths.incrementPermutation(outerPerm));
        return max;
    }

    private static long getNumber(int[] outerCircle, int[] innerCircle, int[] outerPerm, int[] innerPerm) {
        StringBuilder number = new StringBuilder();
        int startingIdx = 0;
        while (outerCircle[outerPerm[startingIdx]] != 6) {
            startingIdx++;
        }
        for (int i = startingIdx; i < startingIdx + 5; i++) {
            number.append(outerCircle[outerPerm[i % 5]])
                    .append(innerCircle[innerPerm[i % 5]])
                    .append(innerCircle[innerPerm[(i + 1) % 5]]);
        }
        return Long.parseLong(number.toString());
    }

    private static boolean isValid(int[] outerCircle, int[] innerCircle, int[] outerPerm, int[] innerPerm) {
        for (int i = 0; i < 4; i++) {
            int sum = outerCircle[outerPerm[i]]
                    + innerCircle[innerPerm[i]] + innerCircle[innerPerm[(i + 1) % 5]];
            int nextSum = outerCircle[outerPerm[i + 1]]
                    + innerCircle[innerPerm[i + 1]] + innerCircle[innerPerm[(i + 2) % 5]];
            if (sum != nextSum) {
                return false;
            }
        }
        return true;
    }
}
