import com.google.common.base.Splitter;

import java.util.List;

// same as S018
public class S067 {

    public static int solve() {
        String input = PeResources.getResource("067.txt");
        int[][] grid = parseInput(input);
        int nRows = grid.length;
        for (int r = nRows - 2; r >= 0; r--) {
            int[] prevRow = grid[r + 1];
            int[] row = grid[r];
            int nCols = row.length;
            for (int c = 0; c < nCols; c++) {
                row[c] += Math.max(prevRow[c], prevRow[c + 1]);
            }
        }
        return grid[0][0];
    }

    private static int[][] parseInput(String input) {
        List<String> lines = Splitter.on('\n').splitToList(input);
        int sizeExcludingEmpty = lines.size() - 1;
        int[][] grid = new int[sizeExcludingEmpty][];
        for (int i = 0; i < sizeExcludingEmpty; i++) {
            List<String> words = Splitter.on(' ').splitToList(lines.get(i));
            grid[i] = new int[words.size()];
            for (int j = 0; j < words.size(); j++) {
                grid[i][j] = Integer.parseInt(words.get(j));
            }
        }
        return grid;
    }
}
