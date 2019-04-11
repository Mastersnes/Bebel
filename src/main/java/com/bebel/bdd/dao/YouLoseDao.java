package com.bebel.bdd.dao;

import com.bebel.bdd.dto.YouLoseDto;
import com.bebel.soclews.util.Logger;
import com.bebel.youloseClient.enums.SaveType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class YouLoseDao extends AbstractDao {
    private final Logger log = new Logger(getClass());

    public YouLoseDao() {
    }

    public Map<SaveType, String> getSaves(final String username) {
        final Map<String, String> params = new HashMap<>();
        params.put("username", username);
        final List<YouLoseDto> dtos = list("SELECT t FROM YouLoseDto t WHERE t.username = :username", params, YouLoseDto.class);

        final Map<SaveType, String> saves = new HashMap<>();
        for (final YouLoseDto dto : dtos) {
            saves.put(SaveType.fromCode(dto.getType()), dto.getSave());
        }

        return saves;
    }

    public String getSave(final String username, final SaveType type) {
        final Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("type", type.toString());

        return unique("SELECT t.save FROM YouLoseDto t WHERE t.username = :username", params, String.class);
    }

    public void save(final String username, final SaveType type, final String save) {
        final YouLoseDto dto = new YouLoseDto();
        dto.setUsername(username);
        dto.setType(type.toString());
        dto.setSave(save);
        save(dto);
    }
}
