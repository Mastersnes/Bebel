package bdd.abstrait;

public enum FieldRestriction {
	NOT_NULL("NOT NULL");

	private final String name;

	FieldRestriction() {
		this(null);
	}
	FieldRestriction(final String name) {
		this.name = name;
	}

	public String toString() {
		if (name != null) return name;
		else return super.name();
	}
}
