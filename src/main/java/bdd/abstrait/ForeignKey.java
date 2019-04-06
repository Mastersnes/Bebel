package bdd.abstrait;


import java.util.Map;
import java.util.TreeMap;

/**
 * Defini une clef etrangere
 */
public class ForeignKey {
	private final String references;
	private final Map<String, String> fields = new TreeMap<>();

	public ForeignKey(final String references) {
		this.references = references;
	}
	public void add(final Field f1, final Field f2) {
		add(f1.name(), f2.name());
	}
	public void add(final String f1, final String f2) {
		fields.put(f1, f2);
	}

	public String sources() {
		return String.join(", ", fields.keySet());
	}
	public String dest() {
		return String.join(", ", fields.values());
	}
	public String references() {
		return references;
	}

	public String resolve() {
		String template = "FOREIGN KEY (%source%) REFERENCES %ref% (%dest%)";
		template = template.replaceAll("%source%", sources());
		template = template.replaceAll("%dest%", dest());
		template = template.replaceAll("%ref%", references());
		return template;
	}
}
