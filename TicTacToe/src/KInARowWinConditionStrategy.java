public final class KInARowWinConditionStrategy
        implements WinCondition {

    private static final int[][] DIRECTIONS = {
            {0, 1},    // horizontal
            {1, 0},    // vertical
            {1, 1},    // main diagonal
            {1, -1}    // anti-diagonal
    };

    private final int target;

    public KInARowWinConditionStrategy(int boardSize, int target) {
        if (target < 1 || target > boardSize) {
            throw new IllegalArgumentException(
                    "Require 1 <= target <= boardSize"
            );
        }

        this.target = target;
    }

    @Override
    public boolean isWinningMove(Board board, Move move) {
        Position position = move.position();
        Mark mark = move.player().mark();

        if (board.markAt(position) != mark) {
            throw new IllegalArgumentException(
                    "Move does not match the board"
            );
        }

        if (target == 1) {
            return true;
        }

        for (int[] direction : DIRECTIONS) {
            int count = 1;

            count += countInDirection(
                    board, position, mark,
                    direction[0], direction[1]
            );

            count += countInDirection(
                    board, position, mark,
                    -direction[0], -direction[1]
            );

            if (count >= target) {
                return true;
            }
        }

        return false;
    }

    private int countInDirection(
            Board board,
            Position start,
            Mark mark,
            int rowStep,
            int colStep) {

        int row = start.row() + rowStep;
        int col = start.col() + colStep;
        int count = 0;

        while (row >= 0 && row < board.size()
                && col >= 0 && col < board.size()
                && board.markAt(new Position(row, col)) == mark) {

            count++;
            row += rowStep;
            col += colStep;
        }

        return count;
    }
}
