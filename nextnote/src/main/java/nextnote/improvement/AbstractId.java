package nextnote.improvement;


public abstract class AbstractId {
	protected static int lastId = 0;
	protected int id;
	
	protected AbstractId(int id) {
		this.id = id;
	}
	
	protected static int issueNextId() {
		lastId++;
		return lastId;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractId other = (AbstractId) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int compareTo(AbstractId other) {
		if (this.id > other.id) {
			return 1;
		} else if (this.id == other.id) {
			return 0;
		} else {
			return -1;
		}
	}
}
