package nextnote;

import java.util.LinkedList;
import java.util.List;

//TODO 노트 추가 시 폰트 사이즈 어떻게 할꺼야?
public class NextNote {	
	public List notes = new LinkedList();

	public int addNextNote(Note note) {
		notes.add(note);
		return notes.size() - 1;
	}

	public Note findNote(int id) {
		return (Note) notes.get(id);
	}

	public void modifyFont(int id, String font, int size) {
		Note targetNote = (Note) notes.get(id);
		targetNote.font = font;
	}

}
