package nextnote;

import java.util.Date;

public class Note {
	private NoteId id;
	private String title;
	private String contents;
	private Date created;
	private Date updated;
	private ContentsStyle contentsStyle;

	public Note(String title, String contents) {
		// TODO 중복제거 - this 코드 중복을 어떻게 해결했는가? 
		this(title, contents, ContentsStyle.createDefault());
	}

	public Note(String title, String contents,
			ContentsStyle contentsStyle) {
		this.title = title;
		this.contents = contents;
		this.created = new Date();
		this.contentsStyle = contentsStyle;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}
	
	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public ContentsStyle getContentsStyle() {
		return contentsStyle;
	}
	
	public NoteId getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	protected void setId(NoteId id) {
		this.id = id;
	}

	protected boolean isSameId(NoteId id) {
		return this.id.equals(id);
	}

	public void setContentStyle(ContentsStyle contentsStyle) {
		this.contentsStyle = contentsStyle;
		setUpdated(new Date());
	}

	private void setUpdated(Date date) {
		this.updated = date;
	}
}