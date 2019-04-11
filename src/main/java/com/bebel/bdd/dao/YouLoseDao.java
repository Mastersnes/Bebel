package com.bebel.bdd.dao;

import com.bebel.bdd.dto.YouLoseDto;
import com.bebel.soclews.util.Logger;
import com.bebel.youloseClient.enums.SaveType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class YouLoseDao extends AbstractDao {
    private final Logger log = new Logger(getClass());

    public YouLoseDao() {
    }

    public Map<SaveType, String> getSaves(final String username) {
        final Map<SaveType, String> saves = new HashMap<>();
        try (final Session session = sessionFactory().openSession()) {
            final Query<YouLoseDto> query = session.createQuery(
                    "SELECT t FROM YouLoseDto t WHERE t.username = :username",
                    YouLoseDto.class
            );
            query.setParameter("username", username);

            for (final YouLoseDto dto : query.list()) {
                saves.put(SaveType.fromCode(dto.getType()), dto.getSave());
            }
        } catch (final Exception e) {
            log.err("Impossible de lire la table.", e);
        }
        return saves;
    }

    public String getSave(final String username, final SaveType type) {
        String save = null;
        try (final Session session = sessionFactory().openSession()) {
            final Query<YouLoseDto> query = session.createQuery(
                    "SELECT t FROM YouLoseDto t WHERE t.username = :username AND t.type = :type",
                    YouLoseDto.class
            );
            query.setParameter("username", username);
            query.setParameter("type", type.toString());

            final YouLoseDto dto = query.uniqueResult();
            if (dto != null) save = dto.getSave();
        } catch (final Exception e) {
            log.err("Impossible de lire la table.", e);
        }
        return save;
    }

    public void save(final String username, final SaveType type, final String save) {
        try (final Session session = sessionFactory().openSession()) {
            final YouLoseDto dto = new YouLoseDto();
            dto.setUsername(username);
            dto.setType(type.toString());
            dto.setSave(save);
            session.saveOrUpdate(dto);
        } catch (final Exception e) {
            log.err("Impossible de lire la table.", e);
        }
    }
}
