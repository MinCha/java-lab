
package refactoring.day1.practice02.comments;

public class Matcher {
    public Matcher() {}
    public boolean match(int[] expected, int[] actual, 
        int clipLimit, int delta) 
    {

        // 배열내에 들어갈 수 있는 최대값을 정한다.
        for (int i = 0; i < actual.length; i++)
            if (actual[i] > clipLimit)
                actual[i] = clipLimit;

        // 두 배열의 길이가 같은지를 비교
        if (actual.length != expected.length)
            return false;

        // 배열의 각 값이 +/- delta 값 안에 들어가 있는지를 비교한다.
        for (int i = 0; i < actual.length; i++) {
            if (Math.abs(expected[i] - actual[i]) > delta)
                return false;
        }

        return true;
    }

}