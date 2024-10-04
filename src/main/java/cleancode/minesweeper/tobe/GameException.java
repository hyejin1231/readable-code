package cleancode.minesweeper.tobe;

public class GameException extends RuntimeException{
    public GameException() {
    }

    public GameException(String message) {
        super(message);
    }
}
