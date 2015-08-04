package refactoring.day2.client;

import refactoring.day2.CoverAndModifyExercise2;
import refactoring.day2.resource.Notifier;

public class CoverAndModifyExercise2Client {
	public static void main(String[] args) {
		CoverAndModifyExercise2 sut = new CoverAndModifyExercise2(new Notifier());
		
		sut.notify(1);
		sut.notify(2);
		sut.notify(3);
	}
}
