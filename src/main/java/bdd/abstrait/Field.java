package bdd.abstrait;


import static bdd.abstrait.FieldType.TEXT;

public class Field {
	private final String name;
	private final FieldType type;
	private final FieldRestriction restriction;

	public Field(final String name) {
		this(name, TEXT);
	}
	public Field(final String name, final FieldType type) {
		this(name, type, null);
	}
	public Field(final String name, final FieldType type, final FieldRestriction restriction) {
		this.name = name;
		this.type = type;
		this.restriction = restriction;
	}

	public String name() {
		return name;
	}

	public String resolve() {
		final StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(type);
		if (restriction != null) sb.append(" ").append(restriction);
		return sb.toString();
	}

	@Override
	public String toString() {
		return name();
	}
}
