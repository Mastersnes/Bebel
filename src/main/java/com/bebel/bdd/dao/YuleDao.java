package com.bebel.bdd.dao;

import com.bebel.bdd.dto.YuleDto;
import com.bebel.soclews.util.Logger;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class YuleDao extends AbstractDao {
    private final Logger log = new Logger(getClass());

    public YuleDao() {
    }

    public String getSave(final String username) {
        log.info("Recuperation de la sauvegarde pour : " + username);
        final Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return unique("SELECT t.save FROM YuleDto t WHERE t.username = :username", params, String.class);
    }

    public void save(final String username, final String save) {
        log.info("Sauvegarde de : " + save + " pour : " + username);
        final YuleDto dto = new YuleDto();
        dto.setUsername(username);
        dto.setSave(save);
        save(dto);
    }
}
