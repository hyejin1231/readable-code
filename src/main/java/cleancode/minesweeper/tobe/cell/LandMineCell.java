package cleancode.minesweeper.tobe.cell;

/**
 * (3) LSP : 리스코프 치환의 원칙
 * -> Cell 부모-상속 클래스로 분리
 */
public class LandMineCell extends Cell
{
	private static final String LAND_MINE_SIGN = "☼";

	@Override
	public boolean isLandMine()
	{
		return true;
	}


	@Override
	public boolean hasLandMineCount()
	{
		return false;
	}

	@Override
	public String getSign()
	{
		if (isOpened)
		{
			return LAND_MINE_SIGN;
		}
		if (isFlagged)
		{
			return FLAG_SIGN;
		}
		return UNCHECKED_SIGN;
	}
}
