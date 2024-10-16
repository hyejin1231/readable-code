package cleancode.minesweeper.tobe.cell;

public interface Cell {

    boolean isLandMine();

    boolean hasLandMineCount();

    CellSnapshot getSnapShat();

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

}