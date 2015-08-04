package refactoring.day1.practice08.PrimitiveObsession;

public class Game {
	public static void main(String[] args){
		String [] row = new String[3];
		
		row[0] = "Liverpool";
		row[1] = "15";
		
		String name = row[0];
		int wins = Integer.parseInt(row[1]);
		
		System.out.println( " TeamName : " + name );
		System.out.println( " Wins : " + wins );
	}
}
