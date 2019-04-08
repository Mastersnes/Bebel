package bdd.youlose;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = YouLoseDTO.NAME)
public class YouLoseDTO implements Serializable {
    public static transient final String NAME = "YOU_LOSE";

    @Id
    @Column(name="USERNAME", updatable = false, nullable = false)
    private String username;
    @Id
    @Column(name="SAVE_TYPE", updatable = false, nullable = false)
    private String type;
    @Column(name="SAVE", nullable = false)
    private String save;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getSave() {
        return save;
    }
    public void setSave(String save) {
        this.save = save;
    }

    public String name() {
        return "YOU_LOSE";
    }
}
