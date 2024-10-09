package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.BoardIndexConverter;
import cleancode.minesweeper.tobe.position.CellPosition;

import java.util.Scanner;

/**
 * SRP : Single Responsibility Principle
 * 단일 책임의 원칙에 따라 사용자의 입력을 받도록 역할 분리 -> Mineswepper, ConsoleInputHandler
 */
public class ConsoleInputHandler implements InputHandler{
    public static final Scanner SCANNER = new Scanner(System.in); // Scanner 를 상수화 해서 재사용함
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    @Override
    public String getUserInput() {
        return SCANNER.nextLine();
    }

    @Override
    public CellPosition getCellPositionFromUser() {
        String userInput = SCANNER.nextLine();

        int colIndex = boardIndexConverter.getSelectedColIndex(userInput);
        int rowIndex = boardIndexConverter.getSelectedRowIndex(userInput);
        return CellPosition.of(rowIndex, colIndex);
    }
}
