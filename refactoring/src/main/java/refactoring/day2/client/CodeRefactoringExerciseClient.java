package refactoring.day2.client;

import refactoring.day2.CodeRefactoringExercise;
import refactoring.day2.resource.CStop;
import refactoring.day2.resource.Notifier;
import refactoring.day2.resource.RStop;

/**
 * 아래 코드는 단지 실습 대상을 사용하는 Client 코드로써 실습할 때 참고한다.
 * (실행되지 않는다.)
 */
public class CodeRefactoringExerciseClient {
	public static void main(String[] args) {
		CodeRefactoringExercise sut = new CodeRefactoringExercise(new CStop(), new RStop(), new Notifier());
		
		sut.stop(1, "년별 정기점검");
		sut.stop(2, "주별 정기점검");
	}
}
