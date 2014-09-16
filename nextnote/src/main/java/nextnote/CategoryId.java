package nextnote;

//TODO 디자인 - NoteId와 거의 코드가 동일하다. 이럴 때는 어떻게 하는게 좋을까?
public class CategoryId implements Comparable<CategoryId> {
	private static int lastId = 0;
	private int id;
	
	protected static CategoryId issue() {
		lastId++;
		return new CategoryId(lastId);
	}

	private CategoryId(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	//TODO 디자인 - 필드가 바뀐다면 아래 코드는 어떻게 되는걸까?
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryId other = (CategoryId) obj;
		if (id != other.id)
			return false;
		return true;
	}

	//TODO 자바Comparator - CategoryId는 왜 compareTo를 구현하고 있는걸까?
	public int compareTo(CategoryId other) {
		if (this.id > other.id) {
			return 1;
		} else if (this.id == other.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
