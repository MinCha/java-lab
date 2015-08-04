package refactoring.day1.practice09.Etc.simplize;

public class Pay {

	private boolean isNew;
	private boolean isChild;
	private boolean isRetired;

	public Pay(boolean isNew, boolean isChild, boolean isRetired) {
		this.isNew = isNew;
		this.isChild = isChild;
		this.isRetired = isRetired;
	}

	double getPayAmount(){
		double result;
		if(isNew) result = 200;
		else {
			if(isChild) result = 350;
			else {
				if(isRetired) result = 100;
				else result = 300;
			}
		}
		return result;
	}
}
