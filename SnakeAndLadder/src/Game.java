import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;

    Game (Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.dice = new Dice(6);
    }

    Game (Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = players;
        this.dice = dice;
    }

    public void startGame(){
        if(players.isEmpty()) return;
        boolean gameWon = false;
        while (!gameWon) {
            for (Player player : players) {
                int roll = dice.roll();
                System.out.println(player.getName() + " rolled a " + roll);
                board.movePlayer(player, roll);
                System.out.println(player.getName() + " is now at position " + player.getPosition());

                if (board.isWinner(player)) {
                    gameWon = true;
                    break;
                }
            }
        }
    }

    public void resetGame(){
        for (Player player : players) {
            player.setPosition(0);
        }
    }
}
