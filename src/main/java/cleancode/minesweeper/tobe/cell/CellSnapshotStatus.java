package cleancode.minesweeper.tobe.cell;

/**
 * Enum의 특성과 활용
 */
public enum CellSnapshotStatus
{
	EMPTY("빈 셀"),
	FLAG("깃 발"),
	LAND_MINE("지뢰"),
	NUMBER("숫자"),
	UNCHECKED("확인 전 ")
	;

	private final String description;

	CellSnapshotStatus(String description)
	{
		this.description = description;
	}
}
