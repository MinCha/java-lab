package nextnote.improvement;

//TODO 디자인 - NoteId와 거의 코드가 동일하다. 이럴 때는 어떻게 하는게 좋을까?
public class CategoryId extends AbstractId {
	private CategoryId(int id) {
		super(id);
	}

	public static CategoryId issue() {
		return new CategoryId(AbstractId.issueNextId());
	}
}
