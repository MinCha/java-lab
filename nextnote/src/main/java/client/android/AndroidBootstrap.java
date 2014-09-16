package client.android;

import nextnote.Category;
import nextnote.NextNote;
import nextnote.Note;

//TODO 접근제어 - UI 개발자 입장에서 접근할 수 있는 메서드는?
public class AndroidBootstrap {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		NextNote nextNote = new NextNote();
		
		Category category = nextNote.addCategory("일기");
		//category.getName();
		
		Note note = new Note("2014/09/23", "오늘 코딩을 마쳤다. 기분이 좋다.");
		//note.getContents();
		//note.getFont();
		//note.getFontsize();
		//note.getId();
		//note.getTitle();
		//note.setContents(contents);
		//note.setTitle(title);
	}
}
