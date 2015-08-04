package refactoring.day1.practice07.SwitchStatements.EmployeeStep3;

public abstract class EmployeeType {
	
	static final int ENGNEER = 0;
	static final int SALESMAN = 1;
	static final int MANAGER = 2;
	
	static EmployeeType newType(int code){
		switch (code) {
		case ENGNEER:
			return new Engineer();
			
		case SALESMAN:
			return new Salesman();

		case MANAGER:
			return new Manager();

		default:
			throw new RuntimeException("Incorrect Employee");
		}
	}
	
	abstract int getTypeCode();
}
