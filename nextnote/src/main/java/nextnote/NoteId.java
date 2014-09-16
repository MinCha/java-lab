package nextnote;

public class NoteId {
	private static int lastId = 0;
	private int id;
	
	protected static NoteId issue() {
		lastId++;
		return new NoteId(lastId);
	}

	private NoteId(int id) {
		this.id = id;
	}

	// TODO 디자인 - 여기는 왜 get을 안 붙혔을까?
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteId other = (NoteId) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
