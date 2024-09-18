package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";
    private int nearbyLandMineCount; // 근처 지뢰 숫자
    private boolean isLandMine;  // 지뢰 여부
    private boolean isFlagged; // 깃발 유무
    private boolean isOpened; // 오픈 여부

    public Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    // 정적 팩터링 메서드 -> 이름을 붙여줄 수 있기 때문에 강사님은 이렇게 활용해서 사용한다고 함.
    public static Cell of( int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened ) {
        return new Cell( nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearByLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCount != 0;
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }

        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
