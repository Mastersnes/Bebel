package bdd.abstrait;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractTable {
	public abstract String name();
	protected abstract List<Field> fields();
	protected abstract String primary();
	protected abstract String foreign();
	protected String resolveFields() {
		final List<String> fields = new ArrayList<>();
		for (final Field field : fields()) {
			fields.add(field.resolve());
		}
		return String.join(", ", fields);
	}

	public String create() {
		String template = "CREATE TABLE IF NOT EXISTS %table% (%datas%));";

		final List<String> datas = new ArrayList<>();
		datas.add("%fields%");
		datas.add("%primaries%");
		if (foreign() != null) datas.add("%foreign%");
		template = template.replaceAll("%datas%", String.join(", ", datas));

		template = template.replaceAll("%table%", name());
		template = template.replaceAll("%fields%", resolveFields());
		template = template.replaceAll("%primaries%", "PRIMARY KEY " + primary());
		if (foreign() != null) template = template.replaceAll("%foreign%", foreign());

		return template;
	}

	public String insert(final Map<String, String> params) {
		String template = "INSERT INTO %table% (%keys%) VALUES (%values%) ON CONFLICT %primaries% DO UPDATE SET %update%;";
		template = template.replaceAll("%table%", name());
		template = template.replaceAll("%keys%", String.join(", ", params.keySet()));
		template = template.replaceAll("%values%", String.join(", ", params.values()));
		template = template.replaceAll("%primaries%", primary());

		final List<String> updates = new ArrayList<>();
		for (final Map.Entry<String, String> entry : params.entrySet()) {
			if (primary().contains(entry.getKey())) continue;
			updates.add(entry.getKey() + "=" + entry.getValue());
		}

		template = template.replaceAll("%update%", String.join(", ", updates));

		return template;
	}

	public String selectAll() {
		String template = "SELECT * FROM %table%;";
		return template.replaceAll("%table%", name());
	}
}
