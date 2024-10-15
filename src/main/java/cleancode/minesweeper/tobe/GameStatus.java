package cleancode.minesweeper.tobe;

/**
 * 주석의 양면성
 */
public enum GameStatus
{
	IN_PROGRESS("진행중"),
	WIN("승리"),
	LOSE("패배")
	;

	private final String description;

	GameStatus(String description)
	{
		this.description = description;
	}
}
