//package bdd.abstrait;
//
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import util.HibernateUtil;
//import util.Logger;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class AbstractDao<DTO> {
//	protected Logger log = new Logger(getClass());
//
//	protected DTO persist(final DTO table) {
//		Transaction transaction = null;
//		try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
//			transaction = session.beginTransaction();
//			session.saveOrUpdate(table);
//			transaction.commit();
//		}catch (final Exception e) {
//			log.err("Impossible d'inserer ou de modifier la table.", e);
//		}
//		return table;
//	}
//
//	protected List<DTO> selectAll(final String table) {
//		final List<DTO> all = new ArrayList<>();
//		try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
//			final Query query = session.createQuery("SELECT * FROM " + table, table.getClass());
//			all.addAll(query.list());
//		}catch (final Exception e) {
//			log.err("Impossible d'inserer ou de modifier la table.", e);
//		}
//		return all;
//	}
//
//
//	/**
//	 * Persiste tout le cache
//	 */
//	protected abstract void persist();
//
//	/**
//	 * Recharge tout le cache
//	 */
//	protected abstract void load();
//}
