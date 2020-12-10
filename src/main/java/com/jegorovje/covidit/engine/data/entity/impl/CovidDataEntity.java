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

  @Column(unique = true)
  String countryCode;

//  @Column(unique = true)
  Integer m49code;

  Double newCases;

  Double newDeath;

  Double newRecovered;

  String sharp;

  Double totalCases;

  Double totalDeath;

  Double totalRecovered;

  Double activeCases;

  Double seriousCritical;

  Double totalTests;

  Double population;

  Double totalCasesPerMln;

  Double deathsPerMln;

  Double testsPerMln;

}
