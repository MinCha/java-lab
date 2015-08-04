package refactoring.day1.practice07.SwitchStatements.EmployeeStep2;

public class Employee {
	
	private int _type;
	
	private int _monthlySalary = 200;
	private int _commission = 50;
	private int _bonus = 100;
	
	static final int ENGNEER = 0;
	static final int SALESMAN = 1;
	static final int MANAGER = 2;
	
	Employee(){}
	
	Employee(int type){
		setType(type);
	}

	int payAmount(){
		switch(_type) {
		case ENGNEER:
			return _monthlySalary;
		case SALESMAN:
			return _monthlySalary + _commission;
		case MANAGER:
			return _monthlySalary + _bonus;
		default:
			throw new RuntimeException("Incorrect Employee");
		}
	}

	int getHoliday(){
		switch(_type) {
		case ENGNEER:
			return 10;
		case SALESMAN:
			return 15;
		case MANAGER:
			return 20;
		default:
			throw new RuntimeException("Incorrect Employee");
		}
	}
	
	String getJobTitle(){
		switch(_type) {
		case ENGNEER:
			return "기술직";
		case SALESMAN:
			return "영업직";
		case MANAGER:
			return "관리자";
		default:
			throw new RuntimeException("Incorrect Employee");
		}
	}

	public void setType(int type){
		_type = type;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
