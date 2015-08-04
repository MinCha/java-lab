package refactoring.day1.practice02.comments;

public class MatcherWorker {
    public static void main( String [] args) {
        Matcher matcher = new Matcher();

        int[] expected = new int[] {10, 50, 30, 98};
        int clipLimit = 100;
        int delta = 5;

        int[] actual = new int[] {12, 55, 25, 110};

        if ( matcher.match(expected, actual, clipLimit, delta) == true){
        	System.out.println("#1. OK");
        } else {
        	System.out.println("#1. NOT OK");
        }

        actual = new int[] {10, 50, 30};
        
        if ( matcher.match(expected, actual, clipLimit, delta) == false ){
        	System.out.println("#2. OK");
        } else {
        	System.out.println("#2. NOT OK");
        }
        
        actual = new int[] {10, 60, 30, 98};
        
        if ( matcher.match(expected, actual, clipLimit, delta) == false ){
        	System.out.println("#3. OK");
        } else {
        	System.out.println("#3. NOT OK");
        }

    }
}
