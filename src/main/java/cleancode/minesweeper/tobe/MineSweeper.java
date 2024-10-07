package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

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
 */
public class MineSweeper implements GameInitializable, GameRunnable {

    private final GameBoard gameBoard;
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

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

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) {
                // 사용자가 의도한 Exception
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                // 사용자가 의도하지 않은 Exception
                outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }
    }


    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRowIndex, selectedColIndex);
            checkIfGameOver();
            return; // Early Return
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCell(selectedRowIndex, selectedColIndex)) {
                gameBoard.open(selectedRowIndex, selectedColIndex);
                changeGameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);
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

    private  String getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        return inputHandler.getUserInput();
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
