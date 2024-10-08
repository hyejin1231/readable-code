package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

/**
 * (1) SRP : 단일 책임의 원칙
 * -> 마인스위퍼 게임과 게임을 실행하는 부분을 분리 : MineSweeper, GameApplication 분리
 *
 * (2) OCP : 개방-폐쇄의 원칙
 * -> 유동적으로 런타임 시점에 원하는 난이도를 선택해서 만들 수 있도록 !!
 *
 * (5) DIP : 의존성 역전 원칙
 */
public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        MineSweeper mineSweeper = new MineSweeper(gameLevel, inputHandler, outputHandler);

        mineSweeper.initialize();
        mineSweeper.run();
    }

}
