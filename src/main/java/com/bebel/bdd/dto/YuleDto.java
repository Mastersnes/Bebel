package com.bebel.bdd.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = YuleDto.NAME)
public class YuleDto implements Serializable {
  public static transient final String NAME = "YULE";

  @Id
  @Column(name="USERNAME", updatable = false, nullable = false)
  private String username;
  @Column(name="SAVE", nullable = false)
  private String save;

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getSave() {
    return save;
  }
  public void setSave(String save) {
    this.save = save;
  }
}
