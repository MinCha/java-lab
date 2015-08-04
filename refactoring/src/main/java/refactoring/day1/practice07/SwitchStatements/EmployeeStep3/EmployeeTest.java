package refactoring.day1.practice07.SwitchStatements.EmployeeStep3;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTest {
	
	@Test
	public void testPayAmount(){
		Employee employee = new Employee( EmployeeType.ENGNEER );
		assertEquals(employee.payAmount(), 200 );
		
		employee = new Employee( EmployeeType.SALESMAN );
		assertEquals(employee.payAmount(), 250 );
		
		employee = new Employee( EmployeeType.MANAGER );
		assertEquals(employee.payAmount(), 300 );
	}
}
