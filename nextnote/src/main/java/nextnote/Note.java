package nextnote;

import java.util.Date;

public class Note {
	public String title;
	public String contents;
	public Date created;
	public Date updated;
	public String font;

	public Note(String title, String contents, Date created, Date updated,
			String font) {
		this.title = title;
		this.contents = contents;
		this.created = created;
		this.updated = updated;
		this.font = font;
	}
}