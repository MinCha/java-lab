package refactoring.day2;

import refactoring.day2.client.CoverAndModifyExercise2Client;
import refactoring.day2.resource.Message;
import refactoring.day2.resource.Notifier;

/**
 * 목표
 * - 아래 소스를 Cover & Modify 한다.
 * - 이전 예제인 <code>CoverAndModifyExercise1</code>과의 차이점이 무엇인지 살펴본다.
 * - {@link CoverAndModifyExercise2Client}를 수정해도 좋다.
 */
public class CoverAndModifyExercise2 {
	private Notifier notifier;

	public CoverAndModifyExercise2(Notifier notifier) {
		this.notifier = notifier;
	}

	public void notify(int type) {
		if (type == 1) {
			notifier.notify(new Message("이벤트"));
		} else if (type == 2) {
			notifier.notify(new Message("해야할 일"));
		} else {
			notifier.notify(new Message("알수없음"));
		}
	}
}
