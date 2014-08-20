package nextnote;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class NextNoteTest {
	//제목/본문/생성시간/마지막수정/폰트 
	@Test
	public void 노트를_추가할수_있다() {
		NextNote sut = new NextNote();
		
		int id = sut.addNextNote(new Note("next title", "contents", new Date(), new Date(), "nanum"));
		
		Note result = sut.findNote(id);
		Assert.assertEquals("next title", result.title);
		Assert.assertEquals("contents", result.contents);
	}
	
	@Test
	public void 노트의_폰트를_변경할수_있다() {
		NextNote sut = new NextNote();
		int id = sut.addNextNote(new Note("next title", "contents", new Date(), new Date(), "nanum"));
		
		sut.modifyFont(id, "gothic", 15);
		
		Note result = sut.findNote(id);
		Assert.assertEquals("gothic", result.font);		
	}
}
