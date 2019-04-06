package bdd.abstrait;


import org.postgresql.ds.PGSimpleDataSource;
import util.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao {
	protected Logger log = new Logger(getClass());
	protected PGSimpleDataSource datasource;

	protected PGSimpleDataSource getDatasource() throws SQLException {
		if (datasource == null) {
			datasource = new PGSimpleDataSource();
			datasource.setServerName(System.getenv("DB_HOST"));
			datasource.setDatabaseName(System.getenv("DB_NAME"));
			try {
				datasource.setPortNumber(Integer.parseInt(System.getenv("DB_PORT")));
			} catch (final Exception e) {
				log.err("Impossible de parser le port de la BDD", e);
			}
			datasource.setUser(System.getenv("DB_USER"));
			datasource.setPassword(System.getenv("DB_PASSWORD"));
			datasource.setSsl(false);
		}
		return datasource;
	}

	protected AbstractTable create(final AbstractTable table) {
		final String create = table.create();
		try (final Connection connection = getDatasource().getConnection()) {
			final Statement statement = connection.createStatement();
			statement.executeUpdate(create);
		} catch (final SQLException e) {
			log.err("Impossible de creer la table " + table.name() + " avec la commande : " + create, e);
		}

		return table;
	}
	protected void insert(final AbstractTable table, Map<String, String> params) {
		final String insert = table.insert(params);
		try (final Connection connection = getDatasource().getConnection()) {
			final Statement statement = connection.createStatement();
			statement.executeUpdate(insert);
		} catch (final SQLException e) {
			log.err("Impossible de remplir la table " + table.name() + " avec la commande : " + insert, e);
		}
	}
	protected List<Map<String, String>> selectAll(final AbstractTable table) {
		final List<Map<String, String>> listParams = new ArrayList<>();
		final String select = table.selectAll();

		ResultSet resultSet = null;
		try (final Connection connection = getDatasource().getConnection()) {
			final Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(select);

			while (resultSet.next()) {
				final Map<String, String> resultParams = new HashMap<>();
				for (final Field field : table.fields()) {
					resultParams.put(field.name(), resultSet.getString(field.name()));
				}
				listParams.add(resultParams);
			}
		} catch (final SQLException e) {
			log.err("Impossible de consulter la table " + table.name() + " avec la commande : " + select, e);
		}
		return listParams;
	}



	protected abstract void persist();
	protected abstract void load();
}
