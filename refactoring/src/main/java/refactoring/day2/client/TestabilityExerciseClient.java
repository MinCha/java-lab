package refactoring.day2.client;

import refactoring.day2.TestabilityExercise;


/**
 * 아래 코드는 단지 실습 대상을 사용하는 Client 코드로써 실습할 때 참고한다.
 * (실행되지 않는다.)
 */
public class TestabilityExerciseClient {
	public static void main(String[] args) {
		TestabilityExercise sut = new TestabilityExercise();
		
		int code = sut.toCodeFrom("8011301938211");
		
		if (code == 1) {
			System.out.println("남");
		} else if (code == 2) {
			System.out.println("여");			
		} else {
			System.out.println("오류");			
		}
	}
}
