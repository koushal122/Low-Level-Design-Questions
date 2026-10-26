public class Dice {
    private final int limit;

    public Dice(int limit) {
        this.limit = limit;
    }

    public int roll() {
        return (int) (Math.random() * limit) + 1;
    }

    public int getLimit() {
        return limit;
    }
}
