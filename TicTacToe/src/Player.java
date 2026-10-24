import java.util.Objects;

public record Player(String id, String name, Mark mark) {

    public Player {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(mark, "mark cannot be null");

        if (id.isBlank()) {
            throw new IllegalArgumentException("id cannot be blank");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }

        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException(
                    "A player cannot use EMPTY"
            );
        }
    }
}
