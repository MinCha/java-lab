package refactoring.day1.practice07.SwitchStatements.EmployeeStep3;

public class Salesman extends EmployeeType {

	int getTypeCode() {
		return SALESMAN;
	}
	
	private int profit;

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

}
