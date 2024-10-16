package cleancode.minesweeper.tobe.minesweeper.gamelevel;

/**
 * OCP : 개방-폐쇄의 원칙
 * -> 게임 난이도를 결정할 수 있는 새로운 요구사항을 만들기 위한 인터페이스
 */
public interface GameLevel {
    int getRowSize();

    int getColSize();

    int getLandMineCount();
}
