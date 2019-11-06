import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Matrix {

    private enum Axis {
        ROW(position -> position.row),
        COLUMN(position -> position.column);

        private final ToIntFunction<MatrixPosition> getIndex;

        Axis(ToIntFunction<MatrixPosition> getIndex) {
            this.getIndex = getIndex;
        }
    }

    private static class MatrixPosition {
        private final int row;
        private final int column;

        MatrixPosition(int row, int column) {
            this.row = row;
            this.column = column;
        }

        private int indexOfAxis(Axis axis) {
            return axis.getIndex.applyAsInt(this);
        }
    }

    private static class MatrixCell {
        private final MatrixPosition position;
        private final int value;

        private MatrixCell(MatrixPosition position, int value) {
            this.position = position;
            this.value = value;
        }

        private static MatrixCell fromPositionAndString(MatrixPosition position, String value) {
            return new MatrixCell(position, Integer.parseInt(value));
        }
    }

    private static final Function<String, String[][]> toNestedArray =
            (matrixAsString) -> Arrays
                    .stream(matrixAsString.split("\n"))
                    .map(row -> row.split("\\s+"))
                    .toArray(String[][]::new);

    private static final Function<String[][], List<MatrixCell>> toMatrixCells =
            (matrix) -> IntStream
                .rangeClosed(1, matrix.length)
                .mapToObj(row -> IntStream
                        .rangeClosed(1, matrix[row - 1].length)
                        .mapToObj(column -> new MatrixPosition(row, column))
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .map(position ->
                        MatrixCell.fromPositionAndString(position, matrix[position.row - 1][position.column - 1]))
                .collect(Collectors.toList());

    private static final Function<String, List<MatrixCell>> toCellsFromString = toNestedArray.andThen(toMatrixCells);

    private final List<MatrixCell> cells;

    Matrix(String matrixAsString) {
        cells = toCellsFromString.apply(matrixAsString);
    }

    int[] getRow(int rowNumber) {
        return getAxisAtIndex(Axis.ROW, rowNumber);
    }

    int[] getColumn(int columnNumber) {
        return getAxisAtIndex(Axis.COLUMN, columnNumber);
    }

    private int[] getAxisAtIndex(Axis axis, int index) {
        return cells.stream()
                .filter(cell -> index == cell.position.indexOfAxis(axis))
                .mapToInt(cell -> cell.value)
                .toArray();
    }
}
