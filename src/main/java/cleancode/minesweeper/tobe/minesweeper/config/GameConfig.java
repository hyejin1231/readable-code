package cleancode.minesweeper.tobe.minesweeper.config;

import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;

/**
 * 숨겨져 있는 도메인 개념 도출하기
 * -> 여기서 게임 설정 정보라는 도메인 개념 추출해서 MineSweeper에 생성자 변경을 최소화 하는 것이다.
 * -> 근데 주의점은 너무 과하게 미래를 내다보고 만들면 오버 엔지니어링이 될 수도 있다고 한다.
 */
public class GameConfig
{
	private final GameLevel gameLevel;
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;

	public GameConfig(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler)
	{
		this.gameLevel = gameLevel;
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
	}

	public GameLevel getGameLevel()
	{
		return gameLevel;
	}

	public InputHandler getInputHandler()
	{
		return inputHandler;
	}

	public OutputHandler getOutputHandler()
	{
		return outputHandler;
	}
}
