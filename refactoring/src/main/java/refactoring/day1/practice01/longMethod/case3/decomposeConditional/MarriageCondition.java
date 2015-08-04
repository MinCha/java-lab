package refactoring.day1.practice01.longMethod.case3.decomposeConditional;

public class MarriageCondition {

	public void determine( int score, int income, boolean authorized ){
		
		if ( !((score > 90) || 
				( (income >= 400) && (income <= 600) 
						&& authorized && (score > 50) ) || 
				(income > 600)) ) 
		{
			reject();
		} else {
			accept();
		}
	}
	
	public void reject(){
		System.out.println("reject!");
	}
	
	public void accept(){
		System.out.println("accept!");
	}
}
