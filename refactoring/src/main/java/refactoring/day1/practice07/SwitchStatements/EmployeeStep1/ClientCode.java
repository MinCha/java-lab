package refactoring.day1.practice07.SwitchStatements.EmployeeStep1;

public class ClientCode
{

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		// 엔지니어
		Employee employee = new Employee( Employee.ENGNEER );
		
		System.out.println( employee.payAmount() );
	}

}
