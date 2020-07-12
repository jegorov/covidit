package com.jegorovje.covidit.engine.data.entity.impl;

import com.jegorovje.covidit.engine.data.entity.AbstractEntity;
import com.jegorovje.covidit.engine.data.entity.CovidDataEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "covid_data")
public class CovidDataEntityImpl extends AbstractEntity implements CovidDataEntity {

  @Id
  UUID id;
  @Column(unique = true)
  String country;
  String newCases;
  String newDeath;
  String newRecovered;
  String sharp;
  String totalCases;
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
