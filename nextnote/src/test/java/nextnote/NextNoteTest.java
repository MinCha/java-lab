package nextnote;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1. 직접 접근
 * note.title
 * 
 * 2. 간접 접근
 * note.getTitle() 
 */
public class NextNoteTest {		
	//제목/본문/생성시간/마지막수정/폰트 
	@Test
	public void 노트를_추가할수_있다() {
		NextNote sut = new NextNote();
		
		int id = sut.addNextNote(new Note("next title", "contents", "nanum"));
		
		Note result = sut.findNote(id);
		Assert.assertEquals("next title", result.getTitle());
		Assert.assertEquals("contents", result.getContents());
	}
	
	@Test
	public void 노트추가시_명시된_폰트사이즈가_없으면_기본폰트사이즈를_자동으로_지정한다() {
		NextNote sut = new NextNote();
		
		int id = sut.addNextNote(new Note("next title", "contents", "nanum"));
		
		Note result = sut.findNote(id);
		Assert.assertEquals(10, result.getFontsize());		
	}
	
	@Test
	public void 노트의_폰트를_변경할수_있다() {
		NextNote sut = new NextNote();
		int id = sut.addNextNote(new Note("next title", "contents", "nanum"));
	
		//TODO 폰트 사이즈는 현재 저장되지 않는 버그 있음  
		sut.modifyFont(id, "gothic", 15);
		
		Note result = sut.findNote(id);
		Assert.assertEquals("gothic", result.getFont());		
	}
	
	@Test
	public void 그동안_작성한_노트를_리스트로_조회할수_있다() {
		NextNote sut = new NextNote(); // SystemUnderTest(테스트 용어)
		sut.addNextNote(new Note("title1", "contents1", "font1"));
		sut.addNextNote(new Note("title2", "contents2", "font2"));
		sut.addNextNote(new Note("title3", "contents3", "font3"));
		sut.addNextNote(new Note("title4", "contents4", "font4"));
		sut.addNextNote(new Note("title5", "contents5", "font5"));
		
		List result = sut.getNotes();
		
		Assert.assertEquals(5, result.size());
		Assert.assertEquals(((Note)result.get(0)).getTitle(), "title1");
		Assert.assertEquals(((Note)result.get(4)).getTitle(), "title5");
	}
	
	@Test
	public void 노트를_삭제할수_있다() {
		NextNote sut = new NextNote();
		int id = sut.addNextNote(new Note("next title", "contents", "nanum"));
		
		sut.removeNote(id);
		
		//방식1
//		List<Note> result = sut.getNotes();
//		Assert.assertEquals(0, result.size());	
		//방식2
		Note foundNote = sut.findNote(id);
		Assert.assertNull(foundNote);
	}
	
	@Test
	public void 노트를_삭제해도_아이디가_유지되어야한다() {
		NextNote sut = new NextNote(); // SystemUnderTest(테스트 용어)
		int idA = sut.addNextNote(new Note("title1", "contents1", "font1"));
		int idB = sut.addNextNote(new Note("title2", "contents2", "font2"));
		int idC = sut.addNextNote(new Note("title3", "contents3", "font3"));
		
		sut.removeNote(idA);
		
		Assert.assertEquals("title3", sut.findNote(idC).getTitle());
	}
}
