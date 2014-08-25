package nextnote;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//TODO [차민창] 학습방법과 추상화에 대한 얘기(닮은 얘기)
//TODO [차민창] 접근제어자의 의미  
//TODO 노트 추가 시 폰트 사이즈 어떻게 할꺼야?
public class NextNote {	
	public List notes = new LinkedList();

	public int addNextNote(Note note) {
		notes.add(note);
		return notes.size() - 1;
	}

	public Note findNote(int id) {
		//TODO [차민창] 노트를 넣었는데 왜 다시 캐스팅을 해야 하나? -> 제네릭으로 이어가면 좋을 듯
		return (Note) notes.get(id);
	}

	public void modifyFont(int id, String font, int size) {
		Note targetNote = (Note) notes.get(id);
		targetNote.updated = new Date();
		targetNote.font = font;
	}

}
