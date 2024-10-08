package cleancode.minesweeper.tobe.cell;

/**
 * 상속과 조합
 * -> 상속보다는 조합을 사용해서 중복 코드가 조금 있더라도 유연하게 설계하는 것이 더 좋다.
 */
public interface Cell
{
    String FLAG_SIGN = "⚑";
    String UNCHECKED_SIGN = "□";

    boolean isLandMine();

    boolean hasLandMineCount();

    String getSign();

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();


}
