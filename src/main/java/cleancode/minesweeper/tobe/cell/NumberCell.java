package cleancode.minesweeper.tobe.cell;

/**
 * (3) LSP : 리스코프 치환의 원칙
 * -> Cell 부모-상속 클래스로 분리
 */
public class NumberCell implements Cell
{
	private final int nearbyLandMineCount; // 근처 지뢰 숫자
	private final CellState cellState = CellState.initialize();

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
		if (cellState.isOpened())
		{
			return String.valueOf(nearbyLandMineCount);
		}
		if (cellState.isFlagged())
		{
			return FLAG_SIGN;
		}
		return UNCHECKED_SIGN;
	}

	@Override
	public void flag()
	{
		cellState.flag();
	}

	@Override
	public void open()
	{
		cellState.open();
	}

	@Override
	public boolean isChecked()
	{
		return cellState.isChecked();
	}

	@Override
	public boolean isOpened()
	{
		return cellState.isOpened();
	}
}
