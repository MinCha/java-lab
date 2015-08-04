package refactoring.day1.practice04.DivergentChange;

public class RuntimeCode {

	public static void main(String[] args) {
		Prescription prescription = new Prescription();
		
		System.out.println("--- Prescription for Patient --");
		System.out.println( prescription.getPrescription("adult", "serious") );
		System.out.println("-------------------------------");
		System.out.println("!! Caution !! DO NOT eat this pill over " 
				+ prescription.getMaxIngestionPerDay("adult", "serious") + " pill a day");
	}

}
