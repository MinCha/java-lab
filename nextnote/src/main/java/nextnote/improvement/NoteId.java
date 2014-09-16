package nextnote.improvement;

public class NoteId extends AbstractId {
	private NoteId(int id) {
		super(id);
	}

	public static NoteId issue() {
		return new NoteId(AbstractId.issueNextId());
	}
}
