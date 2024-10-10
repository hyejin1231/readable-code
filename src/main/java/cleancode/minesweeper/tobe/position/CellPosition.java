package cleancode.minesweeper.tobe.position;

import java.util.Objects;

/**
 * Value Object
 * : 동등성, 불변성, 유효성 검증
 * -> rowIndex, colIndex는 둘이 붙어서 의미가 부여되는데 이를 묶어서 Value Object 로 만드니까 의미가 더 명확해졌다 !
 */
public class CellPosition {
    // final 필드를 이용한 불변성
    private final int rowIndex;
    private final int colIndex;

    public CellPosition(int rowIndex, int colIndex) {
        // 유효성 검증
        if (rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다.");
        }
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    // 팩토리 메서드를 제공해서 값 변경 못하게 객체 생성으로
    public static CellPosition of(int rowIndex, int colIndex) {
        return new CellPosition(rowIndex, colIndex);
    }

    // equals(), hashCode() 재정의를 통한 동등성 보장
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex && colIndex == that.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public boolean canCalculatePositionBy(RelativePosition relativePosition) {
        return this.rowIndex + relativePosition.getDeltaRow() >= 0 &&
                this.colIndex + relativePosition.getDeltaCol() >= 0;
    }

    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
        if (this.canCalculatePositionBy(relativePosition)) {
            return CellPosition.of(this.rowIndex + relativePosition.getDeltaRow(),
                    this.colIndex + relativePosition.getDeltaCol());
        }
        throw new IllegalArgumentException("움직일 수 있는 좌표가 아닙니다.");
    }

    public boolean isRowIndexMoreThanOrEqual(int rowIndex) {
        return this.rowIndex >= rowIndex;
    }

    public boolean isColIndexMoreThanOrEqual(int colIndex) {
        return this.colIndex >= colIndex;
    }

    public boolean isRowIndexLessThan(int rowIndex) {
        return this.rowIndex < rowIndex;
    }

    public boolean isColIndexLessThan(int colIndex) {
        return this.colIndex < colIndex;
    }
}
