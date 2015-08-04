package refactoring.day1.practice03.LongParameterList.PreserveWholeObject;

public class ClientCode {

	public static void main(String[] args) {
		TempRange legalRange = new TempRange(20,10);
		
		HeatingPlan newPlan = new HeatingPlan( legalRange ); // 새로운 난방 계획
		
		Room myRoom = new Room();	// Room 의 기본 온도는 12도에서 18도 사이로 유지되도록 지정되어 있음
		
		System.out.println( myRoom.withinPlan( newPlan ) ); // 새로운 난방계획에 부합되는지 확인
	}

}
