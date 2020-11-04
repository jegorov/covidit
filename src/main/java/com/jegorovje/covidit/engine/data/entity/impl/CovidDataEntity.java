package com.jegorovje.covidit.engine.data.entity.impl;

import com.jegorovje.covidit.engine.data.entity.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@javax.persistence.Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "covid_data")
public class CovidDataEntity extends Entity {

  @Column(unique = true)
  String country;

  String newCases;

  String newDeath;

  String newRecovered;

  String sharp;

  Integer totalCases;

  String totalDeath;

  String totalRecovered;

  String activeCases;

  String seriousCritical;

  String totalTests;

  String population;

  String totalCasesPerMln;

  String deathsPerMln;

  String testsPerMln;

}
