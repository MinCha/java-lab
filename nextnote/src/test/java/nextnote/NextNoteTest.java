package nextnote;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 둘간에는 어떤 차이가 있는걸까? 
 * 
 * 1. 직접 접근 -> note.title
 * 2. 간접 접근 -> note.getTitle() 
 */
public class NextNoteTest {		
	private NextNote sut = new NextNote();
	
	@Test
	public void 노트를_추가할수_있다() {
		Category newCategory = sut.addCategory("일상");
		
		Note newNote = sut.addNote(aSomeNote());
		
		Category result = sut.findCategoryById(newCategory.getId());
		Note note = sut.findNoteById(newNote.getId());
		Assert.assertEquals(newCategory, result);
		Assert.assertEquals(aSomeNote().getTitle(), note.getTitle());
		Assert.assertEquals(aSomeNote().getContents(), note.getContents());
	}

	@Test
	public void 노트를_수정할수_있다() {
		Note note = sut.addNote(aSomeNote());
		
		Note savedNote = sut.findNoteById(note.getId());
		savedNote.setTitle("modified title");
		savedNote.setContents("modified contents");
		sut.updateNote(savedNote);
		
		Note result = sut.findNoteById(note.getId());
		Assert.assertEquals("modified title", result.getTitle());
		Assert.assertEquals("modified contents", result.getContents());
	}

	@Test
	public void 노트추가시_명시된_폰트사이즈가_없으면_기본폰트사이즈를_자동으로_지정한다() {
		Note note = sut.addNote(aSomeNote());
		
		Note result = sut.findNoteById(note.getId());
		
		Assert.assertNotNull(result.getContentsStyle().getFontSize());		
	}
	
	@Test
	public void 그동안_작성한_노트를_리스트로_조회할수_있다() {
		sut.addNote(new Note("title1", "contents1"));
		sut.addNote(new Note("title2", "contents2"));
		sut.addNote(new Note("title3", "contents3"));
		sut.addNote(new Note("title4", "contents4"));
		sut.addNote(new Note("title5", "contents5"));
		
		List<Note> result = sut.findAllNotes();
		
		Assert.assertEquals(5, result.size());
		Assert.assertEquals(((Note)result.get(0)).getTitle(), "title1");
		Assert.assertEquals(((Note)result.get(4)).getTitle(), "title5");
	}
	
	@Test
	public void 노트를_삭제할수_있다() {
		Note note = sut.addNote(aSomeNote());
		
		sut.removeNote(note);
		
		try {
			sut.findNoteById(note.getId());
			Assert.fail();
		} catch (NoteNotFoundException e) {
		}
	}
	
	@Test
	public void 노트를_삭제해도_아이디가_유지되어야한다() {
		Note noteA = sut.addNote(aSomeNote());
		Note noteB = sut.addNote(aSomeNote());
		Note noteC = sut.addNote(new Note("title3", "contents3"));
		
		sut.removeNote(noteA);
		sut.removeNote(noteB);
		
		Assert.assertEquals("title3", sut.findNoteById(noteC.getId()).getTitle());
	}
	
	@Test
	public void 카테고리를_추가할수있다() {
		Category first = sut.addCategory("일상");
		Category second = sut.addCategory("학습");
		
		Assert.assertNotNull(first);
		Category result = sut.findCategoryById(first.getId());
		Assert.assertEquals(first.getName(), result.getName());
		Assert.assertNotNull(second);
		result = sut.findCategoryById(second.getId());
		Assert.assertEquals(second.getName(), result.getName());
	}

	@Test
	public void 카테고리를_삭제할수있다() {
		Category category = sut.addCategory("지울 카테고리");
		
		sut.deleteCategory(category);
		
		Assert.assertNull(sut.findCategoryById(category.getId()));
	}

	@Test
	public void 서비스에서_제공가능한_폰트목록을_조회할수있다() {
		List<String> result = sut.getAvailableFonts();
		
		Assert.assertEquals(4, result.size());
	}

	@Test
	public void 사용자가_만든_카테고리_목록을_조회할수있다() {
		sut.addCategory("a");
		sut.addCategory("b");
		sut.addCategory("c");
		
		List<Category> result = sut.findAllCategories();

		Assert.assertEquals(4, result.size());
		Assert.assertEquals("a", result.get(1).getName());
		Assert.assertEquals("b", result.get(2).getName());
		Assert.assertEquals("c", result.get(3).getName());
	}

	@Test
	public void 카테고리를_삭제하면_그안에_모든_노트는_기본카테고리로_이동한다() {
		Category category = sut.addCategory("공부");
		sut.addNote(category, aSomeNote());
		sut.addNote(category, aSomeNote());
		sut.addNote(category, aSomeNote());
			
		sut.deleteCategory(category);
		
		Category defaultCategory = sut.getDefaultCategory();
		Assert.assertEquals(3, defaultCategory.getNoteCount());
	}

	private Note aSomeNote() {
		return new Note("title", "contents");
	}
}
