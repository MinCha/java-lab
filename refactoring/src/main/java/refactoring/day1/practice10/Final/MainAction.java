package refactoring.day1.practice10.Final;

public class MainAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Customer customer = new Customer("홍길동");
		Movie movie1 = new Movie("TITANIC2", Movie.NEW_RELEASE);
		Movie movie2 = new Movie("EROS", Movie.NEW_RELEASE);
		Movie movie3 = new Movie("Goodbye, Lenin!", Movie.REGULAR);
		
		customer.addRental(new Rental(movie1, 1));
		customer.addRental(new Rental(movie2, 3));
		customer.addRental(new Rental(movie3, 7));

		System.out.println(customer.statement());;
	}

}
