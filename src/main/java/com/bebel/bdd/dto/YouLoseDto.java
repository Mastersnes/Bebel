package com.bebel.bdd.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = YouLoseDto.NAME)
public class YouLoseDto implements Serializable {
  public static transient final String NAME = "YOU_LOSE";

  @Id
  @Column(name="USERNAME", updatable = false, nullable = false)
  private String username;
  @Id
  @Column(name="SAVE_TYPE", updatable = false, nullable = false)
  private String type;

  @Lob
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
}
