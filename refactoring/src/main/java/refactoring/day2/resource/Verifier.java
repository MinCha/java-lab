package refactoring.day2.resource;

public class Verifier {
	private boolean initialized = false;

	public Verifier() {
		initialize();
	}

	protected void initialize() throws IllegalAccessError {
		if (initialized == false) {
			throw new IllegalAccessError("국가주민등록번호검증기관에 연결할 수 없습니다.");
		}
		// ...
	}

	public boolean validate(String identity) {
		if (initialized == false) {
			throw new IllegalAccessError("국가주민등록번호검증기관에 연결할 수 없습니다.");
		}

		boolean any = false;
		return any;
		// ...
	}
}
