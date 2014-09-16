package nextnote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {
	private CategoryId id;
	private String name;
	private Map<NoteId, Note> notes = new HashMap<NoteId, Note>();

	public String getName() {
		return name;
	}

	protected static Category createDefaultCategory() {
		return new Category(CategoryId.issue(), "카테고리 없음");
	}

	protected static Category create(CategoryId id, String name) {
		return new Category(id, name);
	}

	protected CategoryId getId() {
		return id;
	}

	protected void addNote(Note note) {
		this.notes.put(note.getId(), note);
	}

	protected Note first() {
		return getNotes().get(0);
	}

	protected int getNoteCount() {
		return notes.size();
	}

	protected List<Note> getNotes() {
		// TOOD 디자인 - 왜 여기서는 그냥 반환할까?
		return new ArrayList<Note>(notes.values());
	}

	protected void addNotes(List<Note> notes) {
		notes.addAll(notes);
	}

	protected void udateNote(Note note) {
		notes.put(note.getId(), note);
	}

	protected boolean removeNoteIfExists(NoteId noteId) {
		if (notes.containsKey(noteId) == false) {
			return false;
		}

		notes.remove(noteId);
		return true;
	}

	private Category(CategoryId id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", notes=" + notes
				+ "]";
	}
}
