package com.jegorovje.covidit.engine.data.entity.impl;

import com.jegorovje.covidit.engine.data.entity.AbstractEntity;
import com.jegorovje.covidit.engine.data.entity.CovidStatisticEntity;
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
@Table(name = "covid_statistic")
public class CovidStatisticEntityImpl extends AbstractEntity implements CovidStatisticEntity{

  @Id
  UUID id;
  @Column(unique = true)
  String country;
  String newCases, newDeath, newRecovered;
  Integer sharp, totalCases, totalDeath, totalRecovered, activeCases, seriousCritical, totalTests, population;
  Double totalCasesPerMln, deathsPerMln, testsPerMln;
}
