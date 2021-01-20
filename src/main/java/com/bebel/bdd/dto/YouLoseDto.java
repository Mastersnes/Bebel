package com.bebel.bdd.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    YouLoseDto that = (YouLoseDto) o;
    return Objects.equals(username, that.username) && Objects.equals(type, that.type) && Objects.equals(save, that.save);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, type, save);
  }
}
