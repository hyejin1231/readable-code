package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;

/**
 * 리팩토링 (3) - 관점의 차이로 달라지는 추상화
 */
public interface SeatPassProvider
{
	StudyCafeSeatPasses getSeatPasses();
}
