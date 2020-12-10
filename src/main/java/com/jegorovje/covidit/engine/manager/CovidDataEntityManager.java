package com.jegorovje.covidit.engine.manager;

import com.jegorovje.covidit.engine.data.entity.Entity;
import com.jegorovje.covidit.engine.data.entity.impl.CovidDataEntity;
import com.jegorovje.covidit.engine.util.CountryCodeMapperUtil;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Singleton
public class CovidDataEntityManager extends AbstractEntityManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(CovidDataEntityManager.class);

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
    entity.setActiveCases(parseToDoubleNewCases(map.get("activeCases")));
    entity.setTotalCases(Double.parseDouble(parseToString(map.get("totalCases"))));
    entity.setTotalDeath(parseToDoubleNewCases(map.get("totalDeath")));
    entity
        .setTotalRecovered(parseToDoubleNewCases(map.get("totalRecovered")));
    entity.setSharp(parseToString(map.get("sharp")));
    entity.setSeriousCritical(parseToDoubleNewCases(map.get("seriousCritical")));
    entity.setTotalTests(parseToDoubleNewCases(map.get("totalTests")));
    entity.setPopulation(parseToDoubleNewCases(map.get("population")));

    entity.setTestsPerMln(parseToDoubleNewCases(map.get("tests1M")));
    entity.setDeathsPerMln(parseToDoubleNewCases(map.get("deaths1M")));
    entity.setTotalCasesPerMln(parseToDoubleNewCases(map.get("totalCases1M")));

    entity.setCountry(parseToString(map.get("country")));
    entity.setNewCases(parseToDoubleNewCases(map.get("newCases")));
    entity.setNewDeath(parseToDoubleNewCases(map.get("newDeath")));
    entity.setNewRecovered(parseToDoubleNewCases(map.get("newRecovered")));
    entity.setId(UUID.nameUUIDFromBytes(entity.getCountry().getBytes()));

    try {
      entity.setCountryCode(CountryCodeMapperUtil.map().get(entity.getCountry()).getCountryCode());
    } catch (NullPointerException e) {
      LOGGER.error("Error during parsing country {}", entity.getCountry());
      System.out.println(entity.getCountry());
    }
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


  @SneakyThrows
  private Double parseToDoubleNewCases(String value) {
    if (value == null || value.strip().isEmpty()) {
      return null;
    }
    if (value.strip().startsWith("+")) {
      value = value.strip().substring(1);
    }
    if (value.strip().equals("N/A")) {
      return -1D;
    }
    value = value.strip().replace(",", "");
    return Double.valueOf(value);
  }
}
