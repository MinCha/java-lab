package refactoring.day1.practice09.Etc.OrganizingData;

import java.util.ArrayList;
import java.util.List;

public class RuntimeCode {
	public static void main( String [] args ){

		PersonalReport report = new PersonalReport();

		List list = new ArrayList();
		list.add(new Person("강현수",false) );
		list.add(new Person("정인식",false));
		list.add(new Person("박성천",true));
		list.add(new Person("조아현",false));
		
		report.setPersons( list );
		report.printManagers();
		
		// some logic here..
		System.out.println("============== ");
		
		report.getPersons().add( new Person("이장건", true));
		report.printManagers();
	}
}
