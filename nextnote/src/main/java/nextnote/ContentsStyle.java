package nextnote;

public class ContentsStyle {
	private String fontName = AvailableFonts.getDefault();
	private int fontSize = 10;
	private boolean italic = false;

	public static ContentsStyle createDefault() {
		return new ContentsStyle();
	}

	public String getFontName() {
		return fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public boolean isItalic() {
		return italic;
	}

	public ContentsStyle markAsItalic() {
		this.italic = true;
		return this;
	}

	public ContentsStyle changeSize(int fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	public ContentsStyle changeFont(String fontName) {
		this.fontName = fontName;
		return this;
	}

	private ContentsStyle() {
	}
}
