package cleancode.minesweeper.tobe.cell;

public interface Cell {

    boolean isLandMine();

    CellSnapshot getSnapShat();

    boolean hasLandMineCount();

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

}