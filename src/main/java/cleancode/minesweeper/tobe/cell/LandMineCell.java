package cleancode.minesweeper.tobe.cell;

/**
 * (3) LSP : 리스코프 치환의 원칙
 * -> Cell 부모-상속 클래스로 분리
 */
public class LandMineCell implements Cell
{
	private static final String LAND_MINE_SIGN = "☼";
	private final CellState cellState = CellState.initialize();

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
		if (cellState.isOpened())
		{
			return LAND_MINE_SIGN;
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
