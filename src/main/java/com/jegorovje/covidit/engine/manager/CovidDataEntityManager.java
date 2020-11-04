package com.jegorovje.covidit.engine.manager;

import com.jegorovje.covidit.engine.data.entity.Entity;
import com.jegorovje.covidit.engine.data.entity.impl.CovidDataEntity;
import io.micronaut.transaction.annotation.ReadOnly;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import lombok.SneakyThrows;


@Singleton
public class CovidDataEntityManager extends AbstractEntityManager {


  @Inject
  EntityManager entityManager;

  public Entity create() {
    return new CovidDataEntity();
  }

  @ReadOnly
  @Transactional
  public CovidDataEntity findCovidStatisticByCountry(@NotNull String country) {
    String qlString = "from CovidDataEntityImpl as a where LOWER(country) LIKE LOWER(:country)";
    TypedQuery<CovidDataEntity> query = entityManager.createQuery(qlString, CovidDataEntity.class);
    query.setParameter("country", country);

    return query.getResultStream().findFirst().get();
  }

  @ReadOnly
  @Transactional
  public List<CovidDataEntity> getCovidDataList() {
    String qlString = "from CovidDataEntity order by totalCases desc";
    TypedQuery<CovidDataEntity> query = entityManager.createQuery(qlString, CovidDataEntity.class);
    return query.getResultList();
  }

  public CovidDataEntity createCovidStatisticFromMap(Map<String, String> map) {
    CovidDataEntity entity = (CovidDataEntity) create();
    entity.setActiveCases(parseToString(map.get("activeCases")));
    entity.setTotalCases(Integer.parseInt(parseToString(map.get("totalCases"))));
    entity.setTotalDeath(parseToString(map.get("totalDeath")));
    entity
        .setTotalRecovered(parseToString(map.get("totalRecovered")));
    entity.setSharp(parseToString(map.get("sharp")));
    entity.setSeriousCritical(parseToString(map.get("seriousCritical")));
    entity.setTotalTests(parseToString(map.get("totalTests")));
    entity.setPopulation(parseToString(map.get("population")));

    entity.setTestsPerMln(parseToString(map.get("tests1M")));
    entity.setDeathsPerMln(parseToString(map.get("deaths1M")));
    entity.setTotalCasesPerMln(parseToString(map.get("totalCases1M")));

    entity.setCountry(parseToString(map.get("country")));
    entity.setNewCases(parseToString(map.get("newCases")));
    entity.setNewDeath(parseToString(map.get("newDeath")));
    entity.setNewRecovered(parseToString(map.get("newRecovered")));
    entity.setId(UUID.nameUUIDFromBytes(entity.getCountry().getBytes()));
    return entity;
  }

  @SneakyThrows
  private String parseToString(String value) {
    if (value == null || value.strip().isEmpty()) {
      return null;
    }
    if (value.strip().equals("N/A")) {
      return value.strip();
    }
    value = value.strip().replace(",", "");
    return value;
  }
}
