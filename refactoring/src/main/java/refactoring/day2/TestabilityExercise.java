package refactoring.day2;

import refactoring.day2.resource.PersonService;
import refactoring.day2.resource.Verifier;

/**
 * 오늘 얘기한 모든 지식들을 총망라하여 아래 코드를 Cover & Modify를 이용하여 개선한다.
 * - {@link TestabilityExercise}를 수정해도 좋다.
 * @
 */
public class TestabilityExercise {
	private static Verifier verifier = new Verifier();
	private PersonService personService;

	public TestabilityExercise() {
		personService = new PersonService();
	}

	public int toCodeFrom(String identity) {
		if (verifier.validate(identity)) {
			throw new RuntimeException();
		}

		personService.notify(identity);

		return personService.isMan(identity) ? 1 : 2;
	}
}
