package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.PassReader;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.StudyCafeIoHandler;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;

import java.util.List;
import java.util.Optional;

/**
 * 리팩토링(1) - 추상화 레벨
 * - 중복 제거, 메서드 추출
 * - 객체에 메시지 보내기
 *
 * 리팩토링(2) - 객체의 책임과 응집도
 * - IO 통합
 * - 일급 컬렉션
 */
public class StudyCafePassMachine {

    private final StudyCafeIoHandler ioHandler = new StudyCafeIoHandler();

    private final PassReader passReader;

	public StudyCafePassMachine(PassReader passReader)
	{
		this.passReader = passReader;
	}

	public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = selectPass();

            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);
            StudyCafePassOrder passOrder = StudyCafePassOrder.of((selectedPass), optionalLockerPass.orElse(null));

            ioHandler.showPassOrderSummary(passOrder);

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectPass() {
        StudyCafePassType passType = ioHandler.askPassTypeSelecting();
        List<StudyCafeSeatPass> passCandidates = findPassCandidateBy(passType);

        return ioHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidateBy(StudyCafePassType studyCafePassType) {
        // 1. 어떤 데이터를 필요로 하는가 -> 데이터 중심을 여기에 초점을 맞추기, 이게 더 나은 추상화이다.
        // 2. 데이터를 어디로부터 어떻게 가져올 것인가 -> 방법에 초점 맞춘 것
        StudyCafeSeatPasses allPasses = passReader.readStudyCafePasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        // 고정 좌석 타입이 아닌가 ? -> 낮은 추상화
        // 사물함 옵션을 사용할 수 있는 타입이 아닌가 ? -> 높은 추상화
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();
            boolean isLockerSelected = ioHandler.askLockPass(lockerPass);
            if (isLockerSelected) {
                return Optional.of(lockerPass);
            }
        }
        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = passReader.readLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }

}
