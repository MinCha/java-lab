package nextnote;

import java.util.Date;

//TODO 하태호 - 코드중복 
//TODO 고형진 - 새로운 클래스의 발견 Option/Font/Text
//TODO 고형진 - 왜 Note에서 title등에 직접접근하냐?
/**
 * 1. Text에 대한 의견 
 * Text = Contents + Font
 * Note -> ...필드 + Text
 * 
 * 2. Option(고민해보자)
 * 이후에 여러 다른 옵션이 생길 가능성을 염두
 * Option의 자식으로 Font
 */
public class Note {
	private String titles;
	private String contents;
	private Date created;
	private Date updated;
	private String font;
	private int fontsize;

	public Note(String title, String contents, 
			String font) {
		this.titles = title;
		this.contents = contents;
		this.created = new Date();
		this.font = font;
		this.fontsize = 10;
	}

	public Note(String title, String contents,
			String font, int fontsize) {
		this.titles = title;
		this.contents = contents;
		this.created = new Date();
		this.font = font;
		this.fontsize = fontsize;
	}

	public String getTitle() {
		return titles;
	}

	public String getContents() {
		return contents;
	}

	public String getFont() {
		return font;
	}

	public int getFontsize() {
		return fontsize;
	}

	public void setUpdated(Date date) {
		this.updated = date;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public void modifyFont(String font) {
		//this.font = font;
		//this.updated = new Date();
		setUpdated(new Date());
		setFont(font);
	}
}