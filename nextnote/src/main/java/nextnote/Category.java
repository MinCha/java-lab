package nextnote;

public class Category {
	private Integer id;
	private String name;
	
	// static method factory patten
	public static Category create(Integer id, String name) {
		return new Category(id, name);
	}
	
	private Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
