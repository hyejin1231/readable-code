package cleancode.minesweeper.tobe.cell;

/**
 * (3) LSP : 리스코프 치환의 원칙
 * -> Cell 부모-상속 클래스로 분리
 */
public class NumberCell extends Cell
{
	private final int nearbyLandMineCount; // 근처 지뢰 숫자

	public NumberCell(int nearbyLandMineCount)
	{
		this.nearbyLandMineCount = nearbyLandMineCount;
	}

	@Override
	public boolean isLandMine()
	{
		return false;
	}

	@Override
	public boolean hasLandMineCount()
	{
		return true;
	}

	@Override
	public String getSign()
	{
		if (isOpened)
		{
			return String.valueOf(nearbyLandMineCount);
		}
		if (isFlagged)
		{
			return FLAG_SIGN;
		}
		return UNCHECKED_SIGN;
	}
}
