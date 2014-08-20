package nextnote;

import java.util.Date;

//TODO 하태호 - 코드중복 
//TODO 고형진 - 새로운 클래스의 발견 Option/Font/Text
//TODO 고형진 - 왜 Note에서 title등에 직접접근하냐?
public class Note {
	public String title;
	public String contents;
	public Date created;
	public Date updated;
	public String font;
	public int fontsize;

	public Note(String title, String contents, 
			String font) {
		this.title = title;
		this.contents = contents;
		this.created = new Date();
		this.font = font;
		this.fontsize = 10;
	}

	public Note(String title, String contents,
			String font, int fontsize) {
		this.title = title;
		this.contents = contents;
		this.created = new Date();
		this.font = font;
		this.fontsize = fontsize;
	}
}