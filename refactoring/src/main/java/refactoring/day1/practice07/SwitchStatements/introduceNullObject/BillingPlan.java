package refactoring.day1.practice07.SwitchStatements.introduceNullObject;

public class BillingPlan {
	
	private String planB;
	
	BillingPlan(){
		planB = "play";
	}

	public static BillingPlan basic(){
		return new BillingPlan();
	}
	
	public String getPlan(){
		return planB;
	}
}
