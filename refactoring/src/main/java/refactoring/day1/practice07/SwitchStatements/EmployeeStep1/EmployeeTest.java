package refactoring.day1.practice07.SwitchStatements.EmployeeStep1;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTest {
	
	@Test
	public void testPayAmount(){
		Employee employee = new Employee( Employee.ENGNEER );
		assertEquals(employee.payAmount(), 200 );
		
		employee = new Employee( Employee.SALESMAN );
		assertEquals(employee.payAmount(), 250 );
		
		employee = new Employee( Employee.MANAGER );
		assertEquals(employee.payAmount(), 300 );
	}
}
