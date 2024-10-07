package cleancode.minesweeper.tobe.game;

import cleancode.minesweeper.tobe.game.GameRunnable;

/**
 * (4) ISP : 인터페이스 분리 원칙
 */
public class AnotherGame implements GameRunnable {
//    @Override
//    public void initialize() {
//        // 이 게임은 초기화 과정이 필요 없음,,
//    }

    @Override
    public void run() {
        // 게임 진행
    }
}
