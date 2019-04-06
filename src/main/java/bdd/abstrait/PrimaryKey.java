package bdd.abstrait;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Defini une clef etrangere
 */
public class PrimaryKey {
	private final List<String> fields = new ArrayList<>();

	public PrimaryKey(final Field... fields) {
		for (final Field field : fields) {
			add(field);
		}
	}
	public PrimaryKey(final String... fields) {
		for (final String field : fields) {
			add(field);
		}
	}
	public void add(final Field field) {
		add(field.name());
	}
	public void add(final String field) {
		fields.add(field);
	}

	public String resolve() {
		String template = "(%list%)";
		template = template.replaceAll("%list%", String.join(", ", fields));
		return template;
	}
}
