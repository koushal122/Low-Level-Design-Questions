import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Snake and Ladder!");

        // create a board with only 25 cells for now
        Board board = new Board(25, Map.of(
            3, 22, // ladder from 3 to 22
            5, 8,  // ladder from 5 to 8
            11, 25, // ladder from 11 to 25
            20, 24, // ladder from 20 to 24
            23, 1, // snake from 23 to 1
            21, 9, // snake from 21 to 9
            17, 4 // snake from 17 to 4
        ));

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");

        Game game= new Game(board, List.of(player1, player2, player3));

        boolean gameStart=true;

        while (gameStart) {
            game.startGame();
            System.out.println("Do you want to play again? (yes/no)");
            String response = new java.util.Scanner(System.in).nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                gameStart = false;
                System.out.println("Thanks for playing Snake and Ladder!");
            } else{
                game.resetGame();
                System.out.println("Starting a new game!");
            }
        }

    }
}