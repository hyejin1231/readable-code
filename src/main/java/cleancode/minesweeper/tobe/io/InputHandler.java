package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.position.CellPosition;

/**
 * (5) DIP : 의존성 역전 원칙
 */
public interface InputHandler {
    String getUserInput();

    CellPosition getCellPositionFromUser();
}
