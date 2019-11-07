import java.util.Arrays;
import java.util.List;

public class Knapsack {

    private int[][] maxValues;
    private List<Item> items;

    int maximumValue(int capacity, List<Item> items) {
        maxValues = new int[items.size() + 1][capacity + 1];
        this.items = items;
        for (int[] row: maxValues) {
            Arrays.fill(row, -1);
        }
        return maxValueInRange(items.size(), capacity);
    }

    private int maxValueInRange(int row, int column) {
        if (isOutOfBounds(row, column)) {
            return 0;
        }
        int previousRow = row - 1;
        if (shouldCalculate(previousRow, column)) {
            maxValues[previousRow][column] = maxValueInRange(previousRow, column);
        }
        if (itemWeightAt(row) > column) {
            maxValues[row][column] = maxValues[previousRow][column];
        } else {
            int previousColumn = column - itemWeightAt(row);
            if (shouldCalculate(previousRow, previousColumn)) {
                maxValues[previousRow][previousColumn] =
                       maxValueInRange(previousRow, previousColumn);
            }
            maxValues[row][column] =
                    Math.max(
                            maxValues[previousRow][column],
                            maxValues[previousRow][previousColumn] + itemValueAt(row));
        }
        return maxValues[row][column];
    }

    private boolean shouldCalculate(int row, int column) {
        return maxValues[row][column] == -1;
    }

    private static boolean isOutOfBounds(int row, int column) {
        return row == 0 || column <= 0;
    }

    private int itemWeightAt(int index) {
        return items.get(index - 1).weight;
    }

    private int itemValueAt(int index) {
        return items.get(index - 1).value;
    }

}
