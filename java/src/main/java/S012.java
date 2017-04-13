public class S012 {
    public static long solve() {
        int index = 1;
        while (true) {
            if (Maths.numDivisors(Maths.triangleNumber(index)) > 500) {
                return Maths.triangleNumber(index);
            }
            index++;
        }
    }
}
