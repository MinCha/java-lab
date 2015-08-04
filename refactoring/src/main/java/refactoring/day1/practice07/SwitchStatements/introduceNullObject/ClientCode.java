package refactoring.day1.practice07.SwitchStatements.introduceNullObject;

public class ClientCode {
	Site site;
	String clientName;
	
	ClientCode(Site site){
		this.site = site;
	}
	
	public void masterPlan(){

		Client client = site.getClient();
		BillingPlan plan;
		
		if ( client == null) plan = BillingPlan.basic();
		else plan = client.getPlan();
		
		if ( client == null) clientName = "occupant";
		else clientName = client.getName();
		
		System.out.println( "plan :" + plan.getPlan() );
		System.out.println( "name :" + clientName);
		
	}
	
	public static void main( String [] args){
		ClientCode mp = new ClientCode(new Site() );
		mp.masterPlan();
	}
}
