package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Utilitaire JSON utilisé pour les données fixes
 */
public class JsonUtil {
//	private Logger logger = new Logger(getClass());

	public static JsonUtil instance;
	protected final String CONNEXION_PATH = "./Bebel/data.json";


	private JsonUtil(){}

	public static synchronized JsonUtil getInstance() {
		if (instance == null) {
			instance = new JsonUtil();
		}
		return instance;
	}

	/**
	 * Methode permettant de charger un fichier json
	 * 
	 */
	public String load(final String path) {
		String json = "";
		final File data = new File(path);
		try (final BufferedReader in = new BufferedReader(new FileReader(data))) {
			String line;
			while ((line = in.readLine()) != null) json += line;
		} catch (final Exception e) {
//			if (data != null) logger.err("Erreur lors du chargement du fichier : " + data.getAbsolutePath(), e);
//			else logger.err("Erreur lors du chargement du fichier : " + path, e);
		}
		return json;
	}

    /**
     * Methode permettant de sauver un fichier json
     * 
     */
    public void save(final String path, final String json) {
        final File data = new File(path);
        try (final BufferedWriter out = new BufferedWriter(new FileWriter(data))) {
            out.append(json);
            out.flush();
        } catch (final Exception e) {
//			if (data != null) logger.err("Erreur lors de la sauvegarde du fichier : " + data.getAbsolutePath(), e);
//			else logger.err("Erreur lors de la sauvegarde du fichier : " + path, e);
        }
    }
}
