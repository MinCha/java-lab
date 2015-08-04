package refactoring.day2;

import refactoring.day2.client.CoverAndModifyExercise1Client;

/**
 * 목표
 * - 아래 소스를 Cover & Modify 한다.
 * - {@link CoverAndModifyExercise1Client}를 수정해도 좋다.
 * @author Min Cha
 */
public class CoverAndModifyExercise1 {
	public String remove(String s) {
		char[] arr = s.toCharArray();
		String to = "";

		for (char c : arr) {
			if (c != ' ') {
				to = to + c;
			}
		}

		return to;
	}
}