package nextnote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NextNote {
	private Category defaultCategory;
	private Map<CategoryId, Category> categories = new TreeMap<CategoryId, Category>();

	public NextNote() {
		createDefaultCategory();
	}

	public Note addNote(Note note) {
		return addNote(defaultCategory, note);
	}

	public Note addNote(Category category, Note note) {
		NoteId id = NoteId.issue();
		note.setId(id);
		category.addNote(note);
		return note;
	}

	public Note findNoteById(NoteId id) {
		// TODO 성능 - 노트를 찾을 때 아래와 같이 O(n)인데 이게 좋은 방법일지?
		for (Category each : categories.values()) {
			for (Note eachNote : each.getNotes()) {
				if (eachNote.isSameId(id)) {
					return eachNote;
				}
			}
		}

		// TODO 디자인 - 왜 Null을 반환하지 않고 Exception을 던질까?
		throw new NoteNotFoundException();
	}

	public List<Note> findAllNotes() {
		List<Note> result = new ArrayList<Note>();

		for (Category each : categories.values()) {
			result.addAll(each.getNotes());
		}

		return result;
	}

	public void removeNote(Note note) {
		for (Category each : categories.values()) {
			each.removeNoteIfExists(note.getId());
		}
	}

	public Category addCategory(String categoryName) {
		CategoryId id = CategoryId.issue();
		Category category = Category.create(id, categoryName);
		categories.put(category.getId(), category);
		return category;
	}

	public Category findCategoryById(CategoryId id) {
		return categories.get(id);
	}

	public void deleteCategory(Category category) {
		for (Note each : category.getNotes()) {
			defaultCategory.addNote(each);
		}

		categories.remove(category.getId());
	}

	public List<Category> findAllCategories() {
		// TODO 디자인 - unmodifiableList를 쓰는 이유는?
		return Collections.unmodifiableList(new ArrayList<Category>(categories
				.values()));
	}

	public void updateNote(Note note) {
		Category category = findCategoryByNoteId(note.getId());
		// TODO 자바참조모델 - 아래 한줄(updateNote)을 빼도 테스트가 통과한다. 왜 통과할까? 그리고 왜 아래 코드가 있어야 할까?
		category.udateNote(note);
	}

	public List<String> getAvailableFonts() {
		return AvailableFonts.list();
	}

	public Category getDefaultCategory() {
		return defaultCategory;
	}

	private Category findCategoryByNoteId(NoteId noteId) {
		for (Category each : categories.values()) {
			for (Note eachNote : each.getNotes()) {
				if (eachNote.isSameId(noteId)) {
					return each;
				}
			}
		}
		
		throw new NoteNotFoundException();
	}	

	private void createDefaultCategory() {
		defaultCategory = Category.createDefaultCategory();
		categories.put(defaultCategory.getId(), defaultCategory);
	}
}