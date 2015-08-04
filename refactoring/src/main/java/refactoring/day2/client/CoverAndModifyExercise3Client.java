package refactoring.day2.client;

import refactoring.day2.CoverAndModifyExercise3;

public class CoverAndModifyExercise3Client {
	public static void main(String[] args) {
		CoverAndModifyExercise3 sut = new CoverAndModifyExercise3();

		sut.notify(1);
		sut.notify(2);
		sut.notify(3);
	}
}
