//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            int size = readBoardSize(scanner);

            Player player1 = new Player("p1", "Player 1", Mark.X);
            Player player2 = new Player("p2", "Player 2", Mark.O);

            WinCondition winCondition =
                    new KInARowWinConditionStrategy(size, size);

            Game game = new Game(
                    size,
                    size,
                    List.of(player1, player2),
                    winCondition
            );

            boolean playAgain = true;

            while (playAgain) {
                playSingleGame(scanner, game);

                playAgain = askToPlayAgain(scanner);

                if (playAgain) {
                    game.reset();
                    System.out.println("\nStarting a new game!\n");
                }
            }

            System.out.println("Thanks for playing Tic Tac Toe!");

        }
    }

    private static void playSingleGame(
            Scanner scanner,
            Game game) {

        System.out.println("Starting Tic Tac Toe!");
        printBoard(game.boardSnapshot());

        while (game.status() == GameStatus.IN_PROGRESS) {

            Player currentPlayer = game.currentPlayer();

            System.out.println(
                    "\n" + currentPlayer.name()
                            + " (" + currentPlayer.mark() + "), your turn."
            );

            Position position = readMove(scanner);

            try {
                game.makeMove(currentPlayer, position);

                printBoard(game.boardSnapshot());

            } catch (IllegalArgumentException
                     | IllegalStateException e) {

                System.out.println(
                        "Invalid move: " + e.getMessage()
                );

                System.out.println("Please try again.");

                // Same player gets another chance.
                continue;
            }
        }

        if (game.status() == GameStatus.WON) {
            System.out.println(
                    "\nCongratulations! "
                            + game.winner().name()
                            + " (" + game.winner().mark() + ") wins!"
            );
        } else {
            System.out.println("\nThe game is a draw!");
        }
    }

    private static int readBoardSize(Scanner scanner) {
        while (true) {
            System.out.print(
                    "Enter board size (e.g., 3 for 3x3): "
            );

            String input = scanner.nextLine().trim();

            try {
                int size = Integer.parseInt(input);

                if (size > 0) {
                    return size;
                }

                System.out.println(
                        "Board size must be positive."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Please enter a valid integer."
                );
            }
        }
    }

    private static Position readMove(Scanner scanner) {
        while (true) {
            System.out.print(
                    "Enter row and column (0 to "
                            + "size-1, separated by space): "
            );

            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                System.out.println(
                        "Enter exactly two integers."
                );
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                return new Position(row, col);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Row and column must be integers."
                );
            }
        }
    }

    private static void printBoard(Mark[][] board) {
        int size = board.length;

        System.out.println("\nCurrent board:");

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Mark mark = board[row][col];

                String value = mark == Mark.EMPTY
                        ? " "
                        : mark.toString();

                System.out.print(" " + value + " ");

                if (col < size - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < size - 1) {
                System.out.println("---".repeat(size)
                        .replaceAll("(?<=---)(?=---)", "+"));
            }
        }

        System.out.println();
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");

            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("yes")
                    || answer.equalsIgnoreCase("y")) {
                return true;
            }

            if (answer.equalsIgnoreCase("no")
                    || answer.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println(
                    "Please enter yes or no."
            );
        }
    }
}
