package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;

/**
 * 리팩토링 (3) - 관점의 차이로 달라지는 추상화
 */
public interface PassReader
{
	StudyCafeSeatPasses readStudyCafePasses();

	StudyCafeLockerPasses readLockerPasses();
}
