package nextnote;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//TODO [차민창] 접근제어자의 의미  
//TODO 노트 추가 시 폰트 사이즈 어떻게 할꺼야?
public class NextNote {
	public int currentId = 1;
	public Map<Integer, Note> notes = new HashMap<Integer, Note>();

	public int addNextNote(Note note) {
		int id = currentId++;
		notes.put(id, note);
		return id;
	}
	
	// IndexOutOfBoundException
	public Note findNote(int id) {
		//TODO [차민창] 노트를 넣었는데 왜 다시 캐스팅을 해야 하나? -> 제네릭으로 이어가면 좋을 듯
		return notes.get(id);
	}

	public void modifyFont(int id, String font, int size) {
		Note targetNote = (Note) notes.get(id);
		targetNote.modifyFont(font);		
	}

	public List<Note> getNotes() {
		return new LinkedList<Note>(notes.values());
	}

	public void removeNote(int id) {
		notes.remove(id);
	}
}
