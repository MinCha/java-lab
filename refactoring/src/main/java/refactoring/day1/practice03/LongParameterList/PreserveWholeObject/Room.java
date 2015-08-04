package refactoring.day1.practice03.LongParameterList.PreserveWholeObject;

public class Room {
	
	private TempRange _range = TempRange.basicTempRange();
	
	boolean withinPlan(HeatingPlan plan){
		int low = daysTempRange().getLow();
		int high = daysTempRange().getHigh();
		return plan.withinRange(low,high);
	}
	
	public TempRange daysTempRange(){
		return _range;
	}
}
