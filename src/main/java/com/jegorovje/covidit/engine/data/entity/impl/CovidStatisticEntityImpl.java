package com.jegorovje.covidit.engine.data.entity.impl;

import com.jegorovje.covidit.engine.data.entity.AbstractEntity;
import com.jegorovje.covidit.engine.data.entity.CovidStatisticEntity;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//todo нужно ли добавлять сериалайзбл, если в родительском уже есть
@Entity
@Data
@Table(name = "covid_statistic")
public class CovidStatisticEntityImpl extends AbstractEntity implements CovidStatisticEntity,
    Serializable {

  @Id
  private UUID id;
  @Column(unique = true)
  private String country;
  private String newCases, newDeath, newRecovered;
  private Integer sharp, totalCases, totalDeath, totalRecovered, activeCases, seriousCritical, totalTests, population;
  private Double totalCasesPerMln, deaths1PerMln, testsPerMln;
}
