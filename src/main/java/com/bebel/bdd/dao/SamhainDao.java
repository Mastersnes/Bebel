package com.bebel.bdd.dao;

import com.bebel.bdd.dto.SamhainDto;
import com.bebel.soclews.util.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SamhainDao extends AbstractDao {
    private final Logger log = new Logger(getClass());

    public SamhainDao() {
    }

    public String getSave(final String username) {
        log.info("Recuperation de la sauvegarde pour : " + username);
        final Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return unique("SELECT t.save FROM SamhainDto t WHERE t.username = :username", params, String.class);
    }

    public void save(final String username, final String save) {
        log.info("Sauvegarde de : " + save + " pour : " + username);
        final SamhainDto dto = new SamhainDto();
        dto.setUsername(username);
        dto.setSave(save);
        save(dto);
    }
}
