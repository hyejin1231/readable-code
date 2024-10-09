package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;
import cleancode.minesweeper.tobe.position.CellPosition;

/**
 * (1) SRP : 단일 책임의 원칙
 * -> 마인스위퍼 게임과 게임을 실행하는 부분을 분리 : MineSweeper, GameApplication 분리
 *
 * (2) OCP : 개방-폐쇄의 원칙
 * -> 새로운 요구사항이 들어왔을 때, 기존의 기능을 최대한 수정하지 않고 확장이 가능해야 함
 * -> 레벨 난이도 조정 기능 추가
 *
 * (5) DIP : 의존성 역전 원칙
 * -> InputHandler, OutputHandler
 *
 * Value Object
 * -> CellPosition
 */
public class MineSweeper implements GameInitializable, GameRunnable {

    private final GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public MineSweeper(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
        gameBoard = new GameBoard(gameLevel);
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    public void run() {
        outputHandler.showGameStartComments();

        while (true) { // 게임이 시작되는 부분
            try {
                outputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    outputHandler.showGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    outputHandler.showGameLosingComment();
                    break;
                }

                CellPosition cellPosition = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellPosition, userActionInput);
            } catch (GameException e) {
                // 사용자가 의도한 Exception
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                // 사용자가 의도하지 않은 Exception
                outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }
    }


    private void actOnCell(CellPosition cellPosition, String userActionInput) {
        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flagAt(cellPosition);
            checkIfGameOver();
            return; // Early Return
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCellAt(cellPosition)) {
                gameBoard.openAt(cellPosition);
                changeGameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(cellPosition);
            checkIfGameOver();
            return; // Early Return
        }
        throw new GameException("잘못된 번호를 선택하셨니다.");
    }

    private void changeGameStatusToLose() {
        gameStatus = -1;
    }


    private boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private  boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }


    private  String getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserInput();
    }

    private CellPosition getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();

        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다.");
        }

        return cellPosition;
    }

    private  boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private  boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private  void checkIfGameOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private  void changeGameStatusToWin() {
        gameStatus = 1;
    }

}
