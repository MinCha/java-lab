package refactoring.day1.practice03.LongParameterList.PreserveWholeObject;

public class HeatingPlan {
	private TempRange _range;
	
	HeatingPlan(TempRange tempRange){
		_range = tempRange;
	}
	
	boolean withinRange(int low, int high){
		return (low>=_range.getLow() && high <= _range.getHigh());
	}
	
}
