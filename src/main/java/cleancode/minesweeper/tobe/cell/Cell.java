package cleancode.minesweeper.tobe.cell;

/**
 * SOLID (3) : LSP 리스코프 치환 원칙
 * -> Cell, LandMineCell, NumberCell, EmptyCell 로 부모-자식 상속 관계로 변경
 */
public abstract class Cell
{
    protected static final String FLAG_SIGN = "⚑";
    protected static final String UNCHECKED_SIGN = "□";
    protected boolean isFlagged; // 깃발 유무
    protected boolean isOpened; // 오픈 여부

    public abstract boolean isLandMine();

    public abstract boolean hasLandMineCount();

    public abstract String getSign();

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }


    public boolean isOpened() {
        return isOpened;
    }


}
