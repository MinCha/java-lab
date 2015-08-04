package refactoring.day1.practice09.Etc.OrganizingData;

import java.util.ArrayList;
import java.util.List;

public class PersonalReport {
	List persons;
	
	public List getPersons(){
		return this.persons;
	}
	
	public void setPersons(List persons){
		this.persons = persons;
	}
	
	public void printManagers() {
		List list = getPersons();
		for ( int i=0; i<list.size(); i++){
			Person ps = (Person)list.get(i);
			if ( ps.isManager ){
				System.out.println( "Name : " + ps.getName());
			}
		}
	}
}

