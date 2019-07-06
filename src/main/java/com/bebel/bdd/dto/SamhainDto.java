package com.bebel.bdd.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = SamhainDto.NAME)
public class SamhainDto implements Serializable {
  public static transient final String NAME = "SAMHAIN";

  @Id
  @Column(name="USERNAME", updatable = false, nullable = false)
  private String username;

  @Lob
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
