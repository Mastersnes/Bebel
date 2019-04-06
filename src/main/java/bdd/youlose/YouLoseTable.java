package bdd.youlose;


import bdd.abstrait.AbstractTable;
import bdd.abstrait.Field;
import bdd.abstrait.PrimaryKey;

import java.util.Arrays;
import java.util.List;

import static bdd.abstrait.FieldRestriction.NOT_NULL;
import static bdd.abstrait.FieldType.TEXT;

public class YouLoseTable extends AbstractTable {
    public static final String NAME = "YOU_LOSE";
    public static final Field USERNAME = new Field("USERNAME", TEXT, NOT_NULL);
    public static final Field SAVE_TYPE = new Field("SAVE_TYPE", TEXT, NOT_NULL);
    public static final Field SAVE = new Field("SAVE", TEXT, NOT_NULL);

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public List<Field> fields() {
        return Arrays.asList(USERNAME, SAVE_TYPE, SAVE);
    }

    @Override
    public String primary() {
        return new PrimaryKey(USERNAME, SAVE_TYPE).resolve();
    }

    @Override
    public String foreign() {
        return null;
    }
}
