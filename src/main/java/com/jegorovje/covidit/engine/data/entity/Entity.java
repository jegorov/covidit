package com.jegorovje.covidit.engine.data.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class Entity implements Serializable {

  @Id
  UUID id;

  @PrePersist
  public void initializeUUID() {
    if (id == null) {
      id = UUID.randomUUID();
    }
  }
}
