package refactoring.day1.practice07.SwitchStatements.EmployeeStep3;

public class Employee {
	
	private EmployeeType _type;
	
	private int _monthlySalary = 200;
	private int _commission = 50;
	private int _bonus = 100;
	
	Employee(int type){
		_type = EmployeeType.newType(type);
	}

	int payAmount(){
		switch(getType()) {
			case EmployeeType.ENGNEER:
				return _monthlySalary;
			case EmployeeType.SALESMAN:
				return _monthlySalary + _commission;
			case EmployeeType.MANAGER:
				return _monthlySalary + _bonus;
			default:
				throw new RuntimeException("Incorrect Employee");
		}
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return _type.getTypeCode();
	}

}
