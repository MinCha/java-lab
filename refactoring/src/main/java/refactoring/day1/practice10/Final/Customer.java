package refactoring.day1.practice10.Final;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
	private String _name;
	private List<Rental> _rentals = new ArrayList<Rental>();
	
	public Customer(String name){
		_name = name;
	}
	
	public void addRental(Rental arg){
		_rentals.add(arg);
	}
	
	public String getName(){
		return _name;
	}
	
	public String statement(){
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		String result = "[" + getName() + "]님의 대여 기록\n";
		while (rentals.hasNext()){
			double thisAmount = 0;
			Rental each = rentals.next();
			
			switch (each.getMovie().getPriceCode()){
				case Movie.REGULAR:
					thisAmount += 2;
					if (each.getDaysRented() > 2)
						thisAmount += (each.getDaysRented() -2) *1.5;
					break;
				case Movie.NEW_RELEASE:
					thisAmount += each.getDaysRented() * 3;
					break;
				case Movie.CHILDREN:
					thisAmount += 1.5;
					if (each.getDaysRented() > 3)
						thisAmount += (each.getDaysRented() -3) *1.5;
					break;
			}
			
			// frequent renter points adding
			frequentRenterPoints ++;
			
			//최신타이틀을 이틀 이상 대여하는 경우 추가 포인트 제공.
			if( (each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && 
					each.getDaysRented() > 1) frequentRenterPoints ++;
			
			//이 대여에 대한 요금 계산 결과 표시
			result += "\t" + each.getMovie().getTitle() + "\t" +
				String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		
		// Footer adding
		result += "총액: " + String.valueOf(totalAmount) + "\n";
		result += "추가 포인트: " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		
		return result;
	}
}
