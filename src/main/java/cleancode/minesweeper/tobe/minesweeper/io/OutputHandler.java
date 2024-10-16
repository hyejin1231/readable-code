package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

/**
 * (5) DIP : 의존성 역전 원칙
 */
public interface OutputHandler {

    void showGameStartComments() ;

    void showBoard(GameBoard board);

    void showGameWinningComment();
    void showGameLosingComment();

    void showCommentForSelectingCell();
    void showCommentForUserAction() ;

    void showExceptionMessage(GameException e) ;

    void showSimpleMessage(String message);
}
