import java.util.Arrays;
import java.util.Objects;

public final class Board {

    private final int size;
    private final Mark[][] grid;
    private int occupiedCells;

    public Board(int size) {
        if (size < 1) {
            throw new IllegalArgumentException(
                    "Board size must be positive"
            );
        }

        this.size = size;
        this.grid = new Mark[size][size];

        for (Mark[] row : grid) {
            Arrays.fill(row, Mark.EMPTY);
        }
    }

    public int size() {
        return size;
    }

    public boolean isInside(Position position) {
        Objects.requireNonNull(position, "position cannot be null");

        return position.row() >= 0
                && position.row() < size
                && position.col() >= 0
                && position.col() < size;
    }

    public Mark markAt(Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException(
                    "Position is outside the board"
            );
        }

        return grid[position.row()][position.col()];
    }

    public boolean isEmpty(Position position) {
        return markAt(position) == Mark.EMPTY;
    }

    // Package-private: game operations control board mutation.
    void placeMark(Position position, Mark mark) {
        Objects.requireNonNull(mark, "mark cannot be null");

        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException(
                    "Cannot place EMPTY"
            );
        }

        if (!isInside(position)) {
            throw new IllegalArgumentException(
                    "Position is outside the board"
            );
        }

        if (!isEmpty(position)) {
            throw new IllegalArgumentException(
                    "Cell is already occupied"
            );
        }

        grid[position.row()][position.col()] = mark;
        occupiedCells++;
    }

    public boolean isFull() {
        return occupiedCells == size * size;
    }

    public Mark[][] snapshot() {
        Mark[][] copy = new Mark[size][size];

        for (int row = 0; row < size; row++) {
            copy[row] = grid[row].clone();
        }

        return copy;
    }

    void clear() {
        for (Mark[] row : grid) {
            Arrays.fill(row, Mark.EMPTY);
        }

        occupiedCells = 0;
    }
}
