import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int totalCells;
    private final Map<Integer, Integer> jumps;

    public Board(int totalCells, Map<Integer, Integer> jumps) {
        this.totalCells = totalCells;
        this.jumps = new HashMap<>(jumps);
    }

    public int getTotalCells() {
        return totalCells;
    }

    public Map<Integer, Integer> getJumps() {
        return new HashMap<>(jumps);
    }

    public void movePlayer(Player player, int steps) {
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + steps;

        // Check for jumps (snakes or ladders)
        if (jumps.containsKey(newPosition)) {
            newPosition = jumps.get(newPosition);
        }

        if(newPosition > totalCells) {
            newPosition = currentPosition; // Ensure player doesn't exceed the board
        }else if(newPosition == totalCells) {
            // Player has won the game
            player.setPosition(newPosition);
            // You might want to handle the win condition here
            return;
        }

        player.setPosition(newPosition);
    }

    public boolean isWinner(Player player) {
        if(player.getPosition() == totalCells) {
            System.out.println(player.getName() + " has won the game!");
            return true;
        }
        return false;
    }
}
