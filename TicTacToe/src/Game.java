import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Game {

    private final Board board;
    private final List<Player> players;
    private final WinCondition winCondition;

    private int currentPlayerIndex;
    private GameStatus status;
    private Player winner;

    public Game(
            int boardSize,
            int target,
            List<Player> players,
            WinCondition winCondition) {

        Objects.requireNonNull(players, "players cannot be null");
        Objects.requireNonNull(
                winCondition, "winCondition cannot be null"
        );

        if (target < 1 || target > boardSize) {
            throw new IllegalArgumentException(
                    "Require 1 <= target <= boardSize"
            );
        }

        if (players.size() != 2) {
            throw new IllegalArgumentException(
                    "Exactly two players are required"
            );
        }

        this.players = List.copyOf(players);

        Set<String> ids = new HashSet<>();
        Set<Mark> marks = new HashSet<>();

        for (Player player : this.players) {
            Objects.requireNonNull(player, "player cannot be null");

            if (!ids.add(player.id())) {
                throw new IllegalArgumentException(
                        "Player IDs must be unique"
                );
            }

            if (!marks.add(player.mark())) {
                throw new IllegalArgumentException(
                        "Player marks must be distinct"
                );
            }
        }

        this.board = new Board(boardSize);
        this.winCondition = winCondition;
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
    }

    public GameStatus makeMove(
            Player player,
            Position position) {

        Objects.requireNonNull(player, "player cannot be null");
        Objects.requireNonNull(position, "position cannot be null");

        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game has ended");
        }

        Player current = players.get(currentPlayerIndex);

        if (!current.equals(player)) {
            throw new IllegalArgumentException(
                    "It is not this player's turn"
            );
        }

        // Validate and apply the move. Invalid positions or
        // occupied cells throw before the board is modified.
        board.placeMark(position, player.mark());

        Move move = new Move(player, position);

        // A winning move takes precedence over a draw.
        if (winCondition.isWinningMove(board, move)) {
            winner = player;
            status = GameStatus.WON;
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
        } else {
            currentPlayerIndex =
                    (currentPlayerIndex + 1) % players.size();
        }

        return status;
    }

    public GameStatus status() {
        return status;
    }

    public Player winner() {
        return winner;
    }

    public Player currentPlayer() {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "There is no current player after game completion"
            );
        }

        return players.get(currentPlayerIndex);
    }

    public Mark[][] boardSnapshot() {
        return board.snapshot();
    }

    public void reset() {
        board.clear();
        currentPlayerIndex = 0;
        winner = null;
        status = GameStatus.IN_PROGRESS;
    }
}
