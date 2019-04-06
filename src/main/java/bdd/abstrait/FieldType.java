package bdd.abstrait;


public enum FieldType {
	TEXT, NUMBER, INTEGER, NUMERIC, SERIAL, BOOLEAN;

	private final String name;

	FieldType() {
		this(null);
	}
	FieldType(final String name) {
		this.name = name;
	}

	public String toString() {
		if (name != null) return name;
		else return super.name();
	}
}
