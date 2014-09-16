package nextnote;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AvailableFonts {
	private static List<String> fonts = Arrays.asList("굴림","바탕","궁서","맑은고딕");

	public static List<String> list() {
		return Collections.unmodifiableList(fonts);
	}

	public static String getDefault() {
		return fonts.get(0);
	}
}
