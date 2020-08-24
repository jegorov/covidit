package com.jegorovje.covidit.engine.data.entity.impl;

import com.jegorovje.covidit.engine.data.entity.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@javax.persistence.Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends Entity {

  String username;

  String password;

  String role;

  String token;
}
