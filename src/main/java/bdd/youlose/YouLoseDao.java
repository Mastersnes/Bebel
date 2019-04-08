//package bdd.youlose;
//
//
//import bdd.abstrait.AbstractDao;
//import enums.SaveType;
//import util.HibernateUtil;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Function;
//
//public class YouLoseDao extends AbstractDao {
//    private static YouLoseDao instance;
//
//    private final Map<String, Map<SaveType, String>> datas = new HashMap<>();
//
//    private YouLoseDao() {
//        HibernateUtil.getSessionFactory();
//        load();
//
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                persist();
//            }
//        }, 1, 1, TimeUnit.MINUTES);
//    }
//
//    public static synchronized YouLoseDao getInstance() {
//        if (instance == null) {
//            instance = new YouLoseDao();
//        }
//        return instance;
//    }
//
//    public void save(final String username, final SaveType type, final String data) {
//        getSaves(username).put(type, data);
//    }
//
//    public Map<SaveType, String> getSaves(final String username) {
//        Map<SaveType, String> saves = datas.get(username);
//        if (saves == null) {
//            saves = datas.put(username, new HashMap<SaveType, String>());
//        }
//        return saves;
//    }
//
//    public String getSave(final String username, final SaveType type) {
//        return getSaves(username).get(type);
//    }
//
//    @Override
//    protected void persist() {
//        for (final Map.Entry<String, Map<SaveType, String>> datasEntry : datas.entrySet()) {
//            final String username = datasEntry.getKey();
//            for (final Map.Entry<SaveType, String> saves : datasEntry.getValue().entrySet()) {
//                persist(username, saves.getKey(), saves.getValue());
//            }
//        }
//    }
//
//    private void persist(final String username, final SaveType type, final String save) {
//        final YouLoseDTO dto = new YouLoseDTO();
//        dto.setUsername(username);
//        dto.setType(type.toString());
//        dto.setSave(save);
//
//    }
//
//    @Override
//    protected void load() {
//        datas.clear();
//
//        final List<YouLoseDTO> listDto = selectAll(YouLoseDTO.NAME);
//        for (final YouLoseDTO dto : listDto) {
//            final SaveType type = SaveType.fromCode(dto.getType());
//            final Map<SaveType, String> saves = getSaves(dto.getUsername());
//            saves.put(type, dto.getSave());
//        }
//    }
//}
