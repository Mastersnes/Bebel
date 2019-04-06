package bdd.youlose;


import bdd.abstrait.AbstractDao;
import enums.SaveType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static bdd.youlose.YouLoseTable.*;

public class YouLoseDao extends AbstractDao {
    private static YouLoseDao instance;
    private YouLoseTable youLose;

    private final Map<String, Map<SaveType, String>> datas = new HashMap<>();

    private YouLoseDao() {
        create(youLose = new YouLoseTable());
        load();

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> persist(), 1, 1, TimeUnit.MINUTES);
    }

    public static synchronized YouLoseDao getInstance() {
        if (instance == null) {
            instance = new YouLoseDao();
        }
        return instance;
    }

    public void save(final String username, final SaveType type, final String data) {
        getSaves(username).put(type, data);
    }

    public Map<SaveType, String> getSaves(final String username) {
        return datas.computeIfAbsent(username, saves -> new HashMap<>());
    }

    public String getSave(final String username, final SaveType type) {
        return getSaves(username).get(type);
    }

    @Override
    protected void persist() {
        final Map<String, String> params = new HashMap<>();

        for (final Map.Entry<String, Map<SaveType, String>> datasEntry : datas.entrySet()) {
            final String username = datasEntry.getKey();
            for (final Map.Entry<SaveType, String> saves : datasEntry.getValue().entrySet()) {
                final SaveType type = saves.getKey();
                final String save = saves.getValue();

                params.clear();
                params.put(USERNAME.name(), username);
                params.put(SAVE_TYPE.name(), type.toString());
                params.put(SAVE.name(), save);
            }
        }
    }

    @Override
    protected void load() {
        datas.clear();

        final List<Map<String, String>> listParams = selectAll(youLose);
        for (final Map<String, String> params : listParams) {
            final String username = params.get(USERNAME.name());
            final SaveType saveType = SaveType.fromCode(params.get(SAVE_TYPE.name()));
            final String save = params.get(SAVE);

            final Map<SaveType, String> saves = getSaves(username);
            saves.put(saveType, save);
        }
    }
}
