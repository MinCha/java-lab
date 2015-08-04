package refactoring.day2;

import refactoring.day2.client.CoverAndModifyExercise3Client;
import refactoring.day2.resource.Message;
import refactoring.day2.resource.Notifier;

/**
 * 목표
 * - 아래 소스를 Cover & Modify 한다.
 * - {@link CoverAndModifyExercise3Client}를 수정해도 좋다.
 *
 * @author Min Cha
 */
public class CoverAndModifyExercise3 {
	public void notify(int type) {
		Notifier notifier = new Notifier();

		if (type == 1) {
			notifier.notify(new Message("이벤트"));
		} else if (type == 2) {
			notifier.notify(new Message("해야할 일"));
		} else {
			notifier.notify(new Message("알수없음"));
		}
	}
}
