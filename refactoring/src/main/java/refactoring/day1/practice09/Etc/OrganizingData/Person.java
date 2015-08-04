package refactoring.day1.practice09.Etc.OrganizingData;

class Person{
	String name;
	boolean isManager = false;
	
	public Person() {
	}
	
	public Person(String name, boolean isManager) {
		this.name = name;
		this.isManager = isManager;
	}

	public String getName() {
		return this.name;
	}
}