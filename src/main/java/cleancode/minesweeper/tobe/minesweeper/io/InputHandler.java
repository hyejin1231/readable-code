package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

/**
 * (5) DIP : 의존성 역전 원칙
 */
public interface InputHandler {

    UserAction getUserActionFromUser();

    CellPosition getCellPositionFromUser();
}
